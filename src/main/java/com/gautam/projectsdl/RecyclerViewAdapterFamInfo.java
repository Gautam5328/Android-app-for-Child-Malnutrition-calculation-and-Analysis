package com.gautam.projectsdl;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapterFamInfo extends  RecyclerView.Adapter<RecyclerViewAdapterFamInfo.ViewHolder>
{
    Context context;
    List<ChildInfo> MainImageUploadInfoList;

    public RecyclerViewAdapterFamInfo(Context context, List<ChildInfo> mainImageUploadInfoList) {
        this.context = context;
        MainImageUploadInfoList = mainImageUploadInfoList;
    }
    @NonNull
    @Override
    public RecyclerViewAdapterFamInfo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_faminfo, parent, false);

        ViewHolder viewHolder=new ViewHolder( view );

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterFamInfo.ViewHolder holder, int position) {

        ChildInfo datadetails=MainImageUploadInfoList.get(position);
        holder.Mob.setText( datadetails.getMob() );
        holder.FatherInfo.setText( datadetails.getFname());
        holder.Mothername.setText( datadetails.getMothname() );
        holder.DOB.setText( datadetails.getDob() );
        holder.Delivery.setText( datadetails.getDeliv());
        holder.Addr.setText( datadetails.getAdd() );
        holder.FoodInfo.setText( datadetails.getFood());
        holder.Lon.setText( datadetails.getLon() );
        holder.Lat.setText( datadetails.getLat() );
        holder.Occ.setText( datadetails.getOccup() );
    }

    @Override
    public int getItemCount() {
        return MainImageUploadInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView FatherInfo;
        public TextView Mothername;
        public TextView Delivery;
        public TextView DOB;
        public TextView Addr;
        public TextView FoodInfo;
        public  TextView Mob;
        public TextView Lat;
        public TextView Lon,Occ;

        public ViewHolder(View itemview) {
            super(itemview);
            Mob= (TextView) itemview.findViewById( R.id.Showparent );
            FatherInfo=(TextView)itemview.findViewById( R.id.ShowChildHeight );
            Mothername = (TextView) itemView.findViewById(R.id.ShowChildWeight);
            Delivery= (TextView) itemView.findViewById(R.id.ShowChildGender);
            DOB = (TextView) itemView.findViewById(R.id.ShowResult);
            Addr=(TextView)itemView.findViewById( R.id.ShowLatitude );
            FoodInfo=(TextView)itemView.findViewById( R.id.ShowLongitude );
            Lat=(TextView)itemview.findViewById( R.id.ShowLat );
            Lon=(TextView)itemview.findViewById( R.id.ShowLon );
            Occ=(TextView)itemview.findViewById( R.id.ShowRes );
        }
    }
}
