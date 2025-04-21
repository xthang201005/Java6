package edu.poly.assignment_java6.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

import lombok.ToString;

@Data
@ToString
public class CartItemRequest {

	
	

	@JsonCreator
	public CartItemRequest(@JsonProperty("userId") String userId, @JsonProperty("productId") int productId,
			@JsonProperty("quantity") int quantity) {
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public String getUserId() {
		return userId;
	}

	public int getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

}
