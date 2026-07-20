package com.flmfoods.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flmfoods.model.Item;
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}