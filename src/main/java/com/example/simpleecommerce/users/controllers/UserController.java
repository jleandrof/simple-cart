package com.example.simpleecommerce.users.controllers;

import com.example.simpleecommerce.users.exceptions.EmailAlreadyInUseException;
import com.example.simpleecommerce.users.exceptions.UserInvalidPasswordException;
import com.example.simpleecommerce.users.exceptions.UserNotFoundException;
import com.example.simpleecommerce.users.models.LoginDTO;
import com.example.simpleecommerce.users.models.UserDTO;
import com.example.simpleecommerce.users.services.UserService;
import com.example.simpleecommerce.utils.results.ErrorResult;
import com.example.simpleecommerce.utils.results.SuccessfullResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity onCreateUser(@RequestBody UserDTO userDTO) {
        try {
            return new ResponseEntity(new SuccessfullResult(userService.onCreateUser(userDTO)), HttpStatus.OK);
        } catch (EmailAlreadyInUseException e) {
            return new ResponseEntity(new ErrorResult(e.getMessage()), HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResult(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity onUserLogin(@RequestBody LoginDTO login) {
        try {
            return new ResponseEntity(new SuccessfullResult(userService.onLogin(login)), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity(new ErrorResult(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (UserInvalidPasswordException e) {
            return new ResponseEntity(new ErrorResult(e.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResult(), HttpStatus.CONFLICT);
        }
    }
}
