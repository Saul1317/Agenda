package com.example.agenda;

import java.util.List;

public interface OnItemClickListener {
    void UpdateItem(int position, Agenda agenda);
    void DeleteItem(int position, int id_contacto, List<Agenda> agendaList);
}
