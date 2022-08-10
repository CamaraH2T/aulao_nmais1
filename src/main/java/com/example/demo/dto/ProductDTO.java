package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entities.Product;

public class ProductDTO {

	private Long id;
	private String name;
	
	// Associação com lista de categoria DTO
	private List<CategoryDTO> categories = new ArrayList<>();
	
	public ProductDTO() {
	}

	public ProductDTO(Long id, String name, List<CategoryDTO> categories) {
		super();
		this.id = id;
		this.name = name;
		this.categories = categories;
	}
	
	// Converte um Product para ProductDTO
	public ProductDTO(Product product) {
		id = product.getId();
		name = product.getName();
		
		// Poderia ser feito como categories = product.getCategories().stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		// Utilizado para buscar as categorias do Produto. O JPA por padrão, quando se tem um objeto
		// e associado a ele pode ter vários outros objetos. Estes vários, por padrão, não são carregados
		// (lazy loading)
		categories = product.getCategories().stream().map(CategoryDTO::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}
}
