package com.example.simpleecommerce.users.services;

import com.example.simpleecommerce.auth.AuthService;
import com.example.simpleecommerce.auth.exceptions.AuthTokenParsingException;
import com.example.simpleecommerce.users.exceptions.EmailAlreadyInUseException;
import com.example.simpleecommerce.users.exceptions.UserInvalidPasswordException;
import com.example.simpleecommerce.users.exceptions.UserNotFoundException;
import com.example.simpleecommerce.users.models.*;
import com.example.simpleecommerce.users.repositories.UserRepository;
import com.example.simpleecommerce.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthService authService = new AuthService();
    private final EncryptionUtils encryptionUtils = new EncryptionUtils();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfo onLogin(LoginDTO loginDTO) throws UserNotFoundException, UserInvalidPasswordException, AuthTokenParsingException {
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if(user == null)
            throw new UserNotFoundException("Usuário não encontrado");

        if(!encryptionUtils.check(loginDTO.getPassword(), user.getPassword()))
            throw new UserInvalidPasswordException("Senha errada");

        String authToken;
        try {
            authToken = authService.generateAuthToken(user.getId(), user.getRole());
        } catch (Exception e) {
            throw new AuthTokenParsingException("Erro inesperado", e);
        }

        return user.toUserInfo(authToken);
    }

    public UserInfo onCreateUser(UserDTO userDTO) throws EmailAlreadyInUseException {
        User userExists = userRepository.findByEmail(userDTO.getEmail());
        if(userExists != null)
            throw new EmailAlreadyInUseException("Já existe um usuário com este email");
        String encryptedPassword = (encryptionUtils.encrypt(userDTO.getPassword()));
        User user = userRepository.save(userDTO.toUser(encryptedPassword, UserRoles.USER));

        return user.toUserInfo();
    }
}
