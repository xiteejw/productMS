package com.kart.product.dto;

import javax.validation.constraints.*;

import com.kart.product.entity.Product;

public class ProductDTO {

	String prodid;

	@NotNull(message = "Product name absent")
	@Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)*", message = "Invalid Product Name")
	String productname;
	
	@Min(value=200, message="Minumum value is 200")
	double price;
	
	@Min(value=10,message="Minimum value is 10")
	int stock;
	
	String description;
	String image;
	String sellerid;
	String category;
	String subcategory;
	int productrating;
	
	
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Double getPrice() {
		return price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSellerid() {
		return sellerid;
	}
	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public Integer getProductrating() {
		return productrating;
	}
	public void setProductrating(Integer productrating) {
		this.productrating = productrating;
	}
	
	public ProductDTO() {
		super();
	}
	
	
	
	
	@Override
	public String toString() {
		return "ProductDTO [prodid=" + prodid + ", productname=" + productname + ", price=" + price + ", stock=" + stock
				+ ", description=" + description + ", image=" + image + ", sellerid=" + sellerid + ", category="
				+ category + ", subcategory=" + subcategory + ", productrating=" + productrating + "]";
	}
	
	
			// Converts Entity into DTO
			public static ProductDTO valueOf(Product prod) {
				ProductDTO prodDTO = new ProductDTO();
				prodDTO.setProdid(prod.getProdid());
				prodDTO.setProductname(prod.getProductname());
				prodDTO.setDescription(prod.getDescription());
				prodDTO.setImage(prod.getImage());
				prodDTO.setPrice(prod.getPrice());
				prodDTO.setCategory(prod.getCategory());
				prodDTO.setProductrating(prod.getProductrating());
				prodDTO.setSellerid(prod.getSellerid());
				prodDTO.setStock(prod.getStock());
				prodDTO.setSubcategory(prod.getSubcategory());
				return prodDTO;
			}

			// Converts DTO into Entity
			public Product createEntity() {
				Product prod = new Product();
				prod.setProductname(this.productname);
				prod.setDescription(this.description);
				prod.setImage(this.image);
				prod.setPrice(this.price);
				prod.setCategory(this.category);
			    prod.setProductrating(this.productrating);
				prod.setSellerid(this.sellerid);
				prod.setStock(this.stock);
				prod.setSubcategory(this.subcategory);
				return prod;
			}

}
