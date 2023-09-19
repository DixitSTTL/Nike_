package com.nike.products.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nike.products.R;
import com.nike.products.businesslogic.interfaces.GeneralItemClickListener;
import com.nike.products.businesslogic.room.entity.ModelCart;
import com.nike.products.databinding.HolderCartBinding;
import com.nike.products.databinding.HolderGalleryBinding;
import com.nike.products.models.pojo.Get_search_pojo;

import java.util.ArrayList;
import java.util.List;

public class AdapterGallery extends RecyclerView.Adapter<AdapterGallery.ViewHolder> {

    private List<Get_search_pojo.Photo> itemList = new ArrayList<>();
    private GeneralItemClickListener listener;

    public AdapterGallery(List<Get_search_pojo.Photo> itemList, GeneralItemClickListener generalItemClickListener) {
        this.itemList = itemList;
        this.listener = generalItemClickListener;
    }

    @NonNull
    @Override
    public AdapterGallery.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        HolderGalleryBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.holder_gallery, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGallery.ViewHolder holder, int position) {

        holder.bind();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private HolderGalleryBinding binding;

        public ViewHolder(@NonNull HolderGalleryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind() {
            binding.setModel(itemList.get(getAdapterPosition()).getSrc());
            binding.setGeneralItemClickListener(listener);
            binding.setCurrentPosition(getAdapterPosition());
        }
    }
}
