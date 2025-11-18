package com.example.auth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth.dtos.ProductDTO;
import com.example.auth.entities.Product;
import com.example.auth.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Transactional
	public List<ProductDTO> getAll() {
		List<Product> list = repository.findAll();
		return list.stream().map(x-> new ProductDTO(x.getName(), x.getPrice())).toList();
	}
	
	@Transactional
	public ProductDTO register(ProductDTO dto) {
		Product entity = repository.save(new Product(dto.name(), dto.price()));
		return new ProductDTO(entity.getName(), entity.getPrice());
	}
}
