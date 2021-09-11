package com.kart.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kart.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

	public Optional<Product> findByproductname(String productname);
	public List<Product> findAll();
	public List<Product> findByCategory(String category);
	public Optional<Product> findById(String prodid);
	public Object getByProdid(String string);
	public Optional<Product> findByProductnameAndSellerid(String productname, String sellerid);
	
}
