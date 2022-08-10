package com.example.demo.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	public Page<ProductDTO> find(PageRequest pageRequest) {
		// É o page que traz o resultado elaborado (as informações de paginação do final)
		// A classe Product é gerenciada pelo JPA
		// Para isolar a camada, utilizamos um ProductDTO
		// Tira o monitoramento da JPA, independente da conexão do banco
		Page<Product> page = repository.findAll(pageRequest);
		// Transformando a página em lista (Mapa de identidade)
		repository.findProductsCategories(page.stream().collect(Collectors.toList()));
		
		return page.map(ProductDTO::new);
	}
}
