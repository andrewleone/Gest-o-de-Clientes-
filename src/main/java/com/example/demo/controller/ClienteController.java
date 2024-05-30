package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.ClienteService;
import com.example.demo.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{clienteId}")
    public ResponseEntity<?> getClienteById(@PathVariable("clienteId") Long clienteId) {
        if (clienteId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid clienteId");
        }

        try {
            return ResponseEntity.ok(clienteService.getClienteById(clienteId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
