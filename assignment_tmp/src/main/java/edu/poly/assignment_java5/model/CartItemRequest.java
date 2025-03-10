package edu.poly.assignment_java5.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

import lombok.ToString;

@Data
@ToString
public class CartItemRequest {
	private final String userId;
	private final int productId;
	private final int quantity;

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
