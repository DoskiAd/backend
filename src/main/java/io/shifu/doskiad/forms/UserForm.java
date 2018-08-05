package io.shifu.doskiad.forms;

import java.util.Objects;

public class UserForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;

    public UserForm() {
    }

    public UserForm(String firstName, String lastName, String email, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return Objects.equals(firstName, userForm.firstName) &&
                Objects.equals(lastName, userForm.lastName) &&
                Objects.equals(email, userForm.email) &&
                Objects.equals(password, userForm.password) &&
                Objects.equals(confirmPassword, userForm.confirmPassword);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, email, password, confirmPassword);
    }
}
