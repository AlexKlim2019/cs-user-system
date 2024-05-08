package com.cs.user.system.user.service.presentation.rest;

import com.cs.user.system.user.service.presentation.exception.handler.GlobalExceptionHandler;
import com.cs.user.system.user.service.domain.dto.command.DeleteUserCommand;
import com.cs.user.system.user.service.domain.dto.command.PatchUserCommand;
import com.cs.user.system.user.service.domain.dto.response.DeleteUserResponse;
import com.cs.user.system.user.service.domain.port.input.service.UserApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Map;

import static com.cs.user.system.user.service.presentation.exception.handler.GlobalExceptionHandler.INTERNAL_SERVER_ERROR_MESSAGE;
import static com.cs.user.system.user.service.presentation.utils.BodyMapGenerator.CreateUserPayloadGenerator.*;
import static com.cs.user.system.user.service.presentation.utils.BodyMapGenerator.SearchUsersPayload.generateInvalidSearchUsersBodyMap;
import static com.cs.user.system.user.service.presentation.utils.BodyMapGenerator.SearchUsersPayload.generateValidSearchUsersBodyMap;
import static com.cs.user.system.user.service.presentation.utils.BodyMapGenerator.UpdateUserPayloadGenerator.*;
import static com.cs.user.system.user.service.presentation.utils.TestConstants.*;
import static com.cs.user.system.user.service.presentation.utils.UserGenerator.CommandGenerator.*;
import static com.cs.user.system.user.service.presentation.utils.UserGenerator.Responses.*;
import static com.cs.user.system.user.service.presentation.utils.UserGenerator.generateValidSearchUsersQuery;
import static java.time.LocalDate.now;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {UserController.class, GlobalExceptionHandler.class})
class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserApplicationService service;

    private final LocalDate invalidBirthDate = now().plusDays(1);

    @Nested
    class CreateUserTests {

        @Test
        void successfulScenario() throws Exception {
            var command = generateValidCreateUserCommand();
            var response = generateSuccessCreateUserResponse();
            var bodyMap = generateCreateUserValidBodyMap();
            var jsonBody = objectMapper.writeValueAsString(bodyMap);
            given(service.saveUser(command)).willReturn(response);

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.user.id").value(response.user().getId().toString()))
                    .andExpect(jsonPath("$.user.firstName").value(response.user().getFirstName()))
                    .andExpect(jsonPath("$.user.lastName").value(response.user().getLastName()))
                    .andExpect(jsonPath("$.user.email").value(response.user().getEmail()))
                    .andExpect(jsonPath("$.user.birthDate").value(response.user().getBirthDate().toString()))
                    .andExpect(jsonPath("$.user.address").value(response.user().getAddress()))
                    .andExpect(jsonPath("$.user.phoneNumber").value(response.user().getPhoneNumber()))
                    .andExpect(jsonPath("$.message").value(response.message()));
        }

        @Test
        void givenRequestPayloadWithoutFirstName_thenReturnBadRequestResponse() throws Exception {
            var bodyMap = generateCreateUserBodyMapWithoutFirstName();
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The firstName field has the error: First name is mandatory!"));
        }

        @Test
        void givenRequestPayloadWithoutLastName_thenReturnBadRequestResponse() throws Exception {
            var bodyMap = generateCreateUserBodyMapWithoutLastName();
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The lastName field has the error: Last name is mandatory!"));
        }

        @Test
        void givenRequestPayloadWithoutEmail_thenReturnBadRequestResponse() throws Exception {
            var bodyMap = generateCreateUserBodyMapWithoutEmail();
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The email field has the error: Email is mandatory!"));
        }

        @Test
        void givenRequestPayloadWithInvalidEmail_thenReturnBadRequestResponse() throws Exception {
            var invalidEmail = "test.mail.com";
            var bodyMap = generateCreateUserBodyMapWithInvalidEmail(invalidEmail);
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The email field has the error: Email is not correct!"));
        }

        @Test
        void givenRequestPayloadWithoutBirthDate_thenReturnBadRequestResponse() throws Exception {
            var bodyMap = generateCreateUserBodyMapWithoutBirthDate();
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The birthDate field has the error: Birth date is mandatory!"));
        }

        @Test
        void givenRequestPayloadWithInvalidBirthDate_thenReturnBadRequestResponse() throws Exception {
            var bodyMap = generateCreateUserBodyMapWithInvalidBirthDate(invalidBirthDate);
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The birthDate field has the error: Birth date must be earlier than current date!"));
        }

        @Test
        void givenThrownGeneralException_thenReturnInternalServerErrorResponse() throws Exception {
            var command = generateValidCreateUserCommand();
            var bodyMap = generateCreateUserValidBodyMap();
            var jsonBody = objectMapper.writeValueAsString(bodyMap);
            given(service.saveUser(command)).willThrow(new ArrayIndexOutOfBoundsException());

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("$.code").value("Internal Server Error"))
                    .andExpect(jsonPath("$.message").value(INTERNAL_SERVER_ERROR_MESSAGE));
        }
    }

    @Nested
    class SearchUsersTests {

        @Test
        void successfulScenario() throws Exception {
            var command = generateValidSearchUsersQuery();
            var response = generateSuccessSearchUsersResponse();
            var user = response.users().get(0);
            var bodyMap = generateValidSearchUsersBodyMap();
            var jsonBody = objectMapper.writeValueAsString(bodyMap);
            given(service.findAll(command)).willReturn(response);

            mvc.perform(get("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.users[0].id").value(user.getId().toString()))
                    .andExpect(jsonPath("$.users[0].firstName").value(user.getFirstName()))
                    .andExpect(jsonPath("$.users[0].lastName").value(user.getLastName()))
                    .andExpect(jsonPath("$.users[0].email").value(user.getEmail()))
                    .andExpect(jsonPath("$.users[0].birthDate").value(user.getBirthDate().toString()))
                    .andExpect(jsonPath("$.users[0].address").value(user.getAddress()))
                    .andExpect(jsonPath("$.users[0].phoneNumber").value(user.getPhoneNumber()));
        }

        @Test
        void givenRequestPayloadWithIncorrectTimeRange_thenReturnBadRequestResponse() throws Exception {
            var bodyMap = generateInvalidSearchUsersBodyMap();
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(get("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("'From' must be less than 'To'"));
        }

        @Test
        void givenRequestPayloadWithoutFromValue_thenReturnBadRequestResponse() throws Exception {
            var bodyMap = Map.of("to", TO);
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(get("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The from field has the error: From is mandatory!"));
        }

        @Test
        void givenRequestPayloadWithoutToValue_thenReturnBadRequestResponse() throws Exception {
            var bodyMap = Map.of("from", FROM);
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(get("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The to field has the error: To is mandatory!"));
        }
    }

    @Nested
    class UpdateUserTests {

        @Test
        void successfulScenario() throws Exception {
            var bodyMap = generateUpdateUserValidBodyMap();
            var jsonBody = objectMapper.writeValueAsString(bodyMap);
            var command = generateValidUpdateUserCommand();
            var response = generateSuccessUpdateUserResponse();
            var updatedUser = response.user();
            given(service.updateUser(command)).willReturn(response);

            mvc.perform(put("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.user.id").value(updatedUser.getId().toString()))
                    .andExpect(jsonPath("$.user.firstName").value(updatedUser.getFirstName()))
                    .andExpect(jsonPath("$.user.lastName").value(updatedUser.getLastName()))
                    .andExpect(jsonPath("$.user.email").value(updatedUser.getEmail()))
                    .andExpect(jsonPath("$.user.birthDate").value(updatedUser.getBirthDate().toString()))
                    .andExpect(jsonPath("$.user.address").value(updatedUser.getAddress()))
                    .andExpect(jsonPath("$.user.phoneNumber").value(updatedUser.getPhoneNumber()))
                    .andExpect(jsonPath("$.message").value(response.message()));
        }

        @Test
        void givenRequestPayloadWithoutId_thenReturnBadRequestResponse() throws Exception {
            var bodyMap = generateUpdateUserBodyMapWithoutId();
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(put("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The id field has the error: Id is mandatory!"));
        }

        @Test
        void givenRequestPayloadWithoutFirstName_thenReturnBadRequestResponse() throws Exception {
            var bodyMap = generateUpdateUserBodyMapWithoutFirstName();
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The firstName field has the error: First name is mandatory!"));
        }

        @Test
        void givenRequestPayloadWithoutLastName_thenReturnBadRequestResponse() throws Exception {
            var bodyMap = generateUpdateUserBodyMapWithoutLastName();
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The lastName field has the error: Last name is mandatory!"));
        }

        @Test
        void givenRequestPayloadWithoutEmail_thenReturnBadRequestResponse() throws Exception {
            var bodyMap = generateUpdateUserBodyMapWithoutEmail();
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The email field has the error: Email is mandatory!"));
        }

        @Test
        void givenRequestPayloadWithInvalidEmail_thenReturnBadRequestResponse() throws Exception {
            var invalidEmail = "test.mail.com";
            var bodyMap = generateUpdateUserBodyMapWithInvalidEmail(invalidEmail);
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The email field has the error: Email is not correct!"));
        }

        @Test
        void givenRequestPayloadWithoutBirthDate_thenReturnBadRequestResponse() throws Exception {
            var bodyMap = generateUpdateUserBodyMapWithoutBirthDate();
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The birthDate field has the error: Birth date is mandatory!"));
        }

        @Test
        void givenRequestPayloadWithInvalidBirthDate_thenReturnBadRequestResponse() throws Exception {
            var bodyMap = generateUpdateUserBodyMapWithInvalidBirthDate(invalidBirthDate);
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The birthDate field has the error: Birth date must be earlier than current date!"));
        }
    }

    @Nested
    class PartialUpdateUserTests {

        @Test
        void successfulScenario() throws Exception {
            var validBodyMap = Map.of("id", USER_ID.toString(), "firstName", UPDATED_FIRST_NAME);
            var jsonBody = objectMapper.writeValueAsString(validBodyMap);
            var command = PatchUserCommand.builder()
                    .id(USER_ID)
                    .firstName(UPDATED_FIRST_NAME)
                    .build();
            var response = generateSuccessPatchUserResponse();
            var patchedUser = response.user();
            given(service.partialUpdateUser(command)).willReturn(response);

            mvc.perform(patch("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.user.id").value(patchedUser.getId().toString()))
                    .andExpect(jsonPath("$.user.firstName").value(patchedUser.getFirstName()))
                    .andExpect(jsonPath("$.user.lastName").value(patchedUser.getLastName()))
                    .andExpect(jsonPath("$.user.email").value(patchedUser.getEmail()))
                    .andExpect(jsonPath("$.user.birthDate").value(patchedUser.getBirthDate().toString()))
                    .andExpect(jsonPath("$.user.address").value(patchedUser.getAddress()))
                    .andExpect(jsonPath("$.user.phoneNumber").value(patchedUser.getPhoneNumber()))
                    .andExpect(jsonPath("$.message").value(response.message()));
        }

        @Test
        void givenRequestPayloadWithoutId_thenReturnBadRequestResponse() throws Exception {
            var bodyMapWithoutId = Map.of("firstName", UPDATED_FIRST_NAME);
            var jsonBody = objectMapper.writeValueAsString(bodyMapWithoutId);

            mvc.perform(patch("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The id field has the error: Id is mandatory!"));
        }

        @Test
        void givenRequestPayloadWithInvalidEmail_thenReturnBadRequestResponse() throws Exception {
            var invalidEmail = "test.mail.com";
            var invalidBodyMap = Map.of("id", USER_ID, "email", invalidEmail);
            var jsonBody = objectMapper.writeValueAsString(invalidBodyMap);

            mvc.perform(patch("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The email field has the error: Email is not correct!"));
        }

        @Test
        void givenRequestPayloadWithInvalidBirthDate_thenReturnBadRequestResponse() throws Exception {
            var invalidBodyMap = Map.of("id", USER_ID, "birthDate", invalidBirthDate);
            var jsonBody = objectMapper.writeValueAsString(invalidBodyMap);

            mvc.perform(patch("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The birthDate field has the error: Birth date must be earlier than current date!"));
        }
    }

    @Nested
    class DeleteUserTests {

        @Test
        void successfulScenario() throws Exception {
            var validBodyMap = Map.of("id", USER_ID.toString());
            var jsonBody = objectMapper.writeValueAsString(validBodyMap);
            var command = new DeleteUserCommand(USER_ID);
            var response = new DeleteUserResponse("Delete user response message");
            given(service.deleteUser(command)).willReturn(response);

            mvc.perform(delete("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.message").value(response.message()));
        }

        @Test
        void givenRequestPayloadWithoutId_thenReturnBadRequestResponse() throws Exception {
            var bodyMapWithoutId = Map.of();
            var jsonBody = objectMapper.writeValueAsString(bodyMapWithoutId);

            mvc.perform(delete("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message")
                            .value("The id field has the error: Id is mandatory!"));
        }
    }
}
