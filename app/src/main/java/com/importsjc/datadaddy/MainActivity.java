package com.importsjc.datadaddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

import com.importsjc.datadaddy.Modules.CustomValue;
import com.importsjc.datadaddy.Modules.DataPoint;
import com.importsjc.datadaddy.Modules.IListItem;
import com.importsjc.datadaddy.Modules.Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.ItemClickListener{

    List<String> data;
    
    RecyclerView myRecView;
    MyAdapter myAdapter;

    public static String testString = "Test String";
    public static String endpointUrl = "n/a";
    private static List<IListItem> myList = new ArrayList<IListItem>(Arrays.asList(new DataPoint("Test DataPoint", new CustomValue("Price", "45.34"), new CustomValue("Tag", "Gas"), new CustomValue(false))));
    public static Template testTemplate = new Template("Test Template", myList);
    public static List<IListItem> templateList = new ArrayList<IListItem>(Arrays.asList(testTemplate));

    //Updated helper vars
    public static int currentTemplateIndex = -1;
    public static int currentDataPointIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        myRecView = findViewById(R.id.my_recycler_view);
        myRecView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myRecView.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter(this, templateList);
        myAdapter.setClickListener(this);
        myRecView.setAdapter(myAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(myRecView.getContext(),
                layoutManager.getOrientation());
        myRecView.addItemDecoration(dividerItemDecoration);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertSingleItem();
            }
        });
    }

    private void insertSingleItem() {
//        String item = "Pig";
//        int insertIndex = data.size();
//        data.add(insertIndex, item);
//        myAdapter.notifyItemInserted(insertIndex);
        Intent myIntent = new Intent(this, DataEventListActivity.class);
        startActivity(myIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent myIntent = new Intent(this, DataEventListActivity.class);
            startActivity(myIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {
        currentTemplateIndex = position;
        Intent myIntent = new Intent(this, DataEventListActivity.class);
        startActivity(myIntent);
        finish();
//        Toast.makeText(this, "You click " + myAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    //TODO add function to alter the template
}
