package com.ProjetoLoginLaura.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ProjetoLoginLaura.Entity.Login;
import com.ProjetoLoginLaura.Repository.LoginRepository;

@Service
public class LoginService {
	final private LoginRepository loginRepository;

	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	public List<Login> buscarTodosLogin() {
		return loginRepository.findAll();
	}

	public Login buscarLoginPorId(Long id) {
		Optional<Login> login = loginRepository.findById(id);
		return login.orElse(null);
	}

	public Login salvarLogin(Login atLogin) {
		return loginRepository.save(atLogin);
	}

	public Login atualizarLogin(Long id, Login atLogin) {
		Optional<Login> exeLogin = loginRepository.findById(id);
		if (exeLogin.isPresent()) {
			Login login = exeLogin.get();
			BeanUtils.copyProperties(atLogin, login, "id");
			return loginRepository.save(login);
		}

		return null;

	}

	public Boolean apagarLogin(Long id) {
		Optional<Login> exeLogin = loginRepository.findById(id);
		if (exeLogin.isPresent()) {
			loginRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Login authenticate(String username, String password) {
		Login user = loginRepository.findByUsername(username);

		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
}