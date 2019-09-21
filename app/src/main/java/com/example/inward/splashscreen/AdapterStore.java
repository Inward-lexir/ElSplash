package com.example.inward.splashscreen;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import modelos.Store;

public class AdapterStore extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Store> list;

    public AdapterStore(Activity activity, ArrayList<Store> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v==null){

            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.item_store,null);

        }

        Store store = list.get(position);

        TextView lblNombre, lblDescripcion;
        lblNombre = v.findViewById(R.id.lblNameItemStore);
        lblDescripcion = v.findViewById(R.id.lblDescripcionItemStore);

        lblNombre.setText(store.getNombre());
        lblDescripcion.setText(store.getDescripcion());

        return null;
    }
}
