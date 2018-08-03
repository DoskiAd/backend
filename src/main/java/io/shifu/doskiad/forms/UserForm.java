package io.shifu.doskiad.forms;

import java.util.Objects;

public class UserForm {
    private String firstName;
    private String lastName;
    private String login;
    private String password;

    public UserForm() {
    }

    public UserForm(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserForm userForm = (UserForm) o;
        return Objects.equals(firstName, userForm.firstName) &&
                Objects.equals(lastName, userForm.lastName) &&
                Objects.equals(login, userForm.login) &&
                Objects.equals(password, userForm.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, login, password);
    }
}
