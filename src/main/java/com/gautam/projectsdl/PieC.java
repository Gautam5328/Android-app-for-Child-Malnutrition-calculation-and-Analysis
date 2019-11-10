package com.gautam.projectsdl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieC extends AppCompatActivity {

    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pie_c );

        Bundle bundle=getIntent().getExtras();
        String a,b,c;
        a=bundle.getString( "Normal" );
        b=bundle.getString( "Severe" );
        c=bundle.getString( "Moderate" );



        System.out.println("Data is "+a + "\t "+ b + "\t " + "\t "+c);

        String msg="Data is "+a + "\t "+ b + "\t " + "\t "+c;



        PieChart pieChart=(PieChart)findViewById( R.id.piechart );
        ArrayList noofchild=new ArrayList(  );

        noofchild.add( new PieEntry( Float.parseFloat(  a),"Normal" ) );
        noofchild.add( new PieEntry( Float.parseFloat(  b),"Severe" ) );
        noofchild.add( new PieEntry( Float.parseFloat(  c),"Moderate" ) );
        PieDataSet dataSet=new PieDataSet( noofchild,"Malnutition Data" );
        ArrayList <String >result=new ArrayList<String>(  );
        result.add( "Normal" );
        result.add( "Severe" );
        result.add( "Moderate" );

        PieData data=new PieData( dataSet );
        pieChart.setData(data);
        dataSet.setColors( ColorTemplate.COLORFUL_COLORS);
        dataSet.setSliceSpace(2f);
        dataSet.setValueTextColor( Color.WHITE);
        dataSet.setValueTextSize(10f);
        dataSet.setSliceSpace(5f);
//        pieChart.invalidate();

    }
}
