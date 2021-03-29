package com.ccty.service.order.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品实体类
 */
public class Product implements Serializable {

	private static final long serialVersionUID = -432908543160176349L;
	private String id;
	private String productName;
//	private Integer status;
	private BigDecimal price;
//	private String productDesc;
//	private String caption;
//	private Integer inventory;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

//	public Integer getStatus() {
//		return status;
//	}
//
//	public void setStatus(Integer status) {
//		this.status = status;
//	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

//	public String getProductDesc() {
//		return productDesc;
//	}
//
//	public void setProductDesc(String productDesc) {
//		this.productDesc = productDesc;
//	}
//
//	public String getCaption() {
//		return caption;
//	}
//
//	public void setCaption(String caption) {
//		this.caption = caption;
//	}
//
//	public Integer getInventory() {
//		return inventory;
//	}
//
//	public void setInventory(Integer inventory) {
//		this.inventory = inventory;
//	}


	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", productName='" + productName + '\'' +
				", price=" + price +
				'}';
	}
}
