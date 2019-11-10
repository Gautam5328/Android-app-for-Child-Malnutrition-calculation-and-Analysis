package com.gautam.projectsdl;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Pie_Chart extends AppCompatActivity {
    private DatabaseReference mData;
    Button pie,bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pie_chart );

        pie=(Button)findViewById( R.id.pieb );
        bar=(Button)findViewById( R.id.barb );

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


        mData = FirebaseDatabase.getInstance().getReference().child( mydb).child( "ChildDB" );

        pie.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.addValueEventListener( new ValueEventListener() {
                    int a=0,b=0,c=0;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ChildData childData = new ChildData();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {

                            for (DataSnapshot ds1 : ds.getChildren()) {
                                try {
                                    childData = ds1.getValue( ChildData.class );

                                    if(childData.Result.equalsIgnoreCase( "Normal" ))
                                    {
                                        a++;
                                    }
                                    else if(childData.Result.equalsIgnoreCase( "SAM" ))
                                    {
                                        b++;
                                    }
                                    else if(childData.Result.equalsIgnoreCase( "MAM" ))
                                    {
                                        c++;
                                    }
                                    // childData.Result = ds.getKey().toString();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        }

                        Intent i2=new Intent( Pie_Chart.this,PieC.class );
                        Bundle b1=new Bundle(  );
                        b1.putString( "Normal",String.valueOf(  a ));
                        b1.putString( "Severe",String.valueOf( b ) );
                        b1.putString( "Moderate",String.valueOf( c ) );
                        i2.putExtras( b1 );
                        startActivity( i2 );

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                } );

            }
        } );


        bar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mData.addValueEventListener( new ValueEventListener() {
                    int a=0,b=0,c=0;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ChildData childData = new ChildData();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {

                            for (DataSnapshot ds1 : ds.getChildren()) {
                                try {
                                    childData = ds1.getValue( ChildData.class );

                                    if(childData.Result.equalsIgnoreCase( "Normal" ))
                                    {
                                        a++;
                                    }
                                    else if(childData.Result.equalsIgnoreCase( "SAM" ))
                                    {
                                        b++;
                                    }
                                    else if(childData.Result.equalsIgnoreCase( "MAM" ))
                                    {
                                        c++;
                                    }
                                    // childData.Result = ds.getKey().toString();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        }

                        Intent i3=new Intent( Pie_Chart.this,BarC.class );
                        Bundle b1=new Bundle(  );
                        b1.putString( "Normal",String.valueOf(  a ));
                        b1.putString( "Severe",String.valueOf( b ) );
                        b1.putString( "Moderate",String.valueOf( c ) );
                        i3.putExtras( b1 );
                        startActivity( i3 );

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                } );

            }
        } );

    }



}
