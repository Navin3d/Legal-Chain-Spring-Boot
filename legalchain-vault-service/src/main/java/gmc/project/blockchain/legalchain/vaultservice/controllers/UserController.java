package gmc.project.blockchain.legalchain.vaultservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.blockchain.legalchain.vaultservice.models.UserRecordsModel;
import gmc.project.blockchain.legalchain.vaultservice.services.UserService;

@RequestMapping(path = "/user")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/docs/{userName}")
	public ResponseEntity<UserRecordsModel> getUserRecords(@PathVariable String userName) {
		UserRecordsModel returnValue = userService.getUserRecords(userName);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}

}
