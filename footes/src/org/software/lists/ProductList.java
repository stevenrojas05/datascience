package org.software.lists;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.software.entities.Product;

@XmlRootElement(name = "products")
public class ProductList {
	private List<Product> items;

	public ProductList() {
	}

	public ProductList(List<Product> items) {
		this.items = items;
	}

	@XmlElement(name = "data")
	public List<Product> getItems() {
		return items;
	}
}
