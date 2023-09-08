package gmc.project.blockchain.legalchain.authservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.blockchain.legalchain.authservice.models.UserModel;
import gmc.project.blockchain.legalchain.authservice.services.AuthService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(path = "/auth")
@RestController
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping(path = "/signup")
	private ResponseEntity<String> createUser(@RequestBody UserModel user) {
		authService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created user.");
	}

}
