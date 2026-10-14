package com.sylviane.banking.controllers;

import com.sylviane.banking.dto.AddressDTO;
import com.sylviane.banking.services.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")//permet de donner l'acces au frontend
@RestController
@RequestMapping("/adresses")
@RequiredArgsConstructor
@Tag(name = "address")
public class AddressController {
    private final AddressService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AddressDTO addressDTO){
        return ResponseEntity.ok(service.save(addressDTO));
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{address-id}")
    public ResponseEntity<AddressDTO> findById(
            @PathVariable("address-id") Integer addressId
    ){
        return ResponseEntity.ok(service.findById(addressId));
    }

    @DeleteMapping("/{address-id}")
    public ResponseEntity<Void> delete(@PathVariable("address-id") Integer addressId){
        service.delete(addressId);
        return ResponseEntity.accepted().build();
    }
}
