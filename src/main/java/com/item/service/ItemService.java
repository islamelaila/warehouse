package com.item.service;

import java.util.List;

import com.item.model.Item;

public interface ItemService {
	
	boolean addItems(Item item) ;
	boolean deleteItem(Long	 id);
	boolean editItem(Item item);
	Item getItem(Long id );
	List<Item> getItems();

}
