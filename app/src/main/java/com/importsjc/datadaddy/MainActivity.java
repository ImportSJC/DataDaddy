package com.importsjc.datadaddy;

//import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.importsjc.datadaddy.Modules.CustomValue;
import com.importsjc.datadaddy.Modules.DataPoint;
import com.importsjc.datadaddy.Modules.IListItem;
import com.importsjc.datadaddy.Modules.InterfaceAdapter;
import com.importsjc.datadaddy.Modules.Storage;
import com.importsjc.datadaddy.Modules.Template;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MyAdapter.ItemClickListener{

    List<String> data;
    
    RecyclerView myRecView;
    MyAdapter myAdapter;

    public static final String SAVE_FILE = "filename.txt";
    public static final String URL_FILE = "url.txt";

    public static String endpointUrl = "No URL Data";
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

        loadData(getApplicationContext());

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
                Intent myIntent = new Intent(getApplicationContext(), NewTemplate.class);
                startActivityForResult(myIntent, 0);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            
            return true;
        }

        return super.onKeyDown(keyCode, event);
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
            Intent myIntent = new Intent(this, Settings.class);
            startActivity(myIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {
        if(view.getId() == R.id.delete_item){
            currentTemplateIndex = position;
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("Delete Group: '" + templateList.get(currentTemplateIndex).getName() + "'?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }else{
            currentTemplateIndex = position;
            Intent myIntent = new Intent(this, DataEventListActivity.class);
            startActivity(myIntent);
            finish();
        }
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    String tmpName = templateList.get(currentTemplateIndex).getName();
                    templateList.remove(currentTemplateIndex);
                    saveData(getApplicationContext(), null);
                    Toast.makeText(getApplicationContext(), "Group: '" + tmpName + "' deleted", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    Toast.makeText(getApplicationContext(), "Make up your mind! ;)", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public static void loadData(Context context){
        String myStr = "";
        String myStr2 = "";
        try{
            FileInputStream inputStream = context.openFileInput(SAVE_FILE);
            int i = 0;
            while((i=inputStream.read())!=-1){
                myStr += (char)i;
            }

            FileInputStream inputStream2 = context.openFileInput(URL_FILE);
            int i2 = 0;
            while((i2=inputStream2.read())!=-1){
                myStr2 += (char)i2;
            }

            GsonBuilder myBuilder = new GsonBuilder();
            myBuilder.registerTypeAdapter(IListItem.class, new InterfaceAdapter());
            Gson gson = myBuilder.create();

            Storage myStore = gson.fromJson(myStr, Storage.class);
            MainActivity.templateList = (List<IListItem>)(List<?>)myStore.getItemList();

            String myUrl = gson.fromJson(myStr2, String.class);
            endpointUrl = myUrl;
        }catch(IOException ioe){

        }

    }

    public static void saveData(Context context, String stringyData){
        try{
            GsonBuilder myBuilder = new GsonBuilder();
            myBuilder.registerTypeAdapter(IListItem.class, new InterfaceAdapter());
            Gson gson = myBuilder.create();

            List<Template> myList = (List<Template>)(List<?>)MainActivity.templateList;
            Storage myStore = new Storage(myList);
            String text = gson.toJson(myStore);


            FileOutputStream fOut = context.openFileOutput(SAVE_FILE, MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(text);
            osw.flush();
            osw.close();

            if(stringyData != null){
                String myURL = gson.toJson(stringyData);
                FileOutputStream fOut2 = context.openFileOutput(URL_FILE, MODE_PRIVATE);
                OutputStreamWriter osw2 = new OutputStreamWriter(fOut2);
                osw2.write(myURL);
                osw2.flush();
                osw2.close();

                endpointUrl = stringyData;
            }
        }catch(IOException ioe){

        }
    }

    public static void sendData(Context context){
        RequestQueue myQueue = Volley.newRequestQueue(context);

        JSONObject jsonBody = ((DataPoint) ((Template) MainActivity.templateList.get(MainActivity.currentTemplateIndex)).getDataPointList().get(MainActivity.currentDataPointIndex)).getJSONObject();
        final String requestBody = jsonBody.toString();

        StringRequest myRequest = new StringRequest(Request.Method.POST, endpointUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("DEV", "Server response: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("DEV", "ERROR: " + error);
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        myQueue.add(myRequest);
    }
}
