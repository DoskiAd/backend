package io.shifu.doskiad.controller;

import io.shifu.doskiad.forms.LoginForm;
import io.shifu.doskiad.forms.UserForm;
import io.shifu.doskiad.services.LoginService;
import io.shifu.doskiad.services.UserService;
import io.shifu.doskiad.transfer.TokenDto;
import io.shifu.doskiad.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final LoginService loginService;
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public UserController(LoginService loginService, UserService userService, UserValidator userValidator) {
        this.loginService = loginService;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginForm loginForm) {
        return ResponseEntity.ok(loginService.login(loginForm));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserForm userForm) {
        String answer = userValidator.validate(userForm);

        if (answer != null) {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        } else {
            userService.signUp(userForm);
            return ResponseEntity.ok("success");
        }
    }
}
