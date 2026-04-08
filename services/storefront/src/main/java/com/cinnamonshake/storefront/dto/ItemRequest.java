package com.cinnamonshake.storefront.dto;

import lombok.Data;

@Data
public class ItemRequest {

    private Long productId;
    private int quantity;
}