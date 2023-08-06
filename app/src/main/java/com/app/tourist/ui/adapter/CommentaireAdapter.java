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
import com.app.tourist.data.models.Avis;
import com.app.tourist.data.models.SitesModel;
import com.app.tourist.ui.view.detail.DetailActivity;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CommentaireAdapter extends BaseAdapter {
    public ArrayList<Avis> items;
    public Context context;

    public CommentaireAdapter(ArrayList<Avis> items, Context context) {
        this.items = items;
        this.context = context;
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
            view = LayoutInflater.from(this.context).inflate(R.layout.item_commentaire, viewGroup, false);
            holder = new ViewHolder();
            holder.usernameTxt = view.findViewById(R.id.username);
            holder.contenuTxt = view.findViewById(R.id.contenu);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.usernameTxt.setText(items.get(i).getUsername());
        holder.contenuTxt.setText(items.get(i).getCommentaire());

        /*int drawableResourceId = context.getResources().getIdentifier(
                (items.get(i).getPic()), "drawable", context.getPackageName()
        );*/

        return view;
    }

    public class ViewHolder{
        public TextView usernameTxt, contenuTxt;

    }
}
