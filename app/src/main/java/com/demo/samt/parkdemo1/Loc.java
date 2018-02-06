package com.demo.samt.parkdemo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.demo.samt.parkdemo1.Modelo.Local;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dario on 16/1/18.
 */

public class Loc  extends AppCompatActivity implements OnTaskCompleted{
    public String ip = "35.229.114.53:80";
    ClienteRest clienteRest;
    String usuario1;
    private int WS_CONSULTA = 1;
    public List<Local> locales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc);
        usuario1 = getIntent().getExtras().getString("usuariol");
        //EJECUTAMOS EL METODO INGRESAR UNA VEZ GUARDADOS LOS DATOS DEL WS A UNA ARRAYLIST
        //showMensaje(""+usuario1);
        ingresar();
    }

    private void ingresar(){
        //CLIENTE REST PARA CONSULTAR DE QUE SI ME DEVUELVE UN OK EL USUARIO(DUEÑO PARQUEADERO F) ES VALIDO
        clienteRest = new ClienteRest(this);
        try {
            //CONSULTA AL WS
            String url="http://"+ip+"/24/srv/24find/listadolocal?loc_cli_ci="+usuario1;

            //GUARDAMOS EL RESULTADO DE LA CONSULTA
            clienteRest.doGet(url,null,WS_CONSULTA,true);
        }catch (Exception e){
            showMensaje("Error al consultar");
            e.printStackTrace();
        }
    }


    @Override
    public void onTaskCompleted(int idSolicitud) {
        if(idSolicitud == WS_CONSULTA){
            if(!clienteRest.isCancelled()){
                //LISTA DE PARQUEADEROS
                locales=clienteRest.getResultList(Local.class);
                //showMensaje(""+locales);
                initViews();
                showMensaje("TOTAL: "+locales.size());
            }
        }
    }

    private void showMensaje(String mensaje){
        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        toast.show();
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view1);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        //CREAMOS UNA LISTA PARA GUARDAR LOS PARQUEADEROS DE X USUARIO
        ArrayList androidVersions = prepareData();
        DataAdapter1 adapter = new DataAdapter1(getApplicationContext(),androidVersions);
        recyclerView.setAdapter(adapter);
        //AGREGAMOS AQUI EL EVENTO DE CLICK PARA QUE CADA UNO DE LOS BOTONES DE LOS PARQUEADEROS DEVUELVAN UN EVENTO
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Local local = locales.get(position);
                //Toast.makeText(getApplicationContext(), local.getNombre() + " is selected!", Toast.LENGTH_SHORT).show();
                //Intent conta = new Intent(this, parqueoAdmin.class)
                Intent intent = new Intent(view.getContext(), InformacionLocales.class);
                intent.putExtra("uno", local.getId());
                intent.putExtra("dos", local.getId());
                //intent.putExtra("descripcion", local.getDescripcion());
                //intent.putExtra("nombre", local.getNombre());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
    //AQUI GUARDAMOS UNICAMENTE LO QUE MOSTRARIAMOS EN LA LISTA DE PARQUEADEROS
    //LO QUE SERIA EL NOMBRE DEL PARQUEADERO Y LA IMAGEN
    private ArrayList prepareData(){

        ArrayList android_version = new ArrayList<>();
        for(int i=0;i<locales.size();i++){
            Local androidVersion = new Local();
            androidVersion.setNombre(locales.get(i).getNombre());
            //androidVersion.setImagen(locales.get(i).getImagen());
            android_version.add(androidVersion);
        }
        return android_version;
    }
}
