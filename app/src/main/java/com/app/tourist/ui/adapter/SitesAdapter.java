package com.app.tourist.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.tourist.R;
import com.app.tourist.data.models.ItemsModel;
import com.app.tourist.data.models.SitesModel;
import com.app.tourist.ui.view.detail.DetailActivity;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SitesAdapter extends BaseAdapter {
    public ArrayList<SitesModel> items;
    public DecimalFormat formatter;
    public Context context;

    public SitesAdapter(ArrayList<SitesModel> items, Context context) {
        this.items = items;
        this.context = context;
        this.formatter = new DecimalFormat("###,###,###.##");
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if(view == null){
            view = LayoutInflater.from(this.context).inflate(R.layout.item_viewholder, viewGroup, false);
            holder = new ViewHolder();
            holder.titleTxt = view.findViewById(R.id.titleTxt);
            holder.adressTxt = view.findViewById(R.id.adress);
            holder.pic = view.findViewById(R.id.pic);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.titleTxt.setText(items.get(i).getNom());
        holder.adressTxt.setText(items.get(i).getRegion());

        if(items.get(i).getPhotos() != null){
            Glide.with(context)
                    .load(items.get(i).getPhotos()[0])
                    .into(holder.pic);
        }else{
            Glide.with(context)
                    .load("https://lesglobeblogueurs.com/wp-content/uploads/2016/05/pont-ranomafana.jpg")
                    .into(holder.pic);
        }

        /*int drawableResourceId = context.getResources().getIdentifier(
                (items.get(i).getPic()), "drawable", context.getPackageName()
        );*/

        view.setOnClickListener(view1 -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("object", items.get(i));
            context.startActivity(intent);
        });
        return view;
    }

    public class ViewHolder{
        public TextView titleTxt, adressTxt;
        public ImageView pic;

    }
}
