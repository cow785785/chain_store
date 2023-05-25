package com.example.chain_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chain_store.entity.Product;
import com.example.chain_store.service.ifs.ProductService;
import com.example.chain_store.vo.request.ProductRequest;
import com.example.chain_store.vo.response.ProductResponse;

@CrossOrigin
@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/create_product")
	public ProductResponse createProduct(@RequestBody ProductRequest req) {
		return productService.createProduct(req);
	}

	@PostMapping("/delete_product")
	public ProductResponse deleteProduct(@RequestBody ProductRequest req) {
		return productService.deleteProduct(req);
	}

	@PostMapping("/update_product")
	public ProductResponse updateProduct(@RequestBody ProductRequest req) {
		return productService.updateProduct(req);
	}

	@PostMapping("/find_product_by_name")
	public ProductResponse findProductByName(@RequestBody ProductRequest req) {
		return productService.findProductByName(req);
	}

	@PostMapping("/find_product_by_code")
	public ProductResponse findProductByCode(@RequestBody ProductRequest req) {
		return productService.findProductByCode(req);
	}

	@PostMapping("/find_product_by_category")
	public ProductResponse findProductByCategory(@RequestBody ProductRequest req) {
		return productService.findProductByCategory(req);
	}

	@RequestMapping("/find_all_product")
	public List<Product> findAllProduct() {
		return productService.findAllProduct();
	}

	@PostMapping("/find_product_by_name_or_category")
	public ProductResponse findProductByNameOrCategory(@RequestBody ProductRequest req) {
		return productService.findProductByNameOrCategory(req);
	}

}
