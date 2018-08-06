package io.shifu.doskiad.forms;

import java.util.Objects;

public class UserForm {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;

    public UserForm() {
    }

    public UserForm(String name, String email, String password, String confirmPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserForm userForm = (UserForm) o;
        return Objects.equals(name, userForm.name) &&
                Objects.equals(email, userForm.email) &&
                Objects.equals(password, userForm.password) &&
                Objects.equals(confirmPassword, userForm.confirmPassword);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, name, email, password, confirmPassword);
    }
}
