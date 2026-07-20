package com.flmfoods.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flmfoods.dao.ItemRepository;
import com.flmfoods.dao.OrderRepository;
import com.flmfoods.dto.OrderItemRequestDTO;
import com.flmfoods.dto.OrderItemResponseDTO;
import com.flmfoods.dto.OrderResponseDTO;
import com.flmfoods.dto.PlaceOrderRequestDTO;
import com.flmfoods.model.Item;
import com.flmfoods.model.Order;
import com.flmfoods.model.OrderItem;
import com.flmfoods.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderResponseDTO placeOrder(
            PlaceOrderRequestDTO orderRequestDTO) {

        List<OrderItemRequestDTO> orderItemRequestList =
                orderRequestDTO.getOrderItemRequestList();

        List<OrderItem> orderItemList =
                new ArrayList<>();

        double totalPrice = 0;

        for (OrderItemRequestDTO orderItemDTO
                : orderItemRequestList) {

            // Fetch item from database
            Item item = itemRepository
                    .findById(orderItemDTO.getItemId())
                    .orElseThrow(() ->
                            new RuntimeException("Item Not Found"));

            // Check stock quantity
            if (item.getStockQuantity()
                    < orderItemDTO.getQuantity()) {

                throw new RuntimeException(
                        "Out Of Stock");
            }

            // Create order item
            OrderItem orderItem =
                    new OrderItem();

            orderItem.setItem(item);

            orderItem.setQuantity(
                    orderItemDTO.getQuantity());

            // Calculate subtotal
            double subTotal =
                    item.getPrice()
                    * orderItemDTO.getQuantity();

            orderItem.setPrice(subTotal);

            // Decrease stock
            item.setStockQuantity(
                    item.getStockQuantity()
                    - orderItemDTO.getQuantity());

            // Update item in database
            itemRepository.save(item);

            totalPrice += subTotal;

            orderItemList.add(orderItem);
        }

        // Create order
        Order order = new Order();

        order.setOrderItems(orderItemList);

        order.setPrice(totalPrice);

        // Save order
        Order savedOrder =
                orderRepository.save(order);

        // Prepare response
        OrderResponseDTO orderResponseDTO =
                new OrderResponseDTO();

        List<OrderItemResponseDTO>
                orderItemResponseDTOs =
                new ArrayList<>();

        for (OrderItem orderItem
                : savedOrder.getOrderItems()) {

            OrderItemResponseDTO responseDTO =
                    new OrderItemResponseDTO();

            responseDTO.setItemName(
                    orderItem.getItem()
                    .getItemName());

            responseDTO.setQuantity(
                    orderItem.getQuantity());

            responseDTO.setPrice(
                    orderItem.getItem()
                    .getPrice());

            responseDTO.setSubTotal(
                    orderItem.getPrice());

            orderItemResponseDTOs
                    .add(responseDTO);
        }

        orderResponseDTO.setOrderId(
                savedOrder.getOrderId());

        orderResponseDTO
                .setOrderItemREsponseDTO(
                        orderItemResponseDTOs);

        orderResponseDTO.setTotalPrice(
                savedOrder.getPrice());

        return orderResponseDTO;
    }
}