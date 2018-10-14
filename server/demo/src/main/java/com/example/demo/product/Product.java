package com.example.demo.product;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Indexed
@Entity
public class Product {
	@Id
	@GeneratedValue
	private long id;
	@Field
	private String name;
	private BigDecimal price;
	@Field
	private String description;
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String Name)
	{
		this.name=Name;
	}
	
	public BigDecimal getprice()
	{
		return price;
	}
	
	public void setprice(BigDecimal price)
	{
		this.price=price;
	}
	
	public String getdescription()
	{
		return description;
	}
	
	public void setdescription(String description)
	{
		this.description=description;
	}
	
	public Product(){}
	
	public Product(int id, String Name)
	{
		this.id=id;
		this.name=Name;
	}
}
