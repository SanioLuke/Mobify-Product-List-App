package com.example.mobifyproductlistapp.retrofit;

import static com.example.mobifyproductlistapp.retrofit.ProductsApiInterface.ProductsInterface;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mobifyproductlistapp.models.ProductModel;
import com.example.mobifyproductlistapp.models.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsApiHandler {

    private static final String TAG = "ProductsApiHandler";

    public static void callProductsApi(ProductsInterface productsInterface) {
        ProductsApiInterface productsApiInterface = RetrofitClient.getRetrofitInstance().create(ProductsApiInterface.class);
        Call<ResponseModel> callAllProducts = productsApiInterface.getAllProducts();
        callAllProducts.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ProductModel> products = response.body().getProducts();
                    productsInterface.getProducts(products);
                } else {
                    productsInterface.getProducts(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: Products API Call failure : " + t.getLocalizedMessage());
                productsInterface.getProducts(null);
            }
        });


    }

    public static void callProductDetailApi(int productId, ProductsInterface productsInterface) {
        ProductsApiInterface productsApiInterface = RetrofitClient.getRetrofitInstance().create(ProductsApiInterface.class);
        Call<ProductModel> callProductDetail = productsApiInterface.getProductDetail(productId);
        callProductDetail.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(@NonNull Call<ProductModel> call, @NonNull Response<ProductModel> response) {
                if (response.isSuccessful()) {
                    ProductModel productDetail = response.body();
                    productsInterface.getProductItem(productDetail);
                } else {
                    productsInterface.getProducts(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductModel> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: Products API Call failure : " + t.getLocalizedMessage());
                productsInterface.getProducts(null);
            }
        });
    }
}
