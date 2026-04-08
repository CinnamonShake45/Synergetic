package com.cinnamonshake.storefront.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private List<ItemRequest> items;
}