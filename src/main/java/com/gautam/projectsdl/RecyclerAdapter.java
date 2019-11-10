package com.gautam.projectsdl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.*;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>
{
    private List<Infodata> list;

    public RecyclerAdapter(List<Infodata> list){
        this.list = list;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView quotes,author;
        public ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            quotes = (TextView) itemView.findViewById(R.id.quotes);
            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_content, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Infodata data = list.get(i);
        myViewHolder.quotes.setText(data.getQuotes());

        myViewHolder.image.setImageResource(data.getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
