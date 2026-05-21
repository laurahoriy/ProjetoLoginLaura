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

import com.ProjetoLoginLaura.Entity.Produtos;
import com.ProjetoLoginLaura.Service.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
	@Autowired
	private final EstoqueService estoqueService;
	public EstoqueController(EstoqueService estoqueService) {
		this.estoqueService = estoqueService;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Produtos> buscarProdutosId(@PathVariable Long id) {
		Produtos produtos = estoqueService.buscarProdutosPorId(id);
		if (produtos != null) {
			return ResponseEntity.ok(produtos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/")
	public ResponseEntity<List<Produtos>> buscarTodosProdutos() {
		List<Produtos> produtos = estoqueService.buscarTodosProdutos();
		return ResponseEntity.ok(produtos);
	}
	@PostMapping("/")
	public ResponseEntity<Produtos> salvaProdutos(@RequestBody Produtos produtos) {
		Produtos saveProdutos = estoqueService.salvarProdutos(produtos);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveProdutos);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Produtos> alteraProdutos(@PathVariable Long id, @RequestBody Produtos produtos) {
		Produtos atualizaProdutos = estoqueService.atualizarProdutos(id, produtos);
		if (atualizaProdutos != null) {
			return ResponseEntity.ok(atualizaProdutos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Produtos> apagaProdutos(@PathVariable Long id) {
		boolean apagaProdutos = estoqueService.apagarProdutos(id);
		if (apagaProdutos) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}