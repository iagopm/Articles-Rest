package iagopm.articlesapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import iagopm.articlesapi.enums.Brand;
import iagopm.articlesapi.enums.ProductType;

@Entity
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	// Article name
	@NotBlank
	private String name;
	// Article description
	@NotBlank
	private String description;
	// Arteicle rating from 0 - 5
	@Min(0)
	@Max(5)
	private int rating;
	// Price
	@Min(0)
	@Max(999999)
	private double price;
	// Article brand
	@NotBlank
	private Brand brand;
	// Type of article
	@NotBlank
	private ProductType productType;

	public Article() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", description=" + description + ", rating=" + rating
				+ ", price=" + price + ", brand=" + brand + ", productType=" + productType + "]";
	}
}