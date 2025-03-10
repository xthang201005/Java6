package edu.poly.assignment_java5.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data

public class CartItemResponse {
	//lưu trữ thông tin của một sản phẩm trong giỏ hàng
	private final long id;
	private final long cartId;
	private final long productId;
	private final String productName;
	private final double productPrice;
	private final double productDiscount;
	private final int productQuantity;
	private final String productImageName;
	private final int quantity;

	public CartItemResponse(long id, long cartId, long productId, String productName, double productPrice,
			double productDiscount, int productQuantity, String productImageName, int quantity) {
		this.id = id;
		this.cartId = cartId;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDiscount = productDiscount;
		this.productQuantity = productQuantity;
		this.productImageName = productImageName;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public long getCartId() {
		return cartId;
	}

	public long getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public double getProductDiscount() {
		return productDiscount;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public String getProductImageName() {
		return productImageName;
	}

	public int getQuantity() {
		return quantity;
	}
}
