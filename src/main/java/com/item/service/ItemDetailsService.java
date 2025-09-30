package com.item.service;

import com.item.model.ItemDetails;

public interface ItemDetailsService {

	boolean addItemDetails(ItemDetails itemDetails) ;
	boolean editItemDetails(ItemDetails itemDetails);
	ItemDetails getItemDetails(Long id );
	
}
