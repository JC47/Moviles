package com.softsofware.javiercalette.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class searchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Button a = (Button)findViewById(R.id.btnS);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker p = (DatePicker)findViewById(R.id.date1);
                String fecha = p.getDayOfMonth()+"/"+(p.getMonth()+1)+"/"+p.getYear();

                Intent x = new Intent(getApplicationContext(),EventosActivity.class);
                x.putExtra("date_q",fecha);
                startActivity(x);
            }

        });
    }
}
