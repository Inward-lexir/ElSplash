package com.example.inward.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import modelos.Store;

public class MenuActivity extends AppCompatActivity {

    ListView list;

    AdapterStore adapterStore;
    ArrayList<Store> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        list.findViewById(R.id.listTiendas);

        arrayList = new ArrayList<Store>();

        arrayList.add(new Store("tiendaUno", "descripcionUno"));
        arrayList.add(new Store("tiendaDos", "descripcionDos"));

        adapterStore = new AdapterStore(this, arrayList);

        list.setAdapter(adapterStore);
    }
}
