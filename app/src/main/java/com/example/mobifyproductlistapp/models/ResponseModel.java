package com.example.mobifyproductlistapp.models;

import java.util.List;

public class ResponseModel {
    List<ProductModel> products;
    int total;
    int skip;
    int limit;

    public ResponseModel(List<ProductModel> products, int total, int skip, int limit) {
        this.products = products;
        this.total = total;
        this.skip = skip;
        this.limit = limit;
    }

    public List<ProductModel> getProducts() {
        return products;
    }
}
