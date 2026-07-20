package com.flmfoods.ItemService;

import java.util.List;

import com.flmfoods.dto.AddItemRequestDto;
import com.flmfoods.model.Item;

public interface ItemService {

    public AddItemRequestDto addItem(AddItemRequestDto addItemRequestDto);

    public List<Item> getItems();
    
    public List<Item> getItemsByDiscount(double discount);
    public void deleteItem(int id);

}