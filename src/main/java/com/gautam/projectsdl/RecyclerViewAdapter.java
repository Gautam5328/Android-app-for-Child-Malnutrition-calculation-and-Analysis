package com.gautam.projectsdl;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    Context context;
    List<ChildData> MainImageUploadInfoList;
    public RecyclerViewAdapter(Context context, List<ChildData> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        ChildData datadetails=MainImageUploadInfoList.get(position);
        holder.PID.setText( datadetails.getPID() );
        holder.ChildGender.setText( datadetails.getGender());
        holder.ChildHeight.setText( datadetails.getHeight() );
        holder.ChildWeight.setText( datadetails.getWeight() );
        holder.Result.setText( datadetails.getResult());
        holder.Lon.setText( datadetails.getLon() );
        holder.Lat.setText( datadetails.getLat() );
        holder.dDate.setText( datadetails.getDdate() );
    }

    @Override
    public int getItemCount() {
        return MainImageUploadInfoList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder
    {

        public TextView ChildHeight;
        public TextView ChildWeight;
        public TextView ChildGender;
        public TextView Result;
        public TextView PID;
        public TextView Lat;
        public TextView Lon;
        public  TextView dDate;
        public ViewHolder(View itemView) {

            super(itemView);
            PID=(TextView)itemView.findViewById( R.id.Showparent );
            ChildHeight = (TextView) itemView.findViewById(R.id.ShowChildHeight);
            ChildWeight = (TextView) itemView.findViewById(R.id.ShowChildWeight);
            ChildGender = (TextView) itemView.findViewById(R.id.ShowChildGender);
            Result = (TextView) itemView.findViewById(R.id.ShowResult);
            Lat=(TextView)itemView.findViewById( R.id.ShowLatitude );
            Lon=(TextView)itemView.findViewById( R.id.ShowLongitude );
            dDate=(TextView)itemView.findViewById( R.id.ShowDate );

        }
    }
}
