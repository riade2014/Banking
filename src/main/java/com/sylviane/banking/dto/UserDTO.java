package com.sylviane.banking.dto;

import com.sylviane.banking.models.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    @NotNull(message = "Veuillez renseigner un prenom!!")//le firstname ne doit pas être null
    @NotEmpty(message = "le nom ne doit pas etre vide")// ne doit pas être vide
    @NotBlank(message = "le nom ne doit pas contenir des blancs (' ')")// ne doit pas contenir des blancs (' ')
    private String firstname;
    @NotNull(message = "Veuillez renseigner un nom!!")
    @NotEmpty(message = "le nom ne doit pas etre vide")
    @NotBlank(message = "le nom ne doit pas contenir des blancs (' ')")
    private String lastname;
    @NotNull(message = "Veuillez renseigner un mail!!")
    @NotEmpty(message = "le mail ne doit pas etre vide")
    @NotBlank(message = "le mail ne doit pas contenir des blancs (' ')")
    @Email(message = "Le mail n'est pas conforme")//evite d'ecrire un regex pour definir comment le mail doit s'afficher
    private String email;
    @NotNull(message = "Veuillez renseigner un mot de passe!!")
    @NotEmpty(message = "le mot de passe ne doit pas etre vide")
    @NotBlank(message = "le mot de passe ne doit pas contenir des blancs (' ')")
    @Size(min = 8, max = 16,message = "le mot de passe doit contenir entre 8 et 16 caracteres")
    private String password;
    public static UserDTO fromEntity(User user){
        //null check
        //verifie si l'objet est null ou non pour eviter les pointeurs nulls dans l'application
        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User toEntity(UserDTO user){//transformer un UserDTO vers objet User
        //null check
        //verifie si l'objet est null ou non pour eviter les pointeurs nulls dans l'application
        return User.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
