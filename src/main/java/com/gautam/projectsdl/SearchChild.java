package com.gautam.projectsdl;


import androidx.appcompat.app.AppCompatActivity;

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

public class SearchChild extends AppCompatActivity {


    EditText searchtext;
    RecyclerView recyclerView;
    DatabaseReference mData;
    Button b1;
    RecyclerViewAdapter adapter;
    ArrayList<ChildData> cdata=new ArrayList<>(  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search_child );

        recyclerView=(RecyclerView)findViewById( R.id.recyclerView );

        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( SearchChild.this ) );
        recyclerView.addItemDecoration( new DividerItemDecoration( SearchChild.this , LinearLayout.VERTICAL ) );

        searchtext=(EditText)findViewById( R.id.searc );

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        Toast.makeText(this, "" + currentFirebaseUser.getEmail(), Toast.LENGTH_SHORT).show();

        String useremail=currentFirebaseUser.getEmail();
        String mydb;
        if(useremail.contentEquals( "admin@gmail.com" ))
        {
            mydb="DataChild";
        }
        else if(useremail.contentEquals( "abcd@gmail.com" ))
        {
            mydb="DataChild3";
        }
        else if(useremail.contentEquals( "abc@gmail.com" ))
        {
            mydb="DataChild4";
        }

        else if(useremail.contentEquals( "gautam@gmail.com" ))
        {
            mydb="DataChild5";
        }
        else{

            mydb="DataChild2";
        }





        mData= FirebaseDatabase.getInstance().getReference().child( mydb).child( "ChildDB" );

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
                            String tdate=dataSnapshot1.getKey();
                            for(DataSnapshot myd : dataSnapshot1.getChildren())
                            {
                                String mn=myd.getKey().toString();
                                ChildData childData=myd.getValue(ChildData.class);
                                childData.mob=mn;
                                childData.ddate=tdate;
                                if(searchedstring.equals( mn ))
                                {
                                    cdata.add( childData );
                                    count++;
                                }

                                if(count==10)
                                    break;


                            }

                        }
                        adapter=new RecyclerViewAdapter( SearchChild.this,cdata );
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

