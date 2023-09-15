package com.nike.products.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nike.products.R;
import com.nike.products.businesslogic.interfaces.GeneralItemClickListener;
import com.nike.products.businesslogic.room.entity.ModelCart;
import com.nike.products.businesslogic.room.entity.ModelHome;
import com.nike.products.databinding.HolderBookmarkBinding;
import com.nike.products.databinding.HolderCartBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolder> {

    private List<ModelCart> itemList = new ArrayList<>();
    private GeneralItemClickListener listener;

    public AdapterCart(List<ModelCart> itemList, GeneralItemClickListener generalItemClickListener) {
        this.itemList = itemList;
        this.listener = generalItemClickListener;
    }

    @NonNull
    @Override
    public AdapterCart.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        HolderCartBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.holder_cart, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCart.ViewHolder holder, int position) {

        holder.bind();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private HolderCartBinding binding;

        public ViewHolder(@NonNull HolderCartBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind() {
            binding.setModel(itemList.get(getAdapterPosition()));
            binding.setGeneralItemClickListener(listener);
            binding.setCurrentPosition(getAdapterPosition());
        }
    }
}
