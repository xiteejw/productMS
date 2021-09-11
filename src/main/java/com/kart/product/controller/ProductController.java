package com.kart.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kart.product.dto.BuyerDTO;
import com.kart.product.dto.ProductDTO;
import com.kart.product.dto.subscribedProductDTO;
import com.kart.product.exception.KartException;
import com.kart.product.service.ProductService;


@RestController
@CrossOrigin
@RequestMapping
public class ProductController {
	
	 @Value("${user.uri}") 
	 String userUri;
	
	//@Autowired 
	//DiscoveryClient client;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productService;

	// Create a new product
	@PostMapping(value = "api/{sellerid}/products", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createProduct(@PathVariable String sellerid , @Valid @RequestBody ProductDTO prodDTO) throws KartException {
		logger.info("Creation request for product {}", prodDTO);
		String prodid = "Product added with Id :" + productService.createProduct(sellerid, prodDTO);
		return new ResponseEntity<>(prodid, HttpStatus.OK);
	}


	// Fetches specific product details
	@GetMapping(value = "api/product/{productname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> getProductDetails(@PathVariable String productname) throws KartException{

		logger.info("Product detail request {}", productname);

		ProductDTO productDTO = productService.getProductDetails(productname);
		return new ResponseEntity<>(productDTO, HttpStatus.OK);
	}
	
	// Fetches specific product details
		@GetMapping(value = "api/productid/{prodid}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<ProductDTO> getProductById(@PathVariable String prodid) throws KartException{

			logger.info("Product detail request {}", prodid);

			ProductDTO productDTO = productService.getProductByid(prodid);
			return new ResponseEntity<>(productDTO, HttpStatus.OK);
		}

	// Fetches specific product details by category
	@GetMapping(value = "api/products/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProductByCategory(@PathVariable String category) throws KartException{

		logger.info("Product detail request {}", category);
		List<ProductDTO> productDTOs = productService.getProductByCategory(category);
		return new ResponseEntity<>(productDTOs, HttpStatus.OK);
	}


	// Fetches all product details
	@GetMapping(value = "api/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getAllProducts() throws Exception{
		logger.info("Product detail request for All Products {}");

		List<ProductDTO> productDTOs = productService.getAllProducts();
		return new ResponseEntity<>(productDTOs, HttpStatus.OK);
	}

	// Fetches all Subscribed product details
	@GetMapping(value = "api/subscribedproducts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<subscribedProductDTO>> getAllSubscribedProducts() throws KartException{
		logger.info("Product detail request for All Products {}");

		List<subscribedProductDTO> subDTOs = productService.getAllSubscribedProducts();
		return new ResponseEntity<>(subDTOs, HttpStatus.OK);
	}


	// Create Subscribed product details

	@PostMapping(value = "api/subscribedproduct", consumes =MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<String> createSubscribedProduct(@RequestBody subscribedProductDTO subDTO) throws KartException{ 
	logger.info("Product detail request for All Products {}"); 
	String st;

	//List<ServiceInstance> Uinstances= client.getInstances("userMS");
	//ServiceInstance Uinstance= Uinstances.get(0);
	//URI userUri = Uinstance.getUri();
	
	BuyerDTO buyerdto = new RestTemplate().getForObject(userUri +"api/buyer/" + subDTO.getBuyerid() , BuyerDTO.class); 
	if(buyerdto.getIsprivileged()) { 
		st = productService.createSubscribedProduct(subDTO);
	}
	else { 
		st = "Not a privileged User"; 
	}
	return new ResponseEntity<>(st, HttpStatus.OK); 
	}


	//updating product stock by the seller
	@PutMapping(value = "api/product/{prodid}/{sto}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateProductStock(@PathVariable String prodid, @PathVariable int sto) throws KartException{

		logger.info("Product update request {}", prodid);
		String st = productService.updateProductStock(prodid, sto);

		return new ResponseEntity<>(st, HttpStatus.OK);
	}

	//Delete a product
	@DeleteMapping(value = "api/{sellerid}/products/{prodid}")
	public ResponseEntity<String> deleteProduct(@PathVariable String sellerid,@PathVariable String prodid) throws KartException{
		String successMessage = productService.deleteProduct(sellerid, prodid);
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
}
