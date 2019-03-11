package com.example.agenda;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ContactFragment extends Fragment {

    TextInputEditText id_contacto, nombre_contacto, telefono_contacto, cumpleanos_contacto, nota_contacto;
    FloatingActionButton fab_addContact;
    private ConexionSQLite conn;
    String TAG = "CONTACT_FRAGMENT";

    public ContactFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        conn = new ConexionSQLite(getContext(), "bd_agenda", null, SQLiteTablas.VERSION_BD);

        id_contacto = (TextInputEditText) view.findViewById(R.id.id_contacto);
        id_contacto.setEnabled(false);

        nombre_contacto = (TextInputEditText) view.findViewById(R.id.nombre_contacto);
        telefono_contacto = (TextInputEditText) view.findViewById(R.id.telefono_contacto);
        cumpleanos_contacto = (TextInputEditText) view.findViewById(R.id.cumpleanos_contacto);
        nota_contacto = (TextInputEditText) view.findViewById(R.id.nota_contacto);

        Bundle args = getArguments();
        if (args != null) {
            id_contacto.setText(args.getString("id"));
            nombre_contacto.setText(args.getString("nombre"));
            telefono_contacto.setText(args.getString("phone"));
            cumpleanos_contacto.setText(args.getString("birdthday"));
            nota_contacto.setText(args.getString("note"));
        }


        fab_addContact = (FloatingActionButton) view.findViewById(R.id.fab_addContact);
        fab_addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidarDatos(id_contacto.getText().toString(), nombre_contacto.getText().toString(),
                        telefono_contacto.getText().toString(), cumpleanos_contacto.getText().toString(), nota_contacto.getText().toString());
            }
        });
        return view;
    }

    private void ValidarDatos(String id, String nombre, String phone, String birthday, String nota) {
        if (nombre.isEmpty()){
            nombre_contacto.setError("Campo obligatorio");
        }
        else if(phone.isEmpty()){
            telefono_contacto.setError("Campo obligatorio");
        }
        else if(birthday.isEmpty()){
            cumpleanos_contacto.setError("campo obligatorio");
        }
        else{
            if(id.isEmpty()){
                InsertarDatos(nombre, phone, birthday, nota);
            }else{
                ActualizarDatos(id, nombre, phone, birthday, nota);
            }
        }
    }

    private void ActualizarDatos(String id, String nombre, String phone, String birthday, String nota) {
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SQLiteTablas.CAMPO_CONTACTO_AGENDA, nombre);
        values.put(SQLiteTablas.CAMPO_PHONE_AGENDA,phone);
        values.put(SQLiteTablas.CAMPO_CUMPLEANO_AGENDA, birthday);
        values.put(SQLiteTablas.CAMPO_NOTA_AGENDA, nota);

        db.update(SQLiteTablas.TABLA_NOMBRE, values, SQLiteTablas.CAMPO_ID_AGENDA + " = " + id, null);
        db.close();
        Toast.makeText(getContext(), "Se actualizó el contacto correctamente", Toast.LENGTH_SHORT).show();
        OpenListFragment();
    }

    private void InsertarDatos(String nombre, String phone, String birthday, String nota){
        SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(SQLiteTablas.CAMPO_ID_AGENDA, id_contacto.getText().toString());
        values.put(SQLiteTablas.CAMPO_CONTACTO_AGENDA, nombre);
        values.put(SQLiteTablas.CAMPO_PHONE_AGENDA, phone);
        values.put(SQLiteTablas.CAMPO_CUMPLEANO_AGENDA, birthday);
        values.put(SQLiteTablas.CAMPO_NOTA_AGENDA, nota);

        Long idResultado = sqLiteDatabase.insert(SQLiteTablas.TABLA_NOMBRE, SQLiteTablas.CAMPO_ID_AGENDA, values);
        sqLiteDatabase.close();
        if(idResultado != -1) {
            Log.e(TAG, "Todo chido");
            Toast.makeText(getContext(), "Se inserto correctamente", Toast.LENGTH_SHORT).show();
            OpenListFragment();
        }
        else{
            Log.e(TAG, "ERROR");
        }
    }

    private void OpenListFragment(){
        AgendaListFragment contactFragment= new AgendaListFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        transaction.replace(R.id.fragment_agenda, contactFragment, "findThisFragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
