package com.kart.product.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.kart.product.dto.ProductDTO;
import com.kart.product.dto.subscribedProductDTO;
import com.kart.product.entity.Product;
import com.kart.product.entity.subscribedProduct;
import com.kart.product.exception.KartException;
import com.kart.product.repository.ProductRepository;
import com.kart.product.repository.subscribedProductRepository;

@Service
@Transactional
public class ProductService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductRepository productRepo;

	@Autowired
	subscribedProductRepository subRepo;

	//create new product
	public String createProduct(String sellerid, ProductDTO prodDTO) throws KartException{
		Optional<Product> optional = productRepo.findByProductnameAndSellerid(prodDTO.getProductname(), sellerid);
		if(optional.isPresent())
		{
			return "Product already exists";
		}
		else {
		logger.info("Creation request for Product {}", prodDTO);
		prodDTO.setSellerid(sellerid);
		Product product1 = prodDTO.createEntity();
		Product product2 = productRepo.save(product1);
		return product2.getProdid();
		}
	}

	// Fetches product details of a specific product
	public ProductDTO getProductDetails(@PathVariable String productname) throws KartException{
		Optional<Product> optional = productRepo.findByproductname(productname);
		Product product = optional.orElseThrow(() -> new KartException("Invalid Product Name"));
		logger.info("Product Details request for Product {}", productname);
		ProductDTO productDTO = new ProductDTO();
		productDTO = ProductDTO.valueOf(product);
		return productDTO;
	}

	//Fetches products by category
	public List<ProductDTO> getProductByCategory(@PathVariable String category) throws KartException {
		Iterable<Product> products = productRepo.findByCategory(category);
		List<ProductDTO> productDTOs = new ArrayList<>();

		for (Product prod : products) {
			ProductDTO productDTO = ProductDTO.valueOf(prod);
			productDTOs.add(productDTO);
		}
		if (productDTOs.isEmpty())
			throw new KartException("No Products with given category");
		logger.info("Product details : {}", productDTOs);
		return productDTOs;
	}

	// Fetches all product details
	public List<ProductDTO> getAllProducts() throws KartException{
		Iterable<Product> products = productRepo.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();

		for (Product prod : products) {
			ProductDTO productDTO = ProductDTO.valueOf(prod);
			productDTOs.add(productDTO);
		}
		if (productDTOs.isEmpty())
			throw new KartException("No Products are available");
		logger.info("Product details : {}", productDTOs);
		return productDTOs;
	}

	//update product stock
	public String updateProductStock(String prodid, int sto) throws KartException{
		Optional<Product> product = productRepo.findById(prodid);
		Product p = product.orElseThrow(() -> new KartException("Invalid Product Id"));
			int stock = p.getStock() + sto;
			p.setStock(stock);
			return "Updated";
		
	}

	//Delete product details
	public String deleteProduct(String sellerid, String prodid)throws KartException{
		Optional<Product> product = productRepo.findById(prodid);
		Product p = product.orElseThrow(() -> new KartException("Invalid Product Id"));
		if(p.getSellerid().equals(sellerid))
		{
			productRepo.deleteById(prodid);
			return "Product deleted successfully";
		}
		else {
			return "You are not authorised to delete this product";
		}
	}

	//Get all subscribed Products
	public List<subscribedProductDTO> getAllSubscribedProducts() throws KartException{
		Iterable<subscribedProduct> sub = subRepo.findAll();
		List<subscribedProductDTO> subDTOs = new ArrayList<>();

		for (subscribedProduct prod : sub) {
			subscribedProductDTO subDTO = subscribedProductDTO.valueOf(prod);
			subDTOs.add(subDTO);
		}
		if (subDTOs.isEmpty())
			throw new KartException("No Products are available");
		logger.info("Product details : {}", subDTOs);
		return subDTOs;
	}

	//Create subscribed product
	public String createSubscribedProduct(subscribedProductDTO subDTO) {
		subscribedProduct subp = subDTO.createEntity();
		subRepo.save(subp);
		return "success";
	}

	//Get product by id
	public ProductDTO getProductByid(String prodid) throws KartException{
		Optional<Product> product10 = productRepo.findById(prodid);
		Product p10 = product10.orElseThrow(() -> new KartException("Invalid Product Id"));
		ProductDTO prodDTO10 = ProductDTO.valueOf(p10);
		return prodDTO10;
	}

}
