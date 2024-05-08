package com.cs.user.system.user.service.application.rest;

import com.cs.user.system.user.service.application.exception.handler.GlobalExceptionHandler;
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
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static com.cs.user.system.user.service.application.exception.handler.GlobalExceptionHandler.INTERNAL_SERVER_ERROR_MESSAGE;
import static com.cs.user.system.user.service.application.utils.BodyMapGenerator.CreateUserPayloadGenerator.*;
import static com.cs.user.system.user.service.application.utils.BodyMapGenerator.SearchUsersPayload.*;
import static com.cs.user.system.user.service.application.utils.TestConstants.*;
import static com.cs.user.system.user.service.application.utils.UserGenerator.Responses.generateSuccessCreateUserResponse;
import static com.cs.user.system.user.service.application.utils.UserGenerator.Responses.generateSuccessSearchUsersResponse;
import static com.cs.user.system.user.service.application.utils.UserGenerator.generateValidCreateUserCommand;
import static com.cs.user.system.user.service.application.utils.UserGenerator.generateValidSearchUsersQuery;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                    .andExpect(jsonPath("$.message").value("The firstName field has the error: First name is mandatory!"));
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
                    .andExpect(jsonPath("$.message").value("The lastName field has the error: Last name is mandatory!"));
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
                    .andExpect(jsonPath("$.message").value("The email field has the error: Email is mandatory!"));
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
                    .andExpect(jsonPath("$.message").value("The email field has the error: Email is not correct!"));
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
                    .andExpect(jsonPath("$.message").value("The birthDate field has the error: Birth date is mandatory!"));
        }

        @Test
        void givenRequestPayloadWithInvalidBirthDate_thenReturnBadRequestResponse() throws Exception {
            var invalidBirthDate = LocalDate.now().plus(1, ChronoUnit.DAYS);
            var bodyMap = generateCreateUserBodyMapWithInvalidBirthDate(invalidBirthDate);
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST_CODE))
                    .andExpect(jsonPath("$.message").value("The birthDate field has the error: Birth must be earlier than current date!"));
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
                    .andExpect(jsonPath("$.code").value(INTERNAL_SERVER_ERROR_CODE))
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
                    .andExpect(jsonPath("$.message").value("'From' must be less than 'To'"));
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
                    .andExpect(jsonPath("$.message").value("The from field has the error: From is mandatory!"));
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
                    .andExpect(jsonPath("$.message").value("The to field has the error: To is mandatory!"));
        }
    }
}
