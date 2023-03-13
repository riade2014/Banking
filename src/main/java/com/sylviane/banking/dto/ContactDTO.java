package com.sylviane.banking.dto;

import com.sylviane.banking.models.Contact;
import com.sylviane.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ContactDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String iban;
    private Integer userId;

    public static ContactDTO fromEntity(Contact contact){
        return ContactDTO.builder()
                .id(contact.getId())
                .firstname(contact.getFirstname())
                .lastname(contact.getLastname())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .userId(contact.getUser().getId())
                //.userId(contact.getUser().getId_user())
                .build();
    }

    public static Contact toEntity(ContactDTO contact){
        return Contact.builder()
                .id(contact.getId())
                .firstname(contact.getFirstname())
                .lastname(contact.getLastname())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .user(
                        User.builder()
                                .id(contact.getUserId())
                                //.id_user(contact.getUserId())
                                .build()
                )
                .build();
    }
}
