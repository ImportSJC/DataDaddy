package com.importsjc.datadaddy;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Settings");

        Button force_reload = findViewById(R.id.force_reload);
        force_reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.loadData(getApplicationContext());
                Toast.makeText(getApplicationContext(), "App Data Loaded! (You shouldn't have to do that. Let your friendly neighborhood developer know that you needed this.)", Toast.LENGTH_LONG).show();
            }
        });

        TextView url_value = findViewById(R.id.server_url_value);
        url_value.setText(MainActivity.endpointUrl);

        Button save_url = findViewById(R.id.save_url_button);
        save_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView url_value = findViewById(R.id.server_url_value);
                MainActivity.saveData(getApplicationContext(), url_value.getText().toString());
                Toast.makeText(getApplicationContext(), "URL Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(myIntent, 0);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        finish();
        return true;
    }
}
