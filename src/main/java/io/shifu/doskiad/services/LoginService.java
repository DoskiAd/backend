package io.shifu.doskiad.services;

import io.shifu.doskiad.forms.LoginForm;
import io.shifu.doskiad.transfer.TokenDto;

public interface LoginService {
    TokenDto login(LoginForm loginForm);
}
