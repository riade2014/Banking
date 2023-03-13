package com.sylviane.banking.services.impl;

import com.sylviane.banking.config.JwtUtils;
import com.sylviane.banking.controllers.AuthenticationRequest;
import com.sylviane.banking.dto.AccountDTO;
import com.sylviane.banking.dto.AuthenticationResponse;
import com.sylviane.banking.dto.UserDTO;
import com.sylviane.banking.models.Role;
import com.sylviane.banking.models.User;
import com.sylviane.banking.repository.RoleRepository;
import com.sylviane.banking.repository.UserRepository;
import com.sylviane.banking.services.AccountService;
import com.sylviane.banking.services.UserService;
import com.sylviane.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    //@Autowired
    private final UserRepository repository;
    private final AccountService accountService;
    private final ObjectsValidator<UserDTO> validator;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;
    private final RoleRepository roleRepository;
    private final String ROLE_USER = "ROLE_USER";
    /*public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }*/

    @Override
    public Integer save(UserDTO dto) {
        validator.validate(dto);
        User user = UserDTO.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = repository.save(user);
        //return savedUser.getId_user();
        return savedUser.getId();
    }

    @Override
    public List<UserDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Integer id) {
        return repository.findById(id)
                .map(UserDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun utilisateur trouvé pour cet id : "+ id));
    }

    @Override
    public void delete(Integer id) {
        //todo verifier que l'utilisateur n'a pas un compte avant de delete
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Integer validateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aucun utilisateur n'a été trouvé pour la validation de ce compte"));
        //user.setActive(true);
        //repository.save(user);
        // on crée un compte bancaire pour cette utilisateur
        AccountDTO account = AccountDTO.builder()
                .user(UserDTO.fromEntity(user))
                .build();
        accountService.save(account);
        user.setActive(true);
        repository.save(user);
        //return user.getId_user();
        return user.getId();
    }

    @Override
    @Transactional
    public Integer invalidateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Aucun utilisateur n'a été trouvé pour la validation de ce compte"));
        user.setActive(false);
        repository.save(user);
        //return user.getId_user();
        return user.getId();
    }

    @Override
    @Transactional
    public AuthenticationResponse register(UserDTO dto) {
        validator.validate(dto);
        User user = UserDTO.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(findOrCreateRole(ROLE_USER));
        var saveUser = repository.save(user);
        String token = jwtUtils.generateToken(saveUser);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );//authentification de l'utilisateur
        final UserDetails user = repository.findByEmail(request.getEmail()).get();
        /*Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("fullName", user.getFirstname() + " " + user.getLastname());
        final String token = jwtUtils.generateToken(user, claims);*/
        final String token = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private Role findOrCreateRole(String roleName){
        Role role = roleRepository.findByName(roleName)
                .orElse(null);
        if(role == null){
            return roleRepository.save(
                    Role.builder()
                            .name(roleName)
                            .build()
            );
        }
        return role;
    }
}
