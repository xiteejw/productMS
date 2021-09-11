package com.kart.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kart.product.entity.subscribedProduct;

public interface subscribedProductRepository extends JpaRepository<subscribedProduct, String> {

	Optional<subscribedProduct> findByProdidAndBuyerid(String prodid, String buyerid);

}
