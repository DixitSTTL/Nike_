package com.nike.products.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nike.products.R;
import com.nike.products.businesslogic.interfaces.GeneralItemClickListener;
import com.nike.products.databinding.HolderBookmarkBinding;
import com.nike.products.databinding.HolderHomeBinding;
import com.nike.products.businesslogic.room.entity.ModelHome;

import java.util.ArrayList;
import java.util.List;

public class AdapterBookmark extends RecyclerView.Adapter<AdapterBookmark.ViewHolder> {

    List<ModelHome> itemList = new ArrayList<>();
    GeneralItemClickListener listener;
    public AdapterBookmark(List<ModelHome> itemList, GeneralItemClickListener generalItemClickListener) {
        this.itemList = itemList;
        this.listener = generalItemClickListener;
    }

    @NonNull
    @Override
    public AdapterBookmark.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        HolderBookmarkBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.holder_bookmark,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBookmark.ViewHolder holder, int position) {

        holder.bind();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HolderBookmarkBinding binding;
        public ViewHolder(@NonNull HolderBookmarkBinding itemView) {
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
