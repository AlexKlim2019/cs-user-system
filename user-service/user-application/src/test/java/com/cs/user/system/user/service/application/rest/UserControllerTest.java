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

import static com.cs.user.system.user.service.application.utils.UserGenerator.BodyMapGenerator.createBodyMapWithDefaultValues;
import static com.cs.user.system.user.service.application.utils.UserGenerator.BodyMapGenerator.createBodyMapWithoutFirstName;
import static com.cs.user.system.user.service.application.utils.UserGenerator.generateDefaultCreateUserCommand;
import static com.cs.user.system.user.service.application.utils.UserGenerator.generateDefaultCreateUserResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
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
            var command = generateDefaultCreateUserCommand();
            var response = generateDefaultCreateUserResponse();
            var bodyMap = createBodyMapWithDefaultValues();
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
        void givenRequestPayloadWithoutFirstName_thenReturnBadRequestError() throws Exception {
            var emptyFirstName = " ";
            var bodyMap = createBodyMapWithoutFirstName(emptyFirstName);
            var jsonBody = objectMapper.writeValueAsString(bodyMap);

            mvc.perform(post("/users")
                            .contentType(APPLICATION_JSON)
                            .content(jsonBody))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(BAD_REQUEST.name()))
                    .andExpect(jsonPath("$.violations[0].fieldName").value("firstName"))
                    .andExpect(jsonPath("$.violations[0].message").value("First name is mandatory"));
        }
    }
}