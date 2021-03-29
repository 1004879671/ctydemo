package com.ccty.service.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 商品实体类
 */
//@Data
@Entity
@Table(name="tb_product")
@ApiModel
public class Product {

	@Id
//	@GeneratedValue(generator = "user-uuid")
	@ApiModelProperty(value = "id")
	@Column(name="id")
	private String id;

	@ApiModelProperty(value = "产品名称")
	@Column(name="productName")
	private String productName;

	@ApiModelProperty(value = "状态")
	@Column(name="status")
	private Integer status;

	@ApiModelProperty(value = "价格")
	@Column(name="price")
	private BigDecimal price;

	@ApiModelProperty(value = "产品描述")
	@Column(name="productDesc")
	private String productDesc;

	@ApiModelProperty(value = "图片描述")
	@Column(name="caption")
	private String caption;

	@ApiModelProperty(value = "库存")
	@Column(name="inventory")
	private Integer inventory;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

}
