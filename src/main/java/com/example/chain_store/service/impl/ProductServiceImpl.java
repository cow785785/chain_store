package com.example.chain_store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.chain_store.entity.Product;
import com.example.chain_store.repository.ProductDao;
import com.example.chain_store.service.ifs.ProductService;
import com.example.chain_store.vo.request.ProductRequest;
import com.example.chain_store.vo.response.ProductResponse;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

//	新增商品
	@Override
	public ProductResponse createProduct(ProductRequest req) {
		Product product = req.getProduct();
//		ID自動生成

//		防呆 
//		商品代碼 是否不為空 
		if (!StringUtils.hasText(product.getProductCode())) {
			return new ProductResponse("請輸入商品代碼!");
		}
//		商品代碼 是否已存在
		if (productDao.findByProductCode(product.getProductCode()) != null) {
			return new ProductResponse("商品代碼已存在!");
		}
// 		名稱 是否不為空
		if (!StringUtils.hasText(product.getProductName())) {
			return new ProductResponse("請輸入商品名稱!");
		}
//		價錢 	是否不為空
		if (product.getPrice() == null) {
			return new ProductResponse("請輸入商品價格!");
		}
//		價錢 	是否不小於0
		if (product.getPrice() <= 0) {
			return new ProductResponse("商品價格不得小於0!");
		}
//		分類 	是否不為空
		if (!StringUtils.hasText(product.getCategory())) {
			return new ProductResponse("請輸入商品分類!");
		} // 圖片 是否不為空
		if (product.getProductImg() == null) {
			return new ProductResponse("請上傳圖片!");
		}
//		商品簡述 	是否不為空
		if (!StringUtils.hasText(product.getProductInfo())) {
			return new ProductResponse("請輸入商品簡述!");
		}
//		商品詳細描述 	是否不為空
		if (!StringUtils.hasText(product.getProductDescribe())) {
			return new ProductResponse("請輸入商品詳細描述!");
		}
		productDao.save(product);
		return new ProductResponse("新增商品成功!");
	}

//	刪除商品(使用商品代碼來刪除)
	@Override
	public ProductResponse deleteProduct(ProductRequest req) {
		if (!StringUtils.hasText(req.getProductCode())) {
			return new ProductResponse("請輸入商品代碼!");
		}
		Product product = productDao.findByProductCode(req.getProductCode());
		if (product == null) {
			return new ProductResponse("找不到此商品代碼!");
		}
		productDao.delete(product);
		return new ProductResponse("刪除商品成功!");
	}

//	更新商品內容
	@Override
	public ProductResponse updateProduct(ProductRequest req) {

//		此次想更正的商品資料；商品代碼不得修改
		Product reqProduct = req.getProduct();

//		防呆 
//		商品代碼 是否為空 
		if (!StringUtils.hasText(reqProduct.getProductCode())) {
			return new ProductResponse("請輸入商品代碼!");
		}

//		商品代碼 是否已存在
		if (productDao.findByProductCode(reqProduct.getProductCode()) == null) {
			return new ProductResponse("商品代碼錯誤!");
		}

//		從資料庫取出要修改的商品
		Product updatedProduct = productDao
				.findByProductCode(reqProduct.getProductCode());

// 		名稱 是否不為空
		if (!StringUtils.hasText(updatedProduct.getProductName())) {
			return new ProductResponse("請輸入商品名稱!");
		} else {
			updatedProduct.setProductName(reqProduct.getProductName());
		}

//		價錢 	是否不為空
		if (reqProduct.getPrice() == null) {
			return new ProductResponse("請輸入商品價格!");
		}
//		價錢 	是否不小於0
		if (reqProduct.getPrice() <= 0) {
			return new ProductResponse("商品價格不得小於0!");
		} else {
			updatedProduct.setPrice(reqProduct.getPrice());
		}

//		分類 	是否不為空
		if (!StringUtils.hasText(updatedProduct.getCategory())) {
			return new ProductResponse("請輸入商品分類!");
		} else {
			updatedProduct.setCategory(reqProduct.getCategory());
		}
//		圖片 	是否不為空
		if (reqProduct.getProductImg() == null) {
			return new ProductResponse("請上傳圖片!");
		} else {
			updatedProduct.setProductImg(reqProduct.getProductImg());
		}
//		商品簡述 	是否不為空
		if (!StringUtils.hasText(reqProduct.getProductInfo())) {
			return new ProductResponse("請輸入商品簡述!");
		} else {
			updatedProduct.setProductInfo(reqProduct.getProductInfo());
		}
//		商品詳細描述 	是否不為空
		if (!StringUtils.hasText(reqProduct.getProductDescribe())) {
			return new ProductResponse("請輸入商品詳細描述!");
		} else {
			updatedProduct.setProductDescribe(reqProduct.getProductDescribe());
		}

//		確認無誤修改資料
		productDao.save(updatedProduct);
		return new ProductResponse("更新商品成功!");

	}

//	以商品名稱找商品
	@Override
	public ProductResponse findProductByName(ProductRequest req) {
		if (!StringUtils.hasText(req.getProductName())) {
			return new ProductResponse("請輸入商品名稱!");
		}
		List<Product> productList = productDao.findByProductName(req.getProductName());
//		確認是否有該商品名稱
		if (productList.size() == 0) {
			return new ProductResponse("找無此商品!");
		}
//		查詢成功
		return new ProductResponse(productList);
	}

//	以商品代碼找商品
	@Override
	public ProductResponse findProductByCode(ProductRequest req) {
		if (!StringUtils.hasText(req.getProductCode())) {
			return new ProductResponse("請輸入商品代碼!");
		}
		Product product = productDao.findByProductCode(req.getProductCode());
//		商品代碼 是否已存在
		if (product == null) {
			return new ProductResponse("找無此商品!");
		}
//		查詢成功
		return new ProductResponse(product);
	}

//	以分類查找商品
	@Override
	public ProductResponse findProductByCategory(ProductRequest req) {
//		如果沒輸入分類
		if (!StringUtils.hasText(req.getCategory())) {
			return new ProductResponse("請輸入分類名稱");
		}
		List<Product> productList = productDao.findByCategory(req.getCategory());
		if (productList.size() == 0) {
			return new ProductResponse("找無此分類商品");
		}
		return new ProductResponse(productList);
	}

//	找所有商品
	@Override
	public List<Product> findAllProduct() {
		return productDao.findAll();
	}

//	用分類or名字找商品
	@Override
	public ProductResponse findProductByNameOrCategory(ProductRequest req) {
		List<Product> searchList = productDao.findByNameOrCategory(req.getSearchName());
		if (searchList.size() == 0) {
			return new ProductResponse("找無此商品");
		}
		return new ProductResponse(searchList);

	}
}
