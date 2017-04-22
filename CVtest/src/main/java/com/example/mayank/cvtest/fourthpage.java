package com.example.mayank.cvtest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Mayank on 12/5/2016.
 */
public class fourthpage extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView serp;
    String fin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_one);
        fin = getIntent().getStringExtra("mayank");
        TextView serp= (TextView) findViewById(com.example.mayank.cvtest.R.id.url_data1);
        serp.setText(fin);

    }



}
