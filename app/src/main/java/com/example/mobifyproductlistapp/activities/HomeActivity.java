package com.example.mobifyproductlistapp.activities;

import static com.example.mobifyproductlistapp.retrofit.ProductsApiInterface.ProductsInterface;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobifyproductlistapp.databinding.ActivityHomeBinding;
import com.example.mobifyproductlistapp.models.ProductModel;
import com.example.mobifyproductlistapp.retrofit.ProductsApiHandler;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ProductsApiHandler.callProductsApi(new ProductsInterface() {
            @Override
            public void getProducts(List<ProductModel> products) {
                ProductsInterface.super.getProducts(products);
                if (products != null) {
                    StringBuilder data = new StringBuilder();
                    for (ProductModel productModel : products) {
                        data.append(productModel.toString());
                        data.append("\n\n");
                    }
                    binding.emptyDataMessage.setText(data);
                } else {
                    binding.emptyDataMessage.setText("No products available now ! Please try later");
                }
            }
        });

        /*ProductsApiHandler.callProductDetailApi(100, new ProductsInterface() {
            @Override
            public void getProductItem(ProductModel product) {
                if(product!=null){
                    binding.emptyDataMessage.setText(product.toString());
                }
                else{
                    binding.emptyDataMessage.setText("No products available now ! Please try later");
                }
            }
        });*/
    }
}