package it.unical.pizzaweb.authentication.services;

import it.unical.pizzaweb.authentication.dto.UserDTO;
import it.unical.pizzaweb.authentication.entities.User;
import it.unical.pizzaweb.authentication.repositories.UserRepository;
import it.unical.pizzaweb.errors.exceptions.UsernameAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	@Lazy
	private PasswordEncoder bcryptEncoder;

	private final ModelMapper mapper = new ModelMapper();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public boolean usernameExists(UserDTO user) {
		return userRepository.findByUsername(user.getUsername()) != null;
	}

	public UserDTO save(UserDTO user) throws UsernameAlreadyExistsException {
		if(usernameExists(user)) {
			throw new UsernameAlreadyExistsException(String.format("Username already <%s> exists", user.getUsername()));
		}
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		newUser = userRepository.save(newUser);
		return mapper.map(newUser, UserDTO.class);
	}

}