package com.flmfoods.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flmfoods.dao.ItemRepository;
import com.flmfoods.dto.AddItemRequestDto;
import com.flmfoods.dto.AddItemResponseDto;
import com.flmfoods.dto.ItemResponseDTO;
import com.flmfoods.model.Item;
import com.flmfoods.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemrepository;

    @Override
    public AddItemResponseDto addItem(AddItemRequestDto addItemRequestDto) {

        Item item = new Item();

        item.setItemName(addItemRequestDto.getItemName());
        item.setPrice(addItemRequestDto.getPrice());
        item.setStockQuantity(addItemRequestDto.getStockQuantity());
        item.setRating(addItemRequestDto.getRating());
        item.setDiscount(addItemRequestDto.getDiscount());

        Item saveditem = itemrepository.save(item);

        AddItemResponseDto response = new AddItemResponseDto();

        BeanUtils.copyProperties(saveditem, response);

        return response;
    }

    @Override
    public List<Item> getItems() {

        return itemrepository.findAll();
    }

    @Override
    public List<ItemResponseDTO> getItemsByDiscount(double discount) {

        List<Item> itemsList = itemrepository.findAll();

        List<ItemResponseDTO> dtoList = itemsList
                .stream()
                .filter(item -> item.getDiscount() > discount)
                .map(item -> {

                    ItemResponseDTO dto = new ItemResponseDTO();

                    BeanUtils.copyProperties(item, dto);

                    return dto;
                })
                .toList();

        return dtoList;
    }

    @Override
    public Item updateItem(int id, Item updatedItem) {

        Item item = itemrepository.findById(id).get();

        item.setItemName(updatedItem.getItemName());
        item.setPrice(updatedItem.getPrice());
        item.setStockQuantity(updatedItem.getStockQuantity());
        item.setRating(updatedItem.getRating());
        item.setDiscount(updatedItem.getDiscount());

        return itemrepository.save(item);
    }
    @Override
    public void deleteItem(int id) {

        itemrepository.deleteById(id);
    }
	}
