package com.flmfoods.dto;

import java.util.List;

public class OrderResponseDTO {

    private int orderId;

    private double totalPrice;

    private List<OrderItemResponseDTO> orderItemREsponseDTO;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItemResponseDTO> getOrderItemREsponseDTO() {
        return orderItemREsponseDTO;
    }

    public void setOrderItemREsponseDTO(
            List<OrderItemResponseDTO> orderItemREsponseDTO) {

        this.orderItemREsponseDTO = orderItemREsponseDTO;
    }
}