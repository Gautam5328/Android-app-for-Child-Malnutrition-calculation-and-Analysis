package com.gautam.projectsdl;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

public class myGotoLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_my_goto_login );
//        ProgressDialog progressDialog;
//        progressDialog = new ProgressDialog(myGotoLogin.this);
//
//        progressDialog.setMessage("Loading Data Database");
//
//        progressDialog.show();
        gotolog();
    }

    private void gotolog() {
        Intent i1=new Intent( myGotoLogin.this,LoginActivity.class );
        startActivity( i1 );
    }
}
