package com.softsofware.javiercalette.proyecto;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>{

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView name,date,hour,contact,status;
        ImageView img;

        public ViewHolder(View itemView){
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.event_name);
            date = (TextView)itemView.findViewById(R.id.event_date);
            hour = (TextView)itemView.findViewById(R.id.event_hour);
            status = (TextView)itemView.findViewById(R.id.event_status);
            contact = (TextView)itemView.findViewById(R.id.event_contact);
            img = (ImageView)itemView.findViewById(R.id.event_img);

        }
    }

    public List<Evento> eventsList;

    public EventAdapter(List<Evento> eventsList) {
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_layout,parent,false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(eventsList.get(position).getEvento());
        holder.contact.setText(eventsList.get(position).getContacto());
        holder.date.setText(eventsList.get(position).getFecha());
        holder.hour.setText(eventsList.get(position).getHora());
        holder.status.setText(eventsList.get(position).getStatus());
        holder.img.setImageResource(eventsList.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}
