package com.makeinfo.giveblood.Fragments;


import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bluelinelabs.logansquare.LoganSquare;
import com.makeinfo.giveblood.R;
import com.makeinfo.giveblood.Test;
import com.makeinfo.giveblood.adapter;
import com.makeinfo.giveblood.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class bPositive extends ListFragment {

    Context context;
    public bPositive() {
        // Required empty public constructor
    }
    private String mJsonStringsToParse;
    List<User> users;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b_positive, container, false);



    }
    int order;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        order = Test.getValue();
        mJsonStringsToParse = readJson(order);

       // Toast.makeText(getActivity(),String.valueOf(order), Toast.LENGTH_SHORT).show();
        try {
            users = LoganSquare.parseList(mJsonStringsToParse, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Toast.makeText(getApplicationContext(),String.valueOf(users.get(2).name),Toast.LENGTH_SHORT).show();
        adapter adapter = new adapter(getActivity().getApplication(),R.layout.item,users);
        //   ListView mylist = (ListView) findViewById(R.id.listView);
        setListAdapter(adapter);
    }

    private String readJson(int order) {
        String file = null;
        switch (order){
            case 0 : file = readFile("A+ve.json");break;
            case 1 : file = readFile("A-ve.json");break;
            case 2 : file = readFile("AB+ve.json");break;
            case 3 : file = readFile("AB-ve.json");break;
            case 4 : file = readFile("B+ve.json");break;
            case 5 : file = readFile("B-ve.json");break;
            case 6 : file = readFile("O+ve.json");break;
            case 7 : file = readFile("O-ve.json");break;
        }
        return file;
    }

    private String readFile(String filename) {
        StringBuilder sb = new StringBuilder();


        try {
            InputStream json = getActivity().getAssets().open(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));


            String str;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }


            in.close();
        } catch (Exception e) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Error")
                    .setMessage("The JSON file was not able to load properly. These tests won't work until you completely kill this demo app and restart it.")
                    .setPositiveButton("OK", null)
                    .show();
        }


        return sb.toString();
    }


}
