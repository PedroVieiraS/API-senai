package com.api.senai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.senai.classes.ContaBancaria;
import com.api.senai.service.ContaBancariaService;

@RestController
@RequestMapping("contas")
public class ContaBancariaController {

    @Autowired
    private ContaBancariaService contaBancariaService;

    @GetMapping
    public ResponseEntity<List<ContaBancaria>> getAll() {
        return ResponseEntity.ok(contaBancariaService.getAll());
    }

    

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancaria> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contaBancariaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ContaBancaria> create(@RequestBody ContaBancaria contaBancaria) {
        return ResponseEntity.ok(contaBancariaService.create(contaBancaria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaBancaria> update(@PathVariable Long id, @RequestBody ContaBancaria contaNova) {
        ContaBancaria contaExistente = contaBancariaService.getById(id);

        if (contaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(contaBancariaService.update(contaExistente, contaNova));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contaBancariaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
