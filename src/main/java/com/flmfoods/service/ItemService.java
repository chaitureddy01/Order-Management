package com.flmfoods.service;

import java.util.List;

import com.flmfoods.dto.AddItemRequestDto;
import com.flmfoods.dto.AddItemResponseDto;
import com.flmfoods.dto.ItemResponseDTO;
import com.flmfoods.model.Item;

public interface ItemService {

    public AddItemResponseDto addItem(AddItemRequestDto addItemRequestDto);

    public List<Item> getItems();

    public List<ItemResponseDTO> getItemsByDiscount(double discount);

    public Item updateItem(int id, Item updatedItem);

	public void deleteItem(int id);

	
}
