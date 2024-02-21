package com.example.mobifyproductlistapp.activities;

import static com.example.mobifyproductlistapp.retrofit.ProductsApiInterface.ProductsInterface;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mobifyproductlistapp.R;
import com.example.mobifyproductlistapp.adapters.ProductsAdapter;
import com.example.mobifyproductlistapp.databinding.ActivityHomeBinding;
import com.example.mobifyproductlistapp.models.ProductModel;
import com.example.mobifyproductlistapp.retrofit.ProductsApiHandler;
import com.example.mobifyproductlistapp.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    ArrayList<ProductModel> productList = new ArrayList<>();
    ProductsAdapter adapter;
    Dialog loading_dialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppUtils.lightBackgroundStatusBarDesign(HomeActivity.this);
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loading_dialog= AppUtils.createDialogBox(HomeActivity.this, R.layout.progress_dialog, false);
        if(!isFinishing() && loading_dialog!=null && !loading_dialog.isShowing()) loading_dialog.show();

        ProductsApiHandler.callProductsApi(new ProductsInterface() {
            @Override
            public void getProducts(List<ProductModel> products) {
                if(loading_dialog!=null && loading_dialog.isShowing()) loading_dialog.dismiss();
                if (products != null) {
                    productList.clear();
                    productList.addAll(products);
                    binding.homeRecyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                    adapter = new ProductsAdapter(HomeActivity.this, productList, position -> {
                        Intent intent = new Intent(HomeActivity.this, ProductDisplayActivity.class)
                                .putExtra("productId", productList.get(position).getId());
                        startActivity(intent);
                    });
                    binding.homeRecyclerView.setAdapter(adapter);
                } else {
                    binding.emptyDataMessage.setText("No products available now ! Please try later");
                }
            }
        });
    }
}