package com.example.demo.product;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ProductRessource {

	 @Autowired
	 private HibernateSearchService searchService;
	 
	 @Autowired
	 private ProductService productService;
	 
	 @CrossOrigin
	 @GetMapping("/products")
	 public Page<Product> getProducts(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			 						@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) 
	 {
		 return productService.getProducts(page, limit);
	 }
	 
	 @CrossOrigin
	 @GetMapping("/searchproducts")
	 public Page<Product> searchByName(@RequestParam(value = "search", required = false) String q,
			 							@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			 							@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit)
	 {
		 List<Product> searchResults = null;
		 
		 try {
			 searchResults = searchService.fuzzySearch(q);
		 }catch (Exception ex) {
			 //TODO
	     }
		 Page<Product> result = new PageImpl<Product>(searchResults, new PageRequest(page.intValue(), limit.intValue(), null), searchResults.size());
		 
		 return result;
	 }
	 
	 @CrossOrigin
	 @DeleteMapping("/products/{id}")
	 public void deleteProduct(@PathVariable long id) {
		 productService.deleteProduct(id);
	 }
	 
	 @CrossOrigin
	 @PostMapping("/products")
	 public ResponseEntity<Object> createProduct(@RequestBody Product product) {
			Product savedProduct = productService.createProduct(product);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedProduct.getId()).toUri();

			return ResponseEntity.created(location).build();

	 }
	 
	 @CrossOrigin
	 @PostMapping("/products/{id}")
	 public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable long id) {
			Product productOptional = productService.findById(id);

			if (productOptional == null)
				return ResponseEntity.notFound().build();

			product.setId(id);
			
			productService.updateProduct(product);

			return ResponseEntity.noContent().build();

	 }
	 
	 
}
