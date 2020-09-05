package sda.spring.mvc.model.dto;

import javax.validation.constraints.Size;

public class UserDTO {
    private Long id;
    //valideaza campurile
    @Size(min = 2, max = 20)
    private String name;
    private String email;
    private String country;

    public Long getId() {
        return id;
    }

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserDTO setCountry(String country) {
        this.country = country;
        return this;
    }
}
