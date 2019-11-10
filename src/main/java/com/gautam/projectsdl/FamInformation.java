package com.gautam.projectsdl;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.annotations.NotNull;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import java.util.Calendar;

public class FamInformation extends AppCompatActivity {

    private static final String TAG ="MainActivity" ;
    DatePickerDialog.OnDateSetListener mdate;
    String latitude,longitude;
    private FusedLocationProviderClient client;

    EditText birth;
    TextInputLayout fname,mname,ad,cd,o,fg,db,ht,wt,mn;
    TextInputLayout parid;
    RadioGroup radioGroup;
    RadioButton radioButton;
    DatePickerDialog.OnDateSetListener mDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fam_information );

        db=(TextInputLayout)findViewById( R.id.db );
        fname=(TextInputLayout)findViewById( R.id.n );
        mname=(TextInputLayout)findViewById( R.id.mn );
        ad=(TextInputLayout)findViewById( R.id.a );
        cd=(TextInputLayout)findViewById( R.id.cd );
        o=(TextInputLayout)findViewById( R.id.o );
        fg=(TextInputLayout)findViewById( R.id.fg );
        birth=(EditText)findViewById( R.id.dob );

        mn=(TextInputLayout)findViewById( R.id.mob );
        birth.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year,month,day;
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get( Calendar.DAY_OF_MONTH );
                DatePickerDialog datePickerDialog=new DatePickerDialog( FamInformation.this,android.R.style.Theme_Holo_Dialog_MinWidth,mDate,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );
                datePickerDialog.show();
            }
        } );


        mDate=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                Log.d( TAG,"OnDateSet dd /mm/ yyyy" + dayOfMonth + " / " +month + " / " + year);
                birth.setText(dayOfMonth + "/"+month+"/"+year);
            }
        };

        Button b=(Button)findViewById( R.id.b1 );

        client= LocationServices.getFusedLocationProviderClient( this );

        checkpermission();




        radioGroup=(RadioGroup)findViewById( R.id.radio);
        b.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag=0;
                if(fname.getEditText().getText().toString().isEmpty() || mname.getEditText().getText().toString().isEmpty()
                        || db.getEditText().getText().toString().isEmpty() || cd.getEditText().getText().toString().isEmpty() ||
                        o.getEditText().getText().toString().isEmpty() || (radioGroup.getCheckedRadioButtonId()==-1)
                )
                {
                    Toast.makeText( FamInformation.this,"ENter valid data",Toast.LENGTH_SHORT ).show();
                }
                else if(mn.getEditText().getText().toString().length()!=10)
                {

                    Toast.makeText( FamInformation.this,"ENter valid Mobile Number",Toast.LENGTH_SHORT ).show();
                    mn.setError( "ENter valid Mobile Number" );
                    mn.requestFocus();
                }

                else
                {
                    int selectedbutton=radioGroup.getCheckedRadioButtonId();
                    radioButton=(RadioButton)findViewById( selectedbutton );

                    Toast.makeText( FamInformation.this,radioButton.getText().toString(),Toast.LENGTH_SHORT).show();
                    ChildInfo childInfo=new ChildInfo( fname.getEditText().getText().toString(),mname.getEditText().getText().toString(),
                            db.getEditText().getText().toString(),ad.getEditText().getText().toString(),
                            cd.getEditText().getText().toString(),o.getEditText().getText().toString(),radioButton.getText().toString(),fg.getEditText().getText().toString()
                            ,longitude,latitude,mn.getEditText().getText().toString());
                    Intent i=new Intent( FamInformation.this,InfoAdded.class );
                    i.putExtra( "SampleObject",childInfo );
                    startActivity( i );
                }
            }
        } );
    }

    public  void checkpermission()
    {
        if (ActivityCompat.checkSelfPermission( FamInformation.this, ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission( FamInformation.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions( new String[]{ACCESS_FINE_LOCATION}, 123 );
            return;
        }
        else
        {

            client.getLastLocation().addOnSuccessListener( FamInformation.this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location!=null) {
                        String msg = location.getLongitude() + " \t " + location.getLatitude();
                        Toast.makeText( FamInformation.this, msg, Toast.LENGTH_SHORT ).show();
                        longitude=String.valueOf(  location.getLongitude());
                        latitude=String.valueOf(  location.getLatitude());

                    }
                }
            } );
        }
    }
}
