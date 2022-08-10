package it.unical.pizzaweb.authentication.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unical.pizzaweb.entities.Purchase;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
    private List<Purchase> purchases;

}
