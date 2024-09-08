package com.pichincha.client.controller;

import com.pichincha.client.service.ClientService;
import com.pichincha.client.service.dto.ClientDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable(value = "id") String identification) {
        return ResponseEntity.ok(clientService.getClientByIdentification(identification));
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientDto client) {
        return new ResponseEntity<>(clientService.saveClient(client), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable(value = "id") String identification, @RequestBody ClientDto clientToUpdate) {
        return ResponseEntity.ok(clientService.updateClient(identification, clientToUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") String identification) {
        return ResponseEntity.ok(clientService.deleteClient(identification));
    }
}
