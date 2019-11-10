package com.gautam.projectsdl;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.DeflaterOutputStream;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SecondCalc extends AppCompatActivity {

    ListView l1;
    DatabaseReference mData,putdata;
    String latitude,longitude;
    private FusedLocationProviderClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_calc);
        //retrieve data from Main Activity
        Bundle bundle=getIntent().getExtras();
        @NotNull final String Ht=bundle.getString("Ht");
        final  String Wt=bundle.getString("Wt");
        final String Gen,mob;
        Gen=bundle.getString("Gen");
        mob=bundle.getString("mn");
        client= LocationServices.getFusedLocationProviderClient( this );

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        Toast.makeText(this, "" + currentFirebaseUser.getEmail(), Toast.LENGTH_SHORT).show();

        String useremail=currentFirebaseUser.getEmail();


        l1=(ListView)findViewById( R.id.lv );
        @NotNull final Double weight= Double.parseDouble(Wt);
        checkpermission();


        String []childinfo={Ht,Wt,Gen,mob};
        ArrayAdapter adapter=new ArrayAdapter<String >(this,R.layout.activity_listview,childinfo);
        l1.setAdapter( adapter );


        mData=FirebaseDatabase.getInstance().getReference().child(String.valueOf(Integer.parseInt(Ht)-(Integer.parseInt("48"))));
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        //admin@gmail.com ->DataChild  ->FamInfo
        //abc@gmail.com ->DataChild2   ->Faminfo
        //iamo@gmail.com ->DataChild3   ->Faminfo
        //kan@gmail.com ->DataChild4    ->Faminfo
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




        putdata=FirebaseDatabase.getInstance().getReference().child(mydb).child( "ChildDB" ).child(date).child(mob);
        final JSONObject[] jsonObject=new JSONObject[1];
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    jsonObject[0]=new JSONObject(dataSnapshot.getValue().toString());

                    String key1=jsonObject[0].getString("1SD");
                    String key2=jsonObject[0].getString("2SD");
                    String key3=jsonObject[0].getString("3SD");
                    String key4=jsonObject[0].getString("4SD");
                    String key5=jsonObject[0].getString("1SDG");
                    String key6=jsonObject[0].getString("2SDG");
                    String key7=jsonObject[0].getString("3SDG");
                    String key8=jsonObject[0].getString("4SDG");
                    String key9=jsonObject[0].getString("Height");

                    //msg.setText(key1 +" \t " +key2+" \t " +key3 +" \t " +key4 +" \t " +key5 +" \t " +key6+" \t " +key7 +" \t " +key8 +" \t " +key9 +" \t ");

                    //condition to check malnutrition

                    //if between 70-80% or less than 2SD then Moderate else if between 60-70% then Acute ;
                    Map<String,String> data=new HashMap<String, String>( 7);
                    data.put("Height",Ht);
                    data.put("Weight",Wt);
                    data.put("Gender",Gen);

                    data.put( "lon",longitude );
                    data.put( "lat",latitude );


                    if(Gen.equalsIgnoreCase("Male") || Gen.equalsIgnoreCase("m"))
                    {
                        //arranged in SD1(key1)->SD2(key2)->SD3(key3)->SD4(key4) order;

                        if(weight>(Double.parseDouble((key4))) &&  weight<((Double.parseDouble((key3)))))
                        {
                            Toast.makeText(SecondCalc.this,"Severe Acute Malnutrition (SAM)",Toast.LENGTH_LONG).show();
                            //datatowrite.add("SAM");

//                            mData.child( "Height" ).setValue( Ht );
//                            mData.child( "Weight" ).setValue( Wt );
//                            mData.child( "Gender" ).setValue( Gen );
//                            mData.child( "Height" ).setValue( "SAM" );

//                            putdata.child("Height").setValue(Ht);
//                            putdata.child("Weight").setValue(Wt);
//                            putdata.child("Gender").setValue(Gen);
//                            putdata.child("Result").setValue("SAM");

                            //Verify if inserted
                            data.put( "Result","SAM" );

                            putdata.setValue(data).addOnCompleteListener(SecondCalc.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(SecondCalc.this, "Inserted Data in DB", Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(SecondCalc.this, "Not Inserted Data in DB", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }
                        else if(weight>(Double.parseDouble((key3))) &&  weight<((Double.parseDouble((key2)))))
                        {
                            Toast.makeText(SecondCalc.this,"Moderate Acute Malnutrition (MAM)",Toast.LENGTH_LONG).show();
                            //datatowrite.add("MAM");
                            data.put( "Result","MAM" );

//                            putdata.child("Height").setValue(Ht);
//                            putdata.child("Weight").setValue(Wt);
//                            putdata.child("Gender").setValue(Gen);
//                            putdata.child("Result").setValue("MAM");

                            //Verify if inserted

                            putdata.setValue(data).addOnCompleteListener(SecondCalc.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(SecondCalc.this, "Inserted Data in DB", Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(SecondCalc.this, "Not Inserted Data in DB", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(SecondCalc.this,"Normal",Toast.LENGTH_LONG).show();
                            data.put( "Result","Normal" );
//                            putdata.child("Height").setValue(Ht);
//                            putdata.child("Weight").setValue(Wt);
//                            putdata.child("Gender").setValue(Gen);
//                            putdata.child("Result").setValue("Normal");

                            //Verify if inserted

                            putdata.setValue(data).addOnCompleteListener(SecondCalc.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(SecondCalc.this, "Inserted Data in DB", Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(SecondCalc.this, "Not Inserted Data in DB", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                    else
                    {
                        if(weight>(Double.parseDouble((key8))) &&  weight<((Double.parseDouble((key7)))))
                        {
                            Toast.makeText(SecondCalc.this,"Severe Acute Malnutrition (SAM)",Toast.LENGTH_LONG).show();
                            data.put( "Result","SAM" );
//
//                            putdata.child("Height").setValue(Ht);
//                            putdata.child("Weight").setValue(Wt);
//                            putdata.child("Gender").setValue(Gen);
//                            putdata.child("Result").setValue("SAM");

                            //Verify if inserted

                            putdata.setValue(data).addOnCompleteListener(SecondCalc.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(SecondCalc.this, "Inserted Data in DB", Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(SecondCalc.this, "Not Inserted Data in DB", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }
                        else if(weight>(Double.parseDouble((key7))) &&  weight<((Double.parseDouble((key6)))))
                        {
                            Toast.makeText(SecondCalc.this,"Moderate Acute Malnutrition (MAM)",Toast.LENGTH_LONG).show();
                            data.put( "Result","MAM" );
//                            putdata.child("Height").setValue(Ht);
//                            putdata.child("Weight").setValue(Wt);
//                            putdata.child("Gender").setValue(Gen);
//                            putdata.child("Result").setValue("MAM");

                            //Verify if inserted

                            putdata.setValue(data).addOnCompleteListener(SecondCalc.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(SecondCalc.this, "Inserted Data in DB", Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(SecondCalc.this, "Not Inserted Data in DB", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(SecondCalc.this,"Normal",Toast.LENGTH_LONG).show();
                            data.put( "Result","Normal" );
//                            putdata.child("Height").setValue(Ht);
//                            putdata.child("Weight").setValue(Wt);
//                            putdata.child("Gender").setValue(Gen);
//                            putdata.child("Result").setValue("Normal");

                            //Verify if inserted

                            putdata.setValue(data).addOnCompleteListener(SecondCalc.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(SecondCalc.this, "Inserted Data in DB", Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(SecondCalc.this, "Not Inserted Data in DB", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public  void checkpermission()
    {
        if (ActivityCompat.checkSelfPermission( SecondCalc.this, ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission( SecondCalc.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions( new String[]{ACCESS_FINE_LOCATION}, 123 );
            return;
        }
        else
        {

            client.getLastLocation().addOnSuccessListener( SecondCalc.this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location!=null) {
                        String msg = location.getLongitude() + " \t " + location.getLatitude();
                        Toast.makeText( SecondCalc.this, msg, Toast.LENGTH_SHORT ).show();
                        longitude=String.valueOf(  location.getLongitude());
                        latitude=String.valueOf(  location.getLatitude());

                    }
                }
            } );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case 123:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    client.getLastLocation().addOnSuccessListener( SecondCalc.this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if(location!=null) {
                                //  pflag=1;
                                longitude= String.valueOf( (location.getLongitude()) );
                                latitude= String.valueOf( location.getLatitude() );
                                String msg = location.getLongitude() + " \t " + location.getLatitude();
                                Toast.makeText( SecondCalc.this, msg, Toast.LENGTH_SHORT ).show();

                            }
                        }
                    } );
                }
                else
                {
                    Toast.makeText( this,"Permission Denied",Toast.LENGTH_SHORT ).show();
                }
                break;
            default:
                super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        }

    }
}

