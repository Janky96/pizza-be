package it.unical.pizzaweb.authentication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {

    private String username;
    private String password;
    private String role;

}
