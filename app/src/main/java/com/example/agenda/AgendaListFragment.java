package com.example.agenda;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AgendaListFragment extends Fragment {
    private ConexionSQLite conn;
    private RecyclerView recycler_contact;
    private AdapterRecyclerviewContacto adapterRecyclerviewContacto;
    private FloatingActionButton fab_addContact;
    LinearLayoutManager linearLayoutManagerExtras;

    public AgendaListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agenda_list, container, false);
        conn = new ConexionSQLite(getContext(), "bd_agenda", null, SQLiteTablas.VERSION_BD);

        recycler_contact = (RecyclerView) view.findViewById(R.id.recycler_contact);
        linearLayoutManagerExtras = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler_contact.setLayoutManager(linearLayoutManagerExtras);
        getContact(conn);

        fab_addContact = (FloatingActionButton) view.findViewById(R.id.fab_addContact);
        fab_addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactFragment contactFragment= new ContactFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.replace(R.id.fragment_agenda, contactFragment, "findThisFragment");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

    private void getContact(ConexionSQLite conn) {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<Agenda> agendaList = new ArrayList<Agenda>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteTablas.TABLA_NOMBRE, null);
            while(cursor.moveToNext()){
                Agenda agenda = new Agenda(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
                agendaList.add(agenda);
            }
            cursor.close();
            sqLiteDatabase.close();
            setAgendaList(agendaList);
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
        }
    }

    public void setAgendaList(List<Agenda> agendaList) {
        adapterRecyclerviewContacto = new AdapterRecyclerviewContacto(R.layout.cardview_contact, agendaList, getActivity(), getContext(), new OnItemClickListener() {
            @Override
            public void UpdateItem(int position, Agenda agenda) {

                Bundle args = new Bundle();
                args.putString("id", String.valueOf(agenda.getId_agenda()));
                args.putString("nombre", agenda.getNombre_contacto());
                args.putString("phone", agenda.getTelefono());
                args.putString("birdthday", agenda.getCumpleanos());
                args.putString("note", agenda.getNota());

                ContactFragment contactFragment= new ContactFragment();
                contactFragment.setArguments(args);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.replace(R.id.fragment_agenda, contactFragment, "findThisFragment");
                transaction.addToBackStack(null);
                transaction.commit();
            }

            @Override
            public void DeleteItem(int position, int id_agenda, List<Agenda> agendaList) {
                DeleteSqlItem(position, id_agenda, agendaList);
            }
        });
        recycler_contact.setAdapter(adapterRecyclerviewContacto);
    }

    private void DeleteSqlItem(int position, int id_agenda, List<Agenda> agendaList) {
        SQLiteDatabase db = conn.getReadableDatabase();
        db.execSQL("delete from "+ SQLiteTablas.TABLA_NOMBRE +" where "+ SQLiteTablas.CAMPO_ID_AGENDA + " = '" + id_agenda + "'");
        db.close();
        removeItem(position, agendaList);
    }

    private void removeItem(int position, List<Agenda> agendaList) {
        agendaList.remove(position);
        adapterRecyclerviewContacto.notifyItemRemoved(position);
        adapterRecyclerviewContacto.notifyItemRangeChanged(position, agendaList.size());
    }
}
