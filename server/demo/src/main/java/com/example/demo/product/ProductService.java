package com.example.demo.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface ProductService {

	Product createProduct(Product p);
	void updateProduct(Product p);
	void deleteProduct(long id);
	Page<Product> getProducts(Integer page, Integer limit);
	Product findById(long id);
}
