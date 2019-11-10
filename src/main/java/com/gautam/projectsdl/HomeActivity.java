package com.gautam.projectsdl;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeActivity extends AppCompatActivity{

    Double latitude,longitude;
    private EditText ht;
    private EditText wt, gend,mob;
    private Button calculate;
    private EditText gender;
    //int pflag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );
        FirebaseApp.initializeApp( HomeActivity.this );



        ht=(EditText)findViewById(R.id.height);
        wt=(EditText)findViewById(R.id.weight);
        calculate=(Button) findViewById(R.id.b);
        gend=(EditText)findViewById( R.id.gen );
        mob=(EditText)findViewById( R.id.mob );

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                int flag=0;
                if ((ht.getText().toString()).isEmpty()) {
                    ht.setError( "Please Enter Height" );
                    ht.requestFocus();
                }
                else if ((ht.getText().toString()).isEmpty()) {
                    wt.setError( "Please Enter Weight" );
                    wt.requestFocus();
                }
                else if(mob.getText().toString().length()!=10)
                {

                    Toast.makeText( HomeActivity.this,"ENter valid Mobile Number",Toast.LENGTH_SHORT ).show();
                    mob.setError( "ENter valid Mobile Number" );
                    mob.requestFocus();
                }

                else
                {
                    flag=1;
                }
                if(flag==1) {
                    Intent i1 = new Intent( HomeActivity.this, SecondCalc.class );
                    String h = ht.getText().toString().trim();
                    String w = wt.getText().toString();
                    String gen = gend.getText().toString();
                    String mo=mob.getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putString( "Ht", h );
                    bundle.putString( "Wt", w );
                    bundle.putString( "Gen", gen);
                    bundle.putString( "mn", mo );


                    i1.putExtras( bundle );
                    startActivity( i1 );

                }
                else
                {
                    Toast.makeText(HomeActivity.this,"ENter Correct values",Toast.LENGTH_SHORT).show();

                }


            }
        });
    }


}

