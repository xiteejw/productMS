package com.kart.product.dto;

import com.kart.product.entity.subscribedProduct;

public class subscribedProductDTO {

	String buyerid;
	String prodid;
	int quantity;
	
	public String getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// Converts Entity into DTO
	public static subscribedProductDTO valueOf(subscribedProduct product) {
		subscribedProductDTO subscribedprodDTO = new subscribedProductDTO();
		subscribedprodDTO.setBuyerid(product.getBuyerid());
		subscribedprodDTO.setProdid(product.getProdid());
		subscribedprodDTO.setQuantity(product.getQuantity());
		return subscribedprodDTO;
	}

	//create new entity
	public subscribedProduct createEntity() {
		subscribedProduct sub = new subscribedProduct();
		sub.setBuyerid(this.buyerid);
		sub.setProdid(this.prodid);
		sub.setQuantity(this.quantity);
		return sub;
	}

	@Override
	public String toString() {
		return "subscribedprodDTO [buyerid=" + buyerid + ", prodid=" + prodid + ", quantity=" + quantity + "]";
	}
}

