package com.example.demo.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product createProduct(Product p) {
		return productRepository.save(p);
		
	}

	@Override
	public void updateProduct(Product p) {
		productRepository.save(p);
	}

	@Override
	public void deleteProduct(long id) {
		productRepository.delete(id);
		
	}

	@Override
	public Page<Product> getProducts(Integer page, Integer limit) {
		Pageable p = new PageRequest(page, limit);
		return productRepository.findAll(p);///(List<Product>) productRepository.findAll(p);
	}

	@Override
	public Product findById(long id) {
		return productRepository.findOne(id);
	}

}
