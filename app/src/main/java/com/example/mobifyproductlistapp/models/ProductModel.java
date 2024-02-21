package com.example.mobifyproductlistapp.models;

import androidx.annotation.NonNull;

import java.util.List;

public class ProductModel {
    int id;
    String title;
    String description;
    double price;
    double discountPercentage;
    double rating;
    int stock;
    String brand;
    String category;
    String thumbnail;
    List<String> images;

    public ProductModel(int id, String title, String description, double price, double discountPercentage,
                        double rating, int stock, String brand, String category, String thumbnail, List<String> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.rating = rating;
        this.stock = stock;
        this.brand = brand;
        this.category = category;
        this.thumbnail = thumbnail;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getRating() {
        return rating;
    }

    public int getStock() {
        return stock;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public List<String> getImages() {
        return images;
    }
    @NonNull
    @Override
    public String toString() {
        return id + ", " + title + ", " + description + ", " + price + ", "
                + discountPercentage + ", " + rating + ", " + stock + ", "
                + brand + ", " + category + ", " + thumbnail + ", " + images.toString();
    }
}
