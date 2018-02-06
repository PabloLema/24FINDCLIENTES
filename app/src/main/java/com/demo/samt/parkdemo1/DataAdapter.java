package com.demo.samt.parkdemo1;

/**
 * Created by theol on 19/1/2017.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.samt.parkdemo1.Modelo.Parqueadero;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Parqueadero item);
    }

    private ArrayList<Parqueadero> parqueaderos;
    private Context context;


    public DataAdapter(Context context,ArrayList<Parqueadero> parqueaderos) {
        this.context = context;
        this.parqueaderos = parqueaderos;

    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);//le ejecuto grid layaut para otra visualizacion
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_android.setText(parqueaderos.get(i).getNombre());
       // Picasso.with(context).load(parqueaderos.get(i).getImagen()).resize(120, 60).into(viewHolder.img_android);
    }

    @Override
    public int getItemCount() {
        return parqueaderos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_android;
        ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            tv_android = (TextView)view.findViewById(R.id.tv_android);
            img_android = (ImageView)view.findViewById(R.id.img_android);
        }
    }
}