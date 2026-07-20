package com.flmfoods.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.flmfoods.dto.AddItemRequestDto;
import com.flmfoods.dto.AddItemResponseDto;
import com.flmfoods.dto.ItemResponseDTO;
import com.flmfoods.model.Item;
import com.flmfoods.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemservice;

    @PostMapping("/add")
    public AddItemResponseDto addItem(@RequestBody AddItemRequestDto addItemRequestDto) {

        return itemservice.addItem(addItemRequestDto);
    }

    @GetMapping
    public List<Item> getItems() {

        return itemservice.getItems();
    }

    @GetMapping("/discount/{discount}")
    public List<ItemResponseDTO> getItemsByDiscount(@PathVariable double discount) {

        return itemservice.getItemsByDiscount(discount);
    }
    
    @PutMapping("/update/{id}")
    public Item updateItem(@PathVariable int id,
                           @RequestBody Item updatedItem) {

        return itemservice.updateItem(id, updatedItem);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteItem(@PathVariable int id) {

        itemservice.deleteItem(id);

        return "Item Deleted Successfully";
    }
}

