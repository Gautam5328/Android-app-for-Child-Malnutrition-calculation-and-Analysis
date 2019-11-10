package com.gautam.projectsdl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
public class DisplayFamInfo extends AppCompatActivity {

    private DatabaseReference mData;
    //   private ListView mListView;

    Context context;
    List<ChildInfo> list=new ArrayList<>();
    ProgressDialog progressDialog;
    private String pid;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_display_fam_info );

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(DisplayFamInfo.this));
        progressDialog = new ProgressDialog(DisplayFamInfo.this);

        progressDialog.setMessage("Loading From Database");

        progressDialog.show();

        mData= FirebaseDatabase.getInstance().getReference().child("DataChild").child( "FamInfo" );

        mData.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ChildInfo childInfo=new ChildInfo( );
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {

                    for (DataSnapshot myd : ds.getChildren())
                    {
                        try {
                            childInfo= myd.getValue( ChildInfo.class );
                            childInfo.mob=myd.getKey().toString();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    list.add(childInfo);
                }

                adapter=new RecyclerViewAdapterFamInfo(DisplayFamInfo.this,list);

                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();

            }
        } );

    }

}
