package com.softsofware.javiercalette.proyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class EventosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEventos;
    private EventAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        recyclerViewEventos=(RecyclerView)findViewById(R.id.list1);
        recyclerViewEventos.setLayoutManager(new LinearLayoutManager(this));

        String f = getIntent().getExtras().getString("date_q");

        base b = new base(getApplicationContext());

        adaptador=new EventAdapter(b.listar(f));
        recyclerViewEventos.setAdapter(adaptador);
    }
}
