package com.example.mobifyproductlistapp.activities;

import static com.example.mobifyproductlistapp.retrofit.ProductsApiInterface.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mobifyproductlistapp.R;
import com.example.mobifyproductlistapp.databinding.ActivityProductDisplayBinding;
import com.example.mobifyproductlistapp.models.ProductModel;
import com.example.mobifyproductlistapp.retrofit.ProductsApiHandler;
import com.example.mobifyproductlistapp.retrofit.ProductsApiInterface;
import com.example.mobifyproductlistapp.utils.AppUtils;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ProductDisplayActivity extends AppCompatActivity {

    ActivityProductDisplayBinding binding;
    Dialog loading_dialog;
    int productId;
    int currentQuantity= 1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppUtils.lightBackgroundStatusBarDesign(ProductDisplayActivity.this);
        super.onCreate(savedInstanceState);
        binding= ActivityProductDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        productId= getIntent().getIntExtra("productId", 0);
        loading_dialog= AppUtils.createDialogBox(ProductDisplayActivity.this, R.layout.progress_dialog, false);
        if(!isFinishing() && loading_dialog!=null && !loading_dialog.isShowing()) loading_dialog.show();

        binding.productDisplayBackButton.setOnClickListener(v-> onBackPressed());

        ProductsApiHandler.callProductDetailApi(productId, new ProductsInterface() {
            @Override
            public void getProductItem(ProductModel product) {
                if(loading_dialog!=null && loading_dialog.isShowing()) loading_dialog.dismiss();
                if(product!=null){
                    binding.productDisplayTitleText.setText(product.getTitle());
                    binding.pdName.setText(product.getTitle());
                    binding.pdRate.setRating((float) product.getRating());

                    double org_price= product.getPrice();
                    double discount_percent= product.getDiscountPercentage();
                    double discount_val= AppUtils.limitDouble(discount_percent/100*org_price, "#.##");
                    double discounted_price= AppUtils.limitDouble(org_price - discount_val, "#.##");
                    binding.pdPrice.setText("₹ "+discounted_price);

                    binding.pdOriginalPrice.setText("(₹ "+product.getPrice()+")");
                    binding.pdOriginalPrice.setPaintFlags(binding.pdOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                    binding.pdDescription.setText("Product Description : "+product.getDescription());
                    binding.pdCategory.setText("Category : "+product.getCategory());
                    binding.pdBrand.setText("Brand : "+product.getBrand());
                    loadImagesFlipper(product.getImages());
                    binding.pdUserSelectedQuantity.setText(""+currentQuantity);
                    binding.pdStocks.setText("Remaining Stocks : "+product.getStock());
                    binding.pdTotalPrice.setText("₹ "+AppUtils.limitDouble((currentQuantity*discounted_price), "#.##"));

                    binding.pdQuantityAddButton.setOnClickListener(v->{
                        if(currentQuantity < product.getStock()){
                            currentQuantity+=1;
                            binding.pdUserSelectedQuantity.setText(""+currentQuantity);
                            binding.pdTotalPrice.setText(""+AppUtils.limitDouble((currentQuantity*discounted_price), "#.##"));
                        }
                        else {
                            Toast.makeText(ProductDisplayActivity.this, "Reached the maximum stock limit !", Toast.LENGTH_SHORT).show();
                        }
                    });

                    binding.pdQuantityRemoveButton.setOnClickListener(v->{
                        if(currentQuantity > 1){
                            currentQuantity-=1;
                            binding.pdUserSelectedQuantity.setText(""+currentQuantity);
                            binding.pdTotalPrice.setText(""+AppUtils.limitDouble((currentQuantity*discounted_price), "#.##"));
                        }
                    });

                    binding.pdSubmitButton.setOnClickListener(v->{
                        Snackbar.make(binding.getRoot(), "Product successfully purchased", Snackbar.LENGTH_SHORT).show();
                        new Handler().postDelayed(()-> finish(), 1500);
                    });
                }
                else{
                    Snackbar.make(binding.getRoot(), "Unable to find the selected product !! Please try again", Snackbar.LENGTH_SHORT).show();
                    new Handler().postDelayed(()-> finish(), 1500);
                }
            }
        });
    }

    private void loadImagesFlipper(List<String> images) {

        for(String img : images){
            ImageView imageView= new ImageView(ProductDisplayActivity.this);
            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            params.gravity= Gravity.CENTER_HORIZONTAL;
            imageView.setBackground(ContextCompat.getDrawable(ProductDisplayActivity.this, R.color.white));
            Glide.with(ProductDisplayActivity.this).load(img).into(imageView);
            imageView.setLayoutParams(params);
            binding.pdsFlipper.addView(imageView);
        }

        binding.pdsFlipper.startFlipping();
        binding.pdsFlipper.setAutoStart(true);
    }
}