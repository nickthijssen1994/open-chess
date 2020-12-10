package nl.nickthijssen.restserver.controllers;

import nl.nickthijssen.restserver.datamodels.Token;
import nl.nickthijssen.restserver.datamodels.User;
import nl.nickthijssen.restserver.repositories.TokenRepository;
import nl.nickthijssen.restserver.repositories.UserRepository;
import nl.nickthijssen.restshared.messages.requests.*;
import nl.nickthijssen.restshared.messages.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenRepository tokenRepository;

	@GetMapping("/user")
	public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
		System.out.println(principal);
		return Collections.singletonMap("name", principal.getAttribute("name"));
	}

	@GetMapping(path = "/user/oauth")
	public PrincipalResult showPrincipal(Principal principal) {
		PrincipalResult result = new PrincipalResult();
		Object principalObject = principal;
		if (principal != null) {
			result.setSuccess(true);
			result.setMessage("Successful OAuth");
			result.setPrincipal(principalObject.toString());
		}
		else {
			result.setSuccess(false);
			result.setMessage("Unsuccessful OAuth");
			result.setPrincipal(null);
		}

		return result;
	}

	@GetMapping(path = "/user/all/online")
	public List<String> getAllOnlineUsers() {
		List<String> onlineUsers = new ArrayList<>();
		Iterable<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getIsOnline()) {
				onlineUsers.add(user.getUsername());
			}
		}
		return onlineUsers;
	}

	@PostMapping(path = "/user/register")
	public RegistrationResult registerUser(@RequestBody RegistrationRequest registrationRequest) {
		RegistrationResult result = new RegistrationResult();
		if (registrationRequest.getUsername().isBlank() || registrationRequest.getEmail().isBlank() || registrationRequest.getPassword().isBlank() || registrationRequest.getConfirmPassword().isBlank()) {
			result.setSuccess(false);
			result.setMessage("Missing Information");
			return result;
		}
		else if (!registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())) {
			result.setSuccess(false);
			result.setMessage("Passwords are not the same");
			return result;
		}
		else if (userRepository.findByUsername(registrationRequest.getUsername()).isPresent()) {
			result.setSuccess(false);
			result.setMessage("Username already in use");
			return result;
		}
		else {
			User user = new User();
			user.setUsername(registrationRequest.getUsername());
			user.setEmail(registrationRequest.getEmail());
			user.setPassword(registrationRequest.getPassword());
			user.setRegistrationDate(new Date());
			user.setIsOnline(false);
			userRepository.save(user);
			result.setSuccess(true);
			result.setMessage("Successfully Registered");
			result.setUsername(registrationRequest.getUsername());
			return result;
		}
	}

	@PostMapping(path = "/user/login")
	public LoginResult loginUser(@RequestBody LoginRequest loginRequest) {
		LoginResult result = new LoginResult();
		if (loginRequest.getUsername().isBlank() || loginRequest.getPassword().isBlank()) {
			result.setSuccess(false);
			result.setMessage("Missing Information");
		}
		else if (!userRepository.findByUsernameAndPassword(loginRequest.getUsername(),
														   loginRequest.getPassword()).isPresent()) {
			result.setSuccess(false);
			result.setMessage("Combination of username and password is wrong");
		}
		else if (!tokenRepository.findByUsername(loginRequest.getUsername()).isPresent()) {
			Token token = generateNewToken(loginRequest.getUsername());
			tokenRepository.save(token);
			result.setSuccess(true);
			result.setMessage("Successfully Logged In");
			result.setUsername(loginRequest.getUsername());
			result.setToken(token.getUuid());
		}
		else {
			Token token = tokenRepository.findByUsername(loginRequest.getUsername()).get();
			if (!validateToken(token)) {
				tokenRepository.deleteById(token.getId());
				token = generateNewToken(loginRequest.getUsername());
				tokenRepository.save(token);
			}
			result.setSuccess(true);
			result.setMessage("Successfully Logged In");
			result.setUsername(loginRequest.getUsername());
			result.setToken(token.getUuid());
		}
		return result;
	}

	@PostMapping(path = "/authenticate")
	public AuthenticationResult authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
		AuthenticationResult result = new AuthenticationResult();
		if (!tokenRepository.findByUuid(authenticationRequest.getToken()).isPresent()) {
			result.setSuccess(false);
			result.setMessage("Authentication Failed");
		}
		else if (!validateToken(tokenRepository.findByUuid(authenticationRequest.getToken()).get())) {
			result.setSuccess(false);
			result.setMessage("Session Expired");
		}
		else {
			result.setSuccess(true);
			result.setMessage("Successfully Authenticated");
		}
		return result;
	}

	private Token generateNewToken(String username) {
		Token token = new Token();
		token.setUuid(UUID.randomUUID().toString());
		token.setCreationDate(new Date());
		token.setTimeToLive(60);
		token.setUsername(username);
		return token;
	}

	private boolean validateToken(Token token) {
		Date creationDate = token.getCreationDate();
		Date currentDate = new Date();
		Date dateToLive = new Date(creationDate.getTime() + (token.getTimeToLive() * 60000));
		return dateToLive.after(currentDate);
	}
}
