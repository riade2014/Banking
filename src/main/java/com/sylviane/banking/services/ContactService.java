package com.sylviane.banking.services;

import com.sylviane.banking.dto.ContactDTO;

import java.util.List;

public interface ContactService extends AbstractService<ContactDTO>{
    List<ContactDTO> findAllByUserId(Integer userId);
}
