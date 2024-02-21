package com.example.mobifyproductlistapp.retrofit;

import android.util.Log;

import com.example.mobifyproductlistapp.models.ProductModel;
import com.example.mobifyproductlistapp.models.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductsApiInterface {

    String TAG = "ProductsApiInterface";

    @GET("products")
    Call<ResponseModel> getAllProducts();

    @GET("products/{id}")
    Call<ProductModel> getProductDetail(@Path("id") int productId);

    interface ProductsInterface {
        default void getProducts(List<ProductModel> products) {
            Log.e(TAG, "Products: " + products);
        }

        default void getProductItem(ProductModel product) {
            Log.e(TAG, "Product Detail: " + product);
        }
    }
}
