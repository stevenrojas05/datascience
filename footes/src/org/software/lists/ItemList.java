package org.software.lists;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.software.entities.Item;

@XmlRootElement(name = "listing")
public class ItemList {
	private List<Item> items;

	public ItemList() {
	}

	public ItemList(List<Item> items) {
		this.items = items;
	}

	@XmlElement(name = "data")
	public List<Item> getItems() {
		return items;
	}
}
