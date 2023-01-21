package uz.tech4ecobackend.entity.dto;

import uz.tech4ecobackend.entity.Role;

import java.util.Date;
import java.util.Set;


public class UserDTO {
    private Long id;
    private String name;
    private String lastName;
    private String password;
    private String login;
    private String email;
    private Date dateOfBirth;

    private Set<Role> roles;

    public UserDTO(Long id, String name, String lastName, String password, String login, String email, Date dateOfBirth, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
