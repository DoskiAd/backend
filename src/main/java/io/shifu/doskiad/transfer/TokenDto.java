package io.shifu.doskiad.transfer;

import io.shifu.doskiad.model.Token;

public class TokenDto {
    private String value;
    private String email;
    private String name;

    public static TokenDto from(Token token) {
        TokenDto tokenDto = new TokenDto(token.getValue());
        tokenDto.email = token.getUser().getEmail();
        tokenDto.name = token.getUser().getName();
        return tokenDto;
    }

    private TokenDto(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
