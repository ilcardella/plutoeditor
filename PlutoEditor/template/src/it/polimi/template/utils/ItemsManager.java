package it.polimi.template.utils;

import it.polimi.template.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemsManager {

	private static List<Item> items;

	public static List<Item> getItems() {
		if(items == null){
			items = new ArrayList<Item>();

			Item i1 = new Item();
			Item i2 = new Item();
			Item i3 = new Item();
			Item i4 = new Item();

			i1.setShapeCategory(1);
			i2.setShapeCategory(2);
			i3.setShapeCategory(3);
			i4.setShapeCategory(4);

			i1.setName("1");
			i2.setName("2");
			i3.setName("3");
			i4.setName("4");

			items.add(i1);
			items.add(i2);
			items.add(i3);
			items.add(i4);
		}
		
		return items;
	}
}
