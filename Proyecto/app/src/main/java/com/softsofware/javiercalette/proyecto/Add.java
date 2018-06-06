package com.softsofware.javiercalette.proyecto;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Spinner spinner = (Spinner) findViewById(R.id.events);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.eventos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.status);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);

        Spinner aux = (Spinner)findViewById(R.id.contacts);
        ArrayList<String> con = this.getDatos();
        ArrayAdapter<String> s3 =  new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, con);
        aux.setAdapter(s3);
    }

    public ArrayList<String> getDatos(){

        String[] r = new String[]{
                ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.TYPE
        };

        String query = ContactsContract.Data.MIMETYPE + "='"+
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

        String s = ContactsContract.Data.DISPLAY_NAME + " ASC";

        Cursor cContacto = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                r,
                query,
                null,
                s
        );

        ArrayList<String> d = new ArrayList<>();

        while(cContacto.moveToNext()){
            d.add(cContacto.getString(1));
        }

        return d;
    }
}
