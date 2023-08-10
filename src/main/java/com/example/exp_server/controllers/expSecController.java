package com.example.exp_server.controllers;

import com.example.exp_server.models.expSection;
import com.example.exp_server.repositories.expSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/exps")

public class expSecController {

    @Autowired
    private expSectionRepository expSecRepository;

    @PostMapping("/addExpSection")
    public ResponseEntity<String> addExpSection(@RequestBody expSection expSec) {
        // Initially setting an empty list of expenses
        expSec.setExp(new ArrayList<>());

        expSection savedExpSec = expSecRepository.save(expSec);

        return new ResponseEntity<>("Expense section added with ID: " + savedExpSec.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteExpSection/{id}")
    public ResponseEntity<String> deleteExpSection(@PathVariable String id) {
        if (expSecRepository.existsById(id)) {
            expSecRepository.deleteById(id);
            return new ResponseEntity<>("Expense section deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Expense section not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/editSectionTitle/{id}")
    public ResponseEntity<String> editSectionTitle(
            @PathVariable String id,
            @RequestParam String newSectionTitle
    ) {
        if (expSecRepository.existsById(id)) {
            expSection expSec = expSecRepository.findById(id).orElse(null);
            if (expSec != null) {
                expSec.setSecTitle(newSectionTitle);
                expSecRepository.save(expSec);
                return new ResponseEntity<>("Section title updated", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Expense section not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllExpSections")
    public ResponseEntity<List<expSection>> getAllExpSections() {
        List<expSection> expSections = expSecRepository.findAll();
        return new ResponseEntity<>(expSections, HttpStatus.OK);
    }



}
