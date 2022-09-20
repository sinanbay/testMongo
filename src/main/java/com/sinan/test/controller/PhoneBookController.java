package com.sinan.test.controller;

import com.sinan.test.exception.PhoneCardAlreadyExistsException;
import com.sinan.test.exception.PhoneCardNotFoundException;
import com.sinan.test.model.PhoneCard;
import com.sinan.test.repository.IPhoneCardRepository;
import com.sinan.test.service.PhoneCardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CONFLICT;

@RestController
@RequestMapping("/phoneBook")
@AllArgsConstructor
public class PhoneBookController {


    private final PhoneCardService pcService;


    @GetMapping
    public ResponseEntity<List<PhoneCard>> getPhoneCards(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(pcService.getPhoneCards(name), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneCard> getPhoneCard(@PathVariable String id) {
        return new ResponseEntity<>(pcService.getPhoneCardById(id), OK);
    }

    @PostMapping
    public ResponseEntity<PhoneCard> createPhoneCard(@RequestBody PhoneCard newPhoneCard) {
        return new ResponseEntity<>(pcService.createPhoneCard(newPhoneCard), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePhoneCard(@PathVariable String id,
                                      @RequestBody PhoneCard newPhoneCard) {
        pcService.updatePhoneCard(id, newPhoneCard);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoneCard(@PathVariable String id) {
        pcService.deletePhoneCard(id);
        return new ResponseEntity<>(OK);
    }

    @ExceptionHandler(PhoneCardNotFoundException.class)
    public ResponseEntity<String> handlePhoneCardNotNotFoundException(PhoneCardNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(PhoneCardAlreadyExistsException.class)
    public ResponseEntity<String> handlePhoneCardNotIlAlreadyExistsException(PhoneCardAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }
}
