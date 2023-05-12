package com.example.chain_store.contrtoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.chain_store.entity.Product;
import com.example.chain_store.service.ifs.ProductService;
import com.example.chain_store.vo.ProductRequest;
import com.example.chain_store.vo.ProductResponse;

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

	@PostMapping("/find_all_product")
	public List<Product> findAllProduct(@RequestBody ProductRequest req) {
		return productService.findAllProduct(req);
	}

}
