package com.ProjetoLoginLaura.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjetoLoginLaura.Entity.Login;
import com.ProjetoLoginLaura.Entity.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
	Login findByUsername(String username);
}
