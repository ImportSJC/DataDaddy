package com.importsjc.datadaddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.importsjc.datadaddy.Modules.DataPoint;
import com.importsjc.datadaddy.Modules.Template;

public class EditDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Fill in current data
        TextView detail_name = findViewById(R.id.detail_name);
        detail_name.setText(((DataPoint)
                ((Template) MainActivity.templateList.get(MainActivity.currentTemplateIndex)).getDataPointList().get(MainActivity.currentDataPointIndex)).getName());


        TextView value1_name = findViewById(R.id.detail_value1_name);
        EditText value1_value = findViewById(R.id.detail_value1_value);

        TextView value2_name = findViewById(R.id.detail_value2_name);
        EditText value2_value = findViewById(R.id.detail_value2_value);

        TextView value3_name = findViewById(R.id.detail_value3_name);
        EditText value3_value = findViewById(R.id.detail_value3_value);

        if(((DataPoint)
                ((Template) MainActivity.templateList.get(MainActivity.currentTemplateIndex)).getDataPointList().get(MainActivity.currentDataPointIndex)).getValue1().isActive()) {

            value1_name.setText(((DataPoint)
                    ((Template) MainActivity.templateList.get(MainActivity.currentTemplateIndex)).getDataPointList().get(MainActivity.currentDataPointIndex)).getValue1().getName());

            value1_value.setText(((DataPoint)
                    ((Template) MainActivity.templateList.get(MainActivity.currentTemplateIndex)).getDataPointList().get(MainActivity.currentDataPointIndex)).getValue1().getValue());
        }else{
            value1_name.setVisibility(View.GONE);
            value1_value.setVisibility(View.GONE);
        }

        if(((DataPoint)
                ((Template) MainActivity.templateList.get(MainActivity.currentTemplateIndex)).getDataPointList().get(MainActivity.currentDataPointIndex)).getValue2().isActive()) {
            value2_name.setText(((DataPoint)
                    ((Template) MainActivity.templateList.get(MainActivity.currentTemplateIndex)).getDataPointList().get(MainActivity.currentDataPointIndex)).getValue2().getName());


            value2_value.setText(((DataPoint)
                    ((Template) MainActivity.templateList.get(MainActivity.currentTemplateIndex)).getDataPointList().get(MainActivity.currentDataPointIndex)).getValue2().getValue());
        }else{
            value2_name.setVisibility(View.GONE);
            value2_value.setVisibility(View.GONE);
        }

        if(((DataPoint)
                ((Template) MainActivity.templateList.get(MainActivity.currentTemplateIndex)).getDataPointList().get(MainActivity.currentDataPointIndex)).getValue3().isActive()) {
            value3_name.setText(((DataPoint)
                    ((Template) MainActivity.templateList.get(MainActivity.currentTemplateIndex)).getDataPointList().get(MainActivity.currentDataPointIndex)).getValue3().getName());


            value3_value.setText(((DataPoint)
                    ((Template) MainActivity.templateList.get(MainActivity.currentTemplateIndex)).getDataPointList().get(MainActivity.currentDataPointIndex)).getValue3().getValue());
        }else{
            value3_name.setVisibility(View.GONE);
            value3_value.setVisibility(View.GONE);
        }

        Button submit_button = findViewById(R.id.detail_submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(null);
            }
        });

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), DataEventListActivity.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }
}
