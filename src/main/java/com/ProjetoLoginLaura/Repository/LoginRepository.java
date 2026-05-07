package com.ProjetoLoginLaura.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjetoLoginLaura.Entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {
	Login findByUsername(String username);
}
