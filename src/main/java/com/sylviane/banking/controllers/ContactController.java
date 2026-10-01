package com.sylviane.banking.controllers;

import com.sylviane.banking.dto.ContactDTO;
import com.sylviane.banking.services.ContactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 4800, allowCredentials = "false")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/contacts")
@RequiredArgsConstructor
@Tag(name = "contact")
public class ContactController {
    private final ContactService service;

    //@PostMapping("/")
    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody ContactDTO contactDTO) {
        return ResponseEntity.ok(service.save(contactDTO));
    }

    //@GetMapping("/")
    @GetMapping("/users/")
    public ResponseEntity<List<ContactDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    //@GetMapping("/users/{contact-id}")
    @GetMapping("/{contact-id}")
    public ResponseEntity<ContactDTO> findById(
            @PathVariable("contact-id") Integer contactId
    ){
        return ResponseEntity.ok(service.findById(contactId));
    }

    //@GetMapping("/users/{user-id}")
    @GetMapping("/{user-id}/")
    public ResponseEntity<List<ContactDTO>> findAllByUserId(
            @PathVariable("user-id") Integer userId
    ){
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }

    @DeleteMapping("/{contact-id}")
    public ResponseEntity<Void> delete(@PathVariable("contact-id") Integer contactId){
        service.delete(contactId);
        return ResponseEntity.accepted().build();
    }
}
