package com.gautam.projectsdl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bar_chart );
        Bundle bundle=getIntent().getExtras();
        String a,b,c;
        a=bundle.getString( "Normal" );
        b=bundle.getString( "Severe" );
        c=bundle.getString( "Moderate" );



        System.out.println("Data is "+a + "\t "+ b + "\t " + "\t "+c);

        String msg="Data is "+a + "\t "+ b + "\t " + "\t "+c;

        BarChart chart=findViewById( R.id.barchart );


        ArrayList noofmaln=new ArrayList(  );

        noofmaln.add( new BarEntry( Float.parseFloat( a ),0 ) );
        noofmaln.add( new BarEntry( Float.parseFloat( b ),1 ) );
        noofmaln.add( new BarEntry( Float.parseFloat( c ),2 ) );
        BarDataSet bardataSet=new BarDataSet( noofmaln,"Malnutition Data" );

        BarData data = new BarData(bardataSet);

        chart.setData( data );

        bardataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        bardataSet.setValueTextColor(Color.BLACK);
        bardataSet.setValueTextSize(18f);


    }
}
