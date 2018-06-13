package com.softsofware.javiercalette.proyecto;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    NotificationCompat.Builder eBuilder;
    int mNotificationId = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button)findViewById(R.id.add);
        Button b2 = (Button)findViewById(R.id.query);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(view.getContext(),Add.class);
                startActivity(x);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(view.getContext(),searchActivity.class);
                startActivity(x);
            }
        });

        eBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(android.R.drawable.ic_menu_agenda)
                    .setContentTitle("Agenda")
                    .setContentText("Tienes eventos el dia de hoy");

        Date d = new Date();
        java.text.DateFormat df = java.text.DateFormat.getDateInstance();
        String a = df.format(d).substring(0,6).replace("0","");
        String b = df.format(d).substring(6);
        String total = a.concat(b);

        base n = new base(getApplicationContext());
        if(n.isBussy(total)){
            NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            manager.notify(mNotificationId,eBuilder.build());
        }

    }
}
