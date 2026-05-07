package com.ProjetoLoginLaura.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoLoginLaura.Entity.Login;
import com.ProjetoLoginLaura.Service.LoginService;

@RestController
@RequestMapping("/users")
public class LoginController {
	@Autowired
	private final LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Login> buscarLoginId(@PathVariable Long id) {
		Login login = loginService.buscarLoginPorId(id);
		if (login != null) {
			return ResponseEntity.ok(login);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Login>> buscarTodosLogin() {
		List<Login> login = loginService.buscarTodosLogin();
		return ResponseEntity.ok(login);
	}

	@PostMapping("/")
	public ResponseEntity<Login> salvaLogin(@RequestBody Login login) {
		Login saveLogin = loginService.salvarLogin(login);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveLogin);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Login> alteraLogin(@PathVariable Long id, @RequestBody Login login) {
		Login atualizaLogin = loginService.atualizarLogin(id, login);
		if (atualizaLogin != null) {
			return ResponseEntity.ok(atualizaLogin);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Login> apagaLogin(@PathVariable Long id) {
		boolean apagaLogin = loginService.apagarLogin(id);
		if (apagaLogin) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/auth")
	public ResponseEntity<Login> authenticate(@RequestBody Login loginDetails) {
		Login authenticatedUser = loginService.authenticate(loginDetails.getUsername(), loginDetails.getPassword());

		if (authenticatedUser != null) {
			authenticatedUser.setPassword(null);
			return ResponseEntity.ok(authenticatedUser);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}