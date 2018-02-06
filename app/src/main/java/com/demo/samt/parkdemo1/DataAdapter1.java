package com.demo.samt.parkdemo1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.samt.parkdemo1.Modelo.Local;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Dario on 16/1/18.
 */

public class DataAdapter1 extends RecyclerView.Adapter<DataAdapter1.ViewHolder1>{
    public interface OnItemClickListener {
        void onItemClick(Local item);
    }

    private ArrayList<Local> locales;
    private Context context;


    public DataAdapter1(Context context,ArrayList<Local> locales) {
        this.context = context;
        this.locales = locales;

    }

    @Override
    public DataAdapter1.ViewHolder1 onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new DataAdapter1.ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter1.ViewHolder1 viewHolder1, int i) {

        viewHolder1.tv_android.setText(locales.get(i).getNombre());
        //Picasso.with(context).load(locales.get(i).getImagen()).resize(120, 60).into(viewHolder.img_android);
    }

    @Override
    public int getItemCount() {
        return locales.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{
        TextView tv_android;
        ImageView img_android;
        public ViewHolder1(View view) {
            super(view);

            tv_android = (TextView)view.findViewById(R.id.tv_android);
            img_android = (ImageView)view.findViewById(R.id.img_android);
        }
    }
}
