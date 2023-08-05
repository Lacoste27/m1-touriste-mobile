package com.app.tourist.ui.adapter;

import com.app.tourist.R;
import com.app.tourist.data.models.ItemsModel;
import com.app.tourist.ui.view.detail.DetailActivity;
import com.bumptech.glide.Glide;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    public ArrayList<ItemsModel> items;
    public DecimalFormat formatter;
    public Context context;

    public ItemsAdapter(ArrayList<ItemsModel> items) {
        this.items = items;
        this.formatter = new DecimalFormat("###,###,###.##");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewholder, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.adressTxt.setText(items.get(position).getAdresse());

        int drawableResourceId = holder.itemView.getResources().getIdentifier((items.get(position).getPic()), "drawable", holder.itemView.getContext().getOpPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object", items.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView titleTxt, adressTxt;
        public ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTxt = itemView.findViewById(R.id.titleTxt);
            adressTxt = itemView.findViewById(R.id.adress);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
