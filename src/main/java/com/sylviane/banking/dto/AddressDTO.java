package com.sylviane.banking.dto;

import com.sylviane.banking.models.Address;
import com.sylviane.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AddressDTO {
    private Integer id;
    private String street;
    private Integer zipCode;
    private String city;
    private String country;
    private Integer userId;

    public static AddressDTO fromEntity(Address address){
        return AddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .userId(address.getUser().getId())
                .build();
    }
    public static Address toEntity(AddressDTO address){
        return Address.builder()
                .id(address.getId())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .user(
                        User.builder()
                                .id(address.getUserId())
                                //.id_user(address.getUserId())
                                .build()
                        )
                .build();
    }
}
