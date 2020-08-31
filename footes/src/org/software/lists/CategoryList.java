package org.software.lists;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.software.entities.Category;

@XmlRootElement(name = "categories")
public class CategoryList {
	
	private List<Category> items;

	public CategoryList() {}

	public CategoryList(List<Category> items) {
		this.items = items;
	}

	@XmlElement(name = "data")
	public List<Category> getItems() {
		return items;
	}
}
