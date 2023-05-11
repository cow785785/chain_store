package com.example.chain_store.service.ifs;

import com.example.chain_store.vo.ProductRequest;
import com.example.chain_store.vo.ProductResponse;

public interface ProductService {
	public ProductResponse createProduct(ProductRequest req);

	public ProductResponse deleteProduct(ProductRequest req);

	public ProductResponse updateProduct(ProductRequest req);

	public ProductResponse findProductByName(ProductRequest req);

	public ProductResponse findProductByCode(ProductRequest req);
}
