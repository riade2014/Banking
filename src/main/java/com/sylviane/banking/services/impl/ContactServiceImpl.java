package com.sylviane.banking.services.impl;

import com.sylviane.banking.dto.ContactDTO;
import com.sylviane.banking.models.Contact;
import com.sylviane.banking.repository.ContactRepository;
import com.sylviane.banking.services.ContactService;
import com.sylviane.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository repository;
    private final ObjectsValidator<ContactDTO> validator;
    @Override

    public Integer save(ContactDTO dto) {
        validator.validate(dto);
        Contact contact = ContactDTO.toEntity(dto);
        return repository.save(contact).getId();
    }

    @Override
    public List<ContactDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(ContactDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDTO findById(Integer id) {
        return repository.findById(id)
                .map(ContactDTO::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucun contact trouvé pour cet iD : "+id));
    }

    @Override
    public void delete(Integer id) {
        //todo on verifie avant
        repository.deleteById(id);
    }

    @Override
    public List<ContactDTO> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(ContactDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
