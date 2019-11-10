package com.gautam.projectsdl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class InfoAdded extends AppCompatActivity {

    DatabaseReference mdata,compdata;
    ListView listView;
    Map<String,String> data=new HashMap<String, String>( 13 );
    ChildInfo childInfo=new ChildInfo(  );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_info_added );

        listView=(ListView)findViewById( R.id.lv );
        Intent i=getIntent();
        childInfo=(ChildInfo)i.getSerializableExtra( "SampleObject" );
        String[] FamInfo={
                childInfo.getFname(),childInfo.getMothname(),childInfo.getAdd(),childInfo.getDob(),childInfo.getDeliv(),
                childInfo.getOccup(),childInfo.getFood(),childInfo.getLat(),childInfo.getLon(),childInfo.getMob()
        };

        ArrayAdapter adapter=new ArrayAdapter<String >(this,R.layout.activity_listview,FamInfo);
        listView.setAdapter( adapter );
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

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


        mdata= FirebaseDatabase.getInstance().getReference().child( mydb).child( "FamInfo" ).child( date ).child(childInfo.getMob());
        adddata();
    }

    public  void adddata()
    {
        mdata.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {




                data.put( "fname",childInfo.getFname() );
                data.put( "mothname",childInfo.getMothname() );
                data.put( "add",childInfo.getAdd() );
                data.put( "occup",childInfo.getOccup() );
                data.put( "dob",childInfo.getDob() );
                data.put( "deliv",childInfo.getDeliv() );
                data.put( "food",childInfo.getFood() );

                data.put( "Lat",childInfo.getLat() );
                data.put( "Lon",childInfo.getLon() );
                data.put( "mob",childInfo.getMob() );


                mdata.setValue( data ).addOnCompleteListener( InfoAdded.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText( InfoAdded.this,"Data Inserted",Toast.LENGTH_SHORT ).show();
                        }
                        else
                        {
                            Toast.makeText( InfoAdded.this,"Data Insertion Error",Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }






}
