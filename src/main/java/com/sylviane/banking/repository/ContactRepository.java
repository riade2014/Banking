package com.sylviane.banking.repository;

import com.sylviane.banking.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
    List<Contact> findAllByUserId(Integer userId);
}
