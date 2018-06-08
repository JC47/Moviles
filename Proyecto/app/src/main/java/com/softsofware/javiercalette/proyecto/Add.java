package com.softsofware.javiercalette.proyecto;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class Add extends AppCompatActivity {
    Spinner spinner1,spinner2,aux;
    DatePicker d1;
    EditText t1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.d1 = (DatePicker)findViewById(R.id.date);
        this.t1 = (EditText) findViewById(R.id.hour);
        this.b1 = (Button)findViewById(R.id.send);

        this.spinner1 = (Spinner) findViewById(R.id.events);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.eventos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinner1.setAdapter(adapter);

        this.spinner2 = (Spinner) findViewById(R.id.status);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.states, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinner2.setAdapter(adapter1);

        this.aux = (Spinner)findViewById(R.id.contacts);
        ArrayList<String> con = this.getDatos();
        ArrayAdapter<String> s3 =  new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, con);
        this.aux.setAdapter(s3);

        this.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String evento = spinner1.getSelectedItem().toString();
                String fecha = d1.getDayOfMonth()+"/"+(d1.getMonth()+1)+"/"+d1.getYear();
                String hora = t1.getText().toString();
                String status = spinner2.getSelectedItem().toString();
                String contacto = aux.getSelectedItem().toString();

                base bd = new base(getApplicationContext());

                bd.agregarEvento(evento,fecha,hora,status,contacto);
                Toast.makeText(getApplicationContext(),"Guardado",Toast.LENGTH_SHORT).show();
            }
        });
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
