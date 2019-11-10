package com.gautam.projectsdl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DisplayData extends AppCompatActivity
{
    private DatabaseReference mData;
    //   private ListView mListView;

    Context context;
    List<ChildData> list=new ArrayList<>();
    ProgressDialog progressDialog;
    private String pid;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(DisplayData.this));
        progressDialog = new ProgressDialog(DisplayData.this);

        progressDialog.setMessage("Loading Data Database");

        progressDialog.show();

        mData= FirebaseDatabase.getInstance().getReference().child("DataChild").child( "ChildDB" );

        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ChildData childData=new ChildData( );
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String tdate=ds.getKey();
                    for (DataSnapshot myd : ds.getChildren()) {

                        System.out.println(tdate + "Date");
                        try {
                            childData = myd.getValue( ChildData.class );
                            childData.mob = myd.getKey().toString();
                            childData.ddate=tdate.toString();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        list.add( childData );
                    }
                }

                adapter=new RecyclerViewAdapter(DisplayData.this,list);

                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });


    }
}
