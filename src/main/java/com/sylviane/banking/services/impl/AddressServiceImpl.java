package com.sylviane.banking.services.impl;

import com.sylviane.banking.dto.AddressDTO;
import com.sylviane.banking.models.Address;
import com.sylviane.banking.repository.AddressRepository;
import com.sylviane.banking.services.AddressService;
import com.sylviane.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository repository;
    private ObjectsValidator<AddressDTO> validator;

    @Override
    public Integer save(AddressDTO dto) {
        validator.validate(dto);
        Address address = AddressDTO.toEntity(dto);
        return repository.save(address).getId();
    }

    @Override
    public List<AddressDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(AddressDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO findById(Integer id) {
        return repository.findById(id)
                .map(AddressDTO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucune adresse trouvée pour cet ID : "+id));
    }

    @Override
    public void delete(Integer id) {
        //todo on verifie avant la suppression
        repository.deleteById(id);
    }
}
