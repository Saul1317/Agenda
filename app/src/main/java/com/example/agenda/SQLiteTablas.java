package com.example.agenda;

public class SQLiteTablas {
    public static final int VERSION_BD = 1;

    public static final String TABLA_NOMBRE = "agenda";
    public static final String CAMPO_ID_AGENDA = "id_agenda";
    public static final String CAMPO_CONTACTO_AGENDA = "nombre_contacto";
    public static final String CAMPO_PHONE_AGENDA = "phone_contacto";
    public static final String CAMPO_CUMPLEANO_AGENDA = "cumpleanos_contacto";
    public static final String CAMPO_NOTA_AGENDA = "nota_agenda";

    public static final String CREAR_TABLA_AGENDA = "CREATE TABLE " + TABLA_NOMBRE +
            "("+CAMPO_ID_AGENDA+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            ""+CAMPO_CONTACTO_AGENDA+" TEXT  NOT NULL, " +
            ""+CAMPO_PHONE_AGENDA+" TEXT  NOT NULL, " +
            ""+CAMPO_CUMPLEANO_AGENDA+" DATE  NOT NULL, " +
            ""+ CAMPO_NOTA_AGENDA +" TEXT)";

}
