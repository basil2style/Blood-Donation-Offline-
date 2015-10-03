package com.makeinfo.giveblood;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.bluelinelabs.logansquare.LoganSquare;
import com.makeinfo.giveblood.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    private String mJsonStringsToParse;
    List<User> users;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJsonStringsToParse = readJson();

       try {
            users = LoganSquare.parseList(mJsonStringsToParse,User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
       // Toast.makeText(getApplicationContext(),String.valueOf(users.get(2).name),Toast.LENGTH_SHORT).show();
        adapter adapter = new adapter(getApplicationContext(),R.layout.item,users);
    //   ListView mylist = (ListView) findViewById(R.id.listView);
        setListAdapter(adapter);
    }

    private List<String> readJsonFromFile() {

        List<String> strings = new ArrayList<>();
        strings.add(readFile("B+ve.json"));

        return strings;
    }
    private String readJson() {
        return readFile("B+ve.json");
    }

    private String readFile(String filename) {
        StringBuilder sb = new StringBuilder();


        try {
            InputStream json = getAssets().open(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));


            String str;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }


            in.close();
        } catch (Exception e) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("The JSON file was not able to load properly. These tests won't work until you completely kill this demo app and restart it.")
                    .setPositiveButton("OK", null)
                    .show();
        }


        return sb.toString();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
