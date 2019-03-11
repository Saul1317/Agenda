package com.example.agenda;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterRecyclerviewContacto extends RecyclerView.Adapter<AdapterRecyclerviewContacto.AdapterRecyclerviewContactorHolder>{

    private int resource;
    private List<Agenda> contacto_agenda;
    private Activity activity;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public AdapterRecyclerviewContacto(int resource, List<Agenda> contacto_agenda, Activity activity, Context context, OnItemClickListener onItemClickListener) {
        this.resource = resource;
        this.contacto_agenda = contacto_agenda;
        this.activity = activity;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AdapterRecyclerviewContactorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup,false);
        return new AdapterRecyclerviewContactorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerviewContactorHolder holder, final int position) {
        final Agenda agenda = contacto_agenda.get(position);
        holder.cardview_nombre_contacto.setText(agenda.getNombre_contacto());
        holder.cardview_phone_contacto.setText(agenda.getTelefono());
        holder.cardview_birthday_contacto.setText(agenda.getCumpleanos());
        holder.cardview_note_contacto.setText(agenda.getNota());

        holder.cardview_contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.UpdateItem(position, agenda);
                }
            }
        });

        holder.cardview_contacto.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.DeleteItem(position, agenda.getId_agenda(), contacto_agenda);
                }
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return contacto_agenda.size();
    }

    public class AdapterRecyclerviewContactorHolder extends RecyclerView.ViewHolder{

        CardView cardview_contacto;
        TextView cardview_nombre_contacto, cardview_phone_contacto, cardview_birthday_contacto, cardview_note_contacto;


        public AdapterRecyclerviewContactorHolder(View itemView) {
            super(itemView);
            cardview_nombre_contacto = (TextView) itemView.findViewById(R.id.cardview_nombre_contacto);
            cardview_phone_contacto = (TextView) itemView.findViewById(R.id.cardview_phone_contacto);
            cardview_birthday_contacto = (TextView) itemView.findViewById(R.id.cardview_birthday_contacto);
            cardview_note_contacto = (TextView) itemView.findViewById(R.id.cardview_note_contacto);
            cardview_contacto = (CardView) itemView.findViewById(R.id.cardview_contacto);
        }
    }
}
