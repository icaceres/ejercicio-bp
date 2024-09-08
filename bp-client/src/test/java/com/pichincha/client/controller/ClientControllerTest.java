package com.pichincha.client.controller;

import com.pichincha.client.domain.Client;
import com.pichincha.client.repository.ClientRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    public static final String PERSON_NAME = "Test Name";
    @Autowired
    private MockMvc mockMvc;

    private static final String CLIENT_ID = "123456";

    @BeforeAll
    public static void setUp(@Autowired ClientRepository clientRepository) {
        Client client = new Client();
        client.setPersonName(PERSON_NAME);
        client.setAddress("Test Address");
        client.setTelephone("99999999");
        client.setPassword("secret");
        client.setIdentification(CLIENT_ID);
        client.setStatus(Boolean.TRUE);
        clientRepository.save(client);
    }

    @Test
    public void shouldBeGetAllClients() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldBeGetClientById() throws Exception {
        mockMvc.perform(get("/clientes/" + CLIENT_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.personName").value(PERSON_NAME))
                .andExpect(jsonPath("$.address").value("Test Address"))
                .andExpect(jsonPath("$.telephone").value("99999999"));
    }

    @Test
    public void shouldBeCreateClientIfExist() throws Exception {
        String newClientJson = "{ \"identification\": \"123456\", \"personName\": \"Test Name 2\", \"address\": \"456 Elm St\", \"telephone\": \"555-5678\", \"password\": \"secret\" }";

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newClientJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldBeDeleteClient() throws Exception {
        mockMvc.perform(delete("/clientes/" + CLIENT_ID))
                .andExpect(status().isOk());
    }


}
