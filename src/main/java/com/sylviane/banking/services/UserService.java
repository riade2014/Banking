package com.sylviane.banking.services;

import com.sylviane.banking.controllers.AuthenticationRequest;
import com.sylviane.banking.dto.AuthenticationResponse;
import com.sylviane.banking.dto.UserDTO;

public interface UserService extends AbstractService<UserDTO>{
    Integer validateAccount(Integer id);
    Integer invalidateAccount(Integer id);

    AuthenticationResponse register(UserDTO user);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
