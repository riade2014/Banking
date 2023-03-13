package com.sylviane.banking.services.impl;

import com.sylviane.banking.dto.AccountDTO;
import com.sylviane.banking.exceptions.OperationNonPermittedException;
import com.sylviane.banking.models.Account;
import com.sylviane.banking.repository.AccountRepository;
import com.sylviane.banking.services.AccountService;
import com.sylviane.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final ObjectsValidator <AccountDTO> validator;
    @Override
    public Integer save(AccountDTO dto) {
        //block account update => iban cannot be changed
        /*if(dto.getId() !=null){
            throw new OperationNonPermittedException(
                    "Account cannot be updated",
                    "save account",
                    "Account",
                    "Update not permitted"
            );
        }*/
        validator.validate(dto);
        Account account = AccountDTO.toEntity(dto);
        //boolean userHasAlreadyAnAccount = repository.findByUserId(account.getUser().getId_user()).isPresent();
        boolean userHasAlreadyAnAccount = repository.findByUserId(account.getUser().getId()).isPresent();
        //boolean userHasAlreadyAnAccount = repository.findUserId(account.getUser().getId_user()).isPresent();
        if (userHasAlreadyAnAccount && account.getUser().isActive()){
            throw new OperationNonPermittedException(
                    "l'utilisateur selectionné a déjà un compte",
                    "Create account",
                    "Account service",
                    "Account creation"
            );
        }
        //generer un random iban
        if (dto.getId()== null){
            account.setIban(generateRandomIban());
        }
        //account.setIban(generateRandomIban());
        return repository.save(account).getId() ;
    }

    @Override
    public List<AccountDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(AccountDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO findById(Integer id) {
        return repository.findById(id)
                .map(AccountDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun compte n'a été trouvé pour cet ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        //todo check delete account
        repository.deleteById(id);
    }

    private String generateRandomIban(){
        //generer un random iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();
        //verifier au niveau de la base de doés si on n'a pas un autre compte qui contient le meme Iban
        boolean ibanExist =  repository.findByIban(iban).isPresent();//s'il existe => on genere un nouveau random iban
        if(ibanExist){// sinon => on retourne le iban genéré
            generateRandomIban();
        }
        return iban;
    }
}
