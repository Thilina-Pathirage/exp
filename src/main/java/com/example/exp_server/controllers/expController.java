package com.example.exp_server.controllers;

import com.example.exp_server.models.exp;
import com.example.exp_server.models.expSection;
import com.example.exp_server.repositories.expSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@CrossOrigin()
@RestController
@RequestMapping("/expenses")
public class expController {

    @Autowired
    private expSectionRepository expSecRepository;

    // ... Add Expense Section and Delete Expense Section endpoints ...

    @PostMapping("/addExpense/{sectionId}")
    public ResponseEntity<String> addExpense(
            @PathVariable String sectionId,
            @RequestBody exp newExpense
    ) {
        expSection expSec = expSecRepository.findById(sectionId).orElse(null);

        if (expSec != null) {
            String expenseId = generateUniqueExpenseId(); // Generate a unique ID for the expense
            newExpense.setExpId(expenseId); // Set the generated ID for the expense
            expSec.addExp(newExpense);
            expSecRepository.save(expSec);
            return new ResponseEntity<>("Expense added to section", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Expense section not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteExpense/{sectionId}/{expenseId}")
    public ResponseEntity<String> deleteExpense(
            @PathVariable String sectionId,
            @PathVariable String expenseId
    ) {
        expSection expSec = expSecRepository.findById(sectionId).orElse(null);

        if (expSec != null) {
            List<exp> expenses = expSec.getExp();
            Optional<exp> expenseToRemove = expenses.stream()
                    .filter(e -> e.getExpId().equals(expenseId))
                    .findFirst();

            if (expenseToRemove.isPresent()) {
                expenses.remove(expenseToRemove.get());
                expSecRepository.save(expSec);
                return new ResponseEntity<>("Expense deleted from section", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Expense not found", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Expense section not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateExpense/{sectionId}/{expenseId}")
    public ResponseEntity<String> updateExpense(
            @PathVariable String sectionId,
            @PathVariable String expenseId,
            @RequestBody exp updatedExpense
    ) {
        expSection expSec = expSecRepository.findById(sectionId).orElse(null);

        if (expSec != null) {
            List<exp> expenses = expSec.getExp();
            Optional<exp> expenseToUpdate = expenses.stream()
                    .filter(e -> e.getExpId().equals(expenseId))
                    .findFirst();

            if (expenseToUpdate.isPresent()) {
                exp existingExpense = expenseToUpdate.get();
                existingExpense.setExpTitle(updatedExpense.getExpTitle());
                existingExpense.setExpValue(updatedExpense.getExpValue());
                expSecRepository.save(expSec);
                return new ResponseEntity<>("Expense updated in section", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Expense not found", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Expense section not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updatePaidStatus/{sectionId}/{expenseId}")
    public ResponseEntity<String> updatePaidStatus(
            @PathVariable String sectionId,
            @PathVariable String expenseId,
            @RequestParam boolean paid
    ) {
        expSection expSec = expSecRepository.findById(sectionId).orElse(null);

        if (expSec != null) {
            List<exp> expenses = expSec.getExp();
            Optional<exp> expenseToUpdate = expenses.stream()
                    .filter(e -> e.getExpId().equals(expenseId))
                    .findFirst();

            if (expenseToUpdate.isPresent()) {
                exp existingExpense = expenseToUpdate.get();
                existingExpense.setPaid(paid);
                expSecRepository.save(expSec);
                return new ResponseEntity<>("Paid status updated for expense", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Expense not found", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Expense section not found", HttpStatus.NOT_FOUND);
        }
    }

    private String generateUniqueExpenseId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(characters.length());
            builder.append(characters.charAt(index));
        }
        return builder.toString();
    }
}
