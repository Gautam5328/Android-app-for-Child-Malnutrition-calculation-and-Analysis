package com.gautam.projectsdl;


import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {
    EditText searchtext;
    RecyclerView recyclerView;
    DatabaseReference mData;
    Button b1;
    RecyclerViewAdapterFamInfo adapter;
    ArrayList<ChildInfo> cdata=new ArrayList<>(  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search );
        recyclerView=(RecyclerView)findViewById( R.id.recyclerView );

        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( Search.this ) );
        recyclerView.addItemDecoration( new DividerItemDecoration( Search.this , LinearLayout.VERTICAL ) );

        searchtext=(EditText)findViewById( R.id.searc );

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        Toast.makeText(this, "" + currentFirebaseUser.getEmail(), Toast.LENGTH_SHORT).show();

        String useremail=currentFirebaseUser.getEmail();
        String mydb;
        if(useremail.contentEquals( "admin@gmail.com" ))
        {
            mydb="DataChild";
        }
        else if(useremail.contentEquals( "iamo@gmail.com" ))
        {
            mydb="DataChild3";
        }
        else if(useremail.contentEquals( "kan@gmail.com" ))
        {
            mydb="DataChild4";
        }
        else{

            mydb="DataChild2";
        }


        mData= FirebaseDatabase.getInstance().getReference().child( mydb).child( "FamInfo" );

        searchtext.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!s.toString().isEmpty())
                {
                    setAdapter(s.toString());
                }
                else
                {
                    cdata.clear();
                    recyclerView.removeAllViews();
                }

            }

            private void setAdapter(final String searchedstring) {
                cdata.clear();
                recyclerView.removeAllViews();

                mData.addValueEventListener( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int count=0;
                        for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren())
                        {

                            for(DataSnapshot myd : dataSnapshot1.getChildren())
                            {
                                String mn=myd.getKey().toString();
                                ChildInfo childData=myd.getValue(ChildInfo.class);
                                if(searchedstring.equals( mn ))
                                {
                                    cdata.add( childData );
                                    count++;
                                }

                                if(count==10)
                                    break;


                            }

                        }
                        adapter=new RecyclerViewAdapterFamInfo( Search.this,cdata );
                        recyclerView.setAdapter( adapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );
            }
        } );

    }

}
