package com.softsofware.javiercalette.proyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

        adaptador=new EventAdapter(getEventos());
        recyclerViewEventos.setAdapter(adaptador);
    }

    public List<Evento> getEventos(){
        List<Evento> e = new ArrayList<>();
        e.add(new Evento("Cita","12/12/12","12:12","Pendiente","Alfredo",R.drawable.event));
        e.add(new Evento("Junta","12/12/12","12:12","Pendiente","Alfredo",R.drawable.event));
        e.add(new Evento("Examen","12/12/12","12:12","Pendiente","Alfredo",R.drawable.event));
        return e;
    }
}
