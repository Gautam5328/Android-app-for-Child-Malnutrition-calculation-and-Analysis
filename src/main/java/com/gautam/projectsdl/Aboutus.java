package com.gautam.projectsdl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

public class Aboutus extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i1=new Intent( Aboutus.this,LoginActivity.class );
        startActivity( i1 );
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);


    }
}
