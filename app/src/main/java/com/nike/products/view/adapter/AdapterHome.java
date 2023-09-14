package com.nike.products.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nike.products.R;
import com.nike.products.businesslogic.interfaces.GeneralItemClickListener;
import com.nike.products.databinding.HolderHomeBinding;
import com.nike.products.businesslogic.room.entity.ModelHome;

import java.util.ArrayList;
import java.util.List;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolder> {
    
    List<ModelHome> itemList = new ArrayList<>();
    GeneralItemClickListener listener;
    public AdapterHome(List<ModelHome> itemList, GeneralItemClickListener generalItemClickListener) {
        this.itemList = itemList;
        this.listener = generalItemClickListener;
    }

    @NonNull
    @Override
    public AdapterHome.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        HolderHomeBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.holder_home,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHome.ViewHolder holder, int position) {

        holder.bind();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HolderHomeBinding binding;
        public ViewHolder(@NonNull HolderHomeBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind() {
            binding.setModel(itemList.get(getAdapterPosition()));
            binding.setGeneralItemListener(listener);
            binding.setCurrentPosition(getAdapterPosition());
        }
    }
}
