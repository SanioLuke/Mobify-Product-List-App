package com.example.mobifyproductlistapp.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobifyproductlistapp.databinding.ProductItemBinding;
import com.example.mobifyproductlistapp.models.ProductModel;
import com.example.mobifyproductlistapp.utils.AppInterfaces;

import java.util.ArrayList;

@SuppressWarnings("all")
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ListViewHolder> {

    Activity activity;
    ArrayList<ProductModel> arrayList;
    AppInterfaces.AdapterInterface adapterInterface;

    public ProductsAdapter(Activity activity, ArrayList<ProductModel> arrayList, AppInterfaces.AdapterInterface adapterInterface) {
        this.activity = activity;
        this.arrayList = arrayList;
        this.adapterInterface = adapterInterface;
    }

    @NonNull
    @Override
    public ProductsAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ListViewHolder holder, int position) {
        ProductModel model = arrayList.get(holder.getAdapterPosition());
        holder.onBind(activity, model, adapterInterface);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {

        ProductItemBinding binding;

        public ListViewHolder(ProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void onBind(Activity activity, ProductModel model, AppInterfaces.AdapterInterface adapterInterface) {
            binding.piName.setText(model.getTitle());
            binding.piPrice.setText("Price : ₹" + model.getPrice());
            binding.piCategory.setText("Category : " + model.getCategory());
            Glide.with(activity).load(model.getThumbnail()).into(binding.piImage);
            binding.piRate.setRating((float) model.getRating());
            binding.getRoot().setOnClickListener(v -> adapterInterface.onItemClick(getAdapterPosition()));
        }
    }
}
