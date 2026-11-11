package com.aurabite.backend.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long menuItemId;
    private Integer quantity;
}
