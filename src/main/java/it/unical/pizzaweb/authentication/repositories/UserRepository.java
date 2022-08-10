package it.unical.pizzaweb.authentication.repositories;

import it.unical.pizzaweb.authentication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
