package com.demo.samt.parkdemo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.demo.samt.parkdemo1.Modelo.Parqueadero;

import java.util.ArrayList;
import java.util.List;

public class parqueos extends AppCompatActivity implements OnTaskCompleted{
    public String ip = "35.229.114.53:80";
    ClienteRest clienteRest;
    String usuario;
    private int WS_CONSULTA = 1;
    List<Parqueadero> parqueaderos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parqueos);
        usuario = getIntent().getExtras().getString("usuario");
        //showMensaje(""+usuario);
        //EJECUTAMOS EL METODO INGRESAR UNA VEZ GUARDADOS LOS DATOS DEL WS A UNA ARRAYLIST
        ingresar();
    }

    private void ingresar(){
        //CLIENTE REST PARA CONSULTAR DE QUE SI ME DEVUELVE UN OK EL USUARIO(DUEÑO PARQUEADERO F) ES VALIDO
        clienteRest = new ClienteRest(this);
        try {
            //CONSULTA AL WS
            String url="http://"+ip+"/24/srv/24find/listadopark?par_cli_ci="+usuario;

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
                parqueaderos=clienteRest.getResultList(Parqueadero.class);
                initViews();
                showMensaje("TOTAL: "+parqueaderos.size());
            }
        }
    }

    private void showMensaje(String mensaje){
        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        toast.show();
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        //CREAMOS UNA LISTA PARA GUARDAR LOS PARQUEADEROS DE X USUARIO
        ArrayList androidVersions = prepareData();
        DataAdapter adapter = new DataAdapter(getApplicationContext(),androidVersions);
        recyclerView.setAdapter(adapter);
        //AGREGAMOS AQUI EL EVENTO DE CLICK PARA QUE CADA UNO DE LOS BOTONES DE LOS PARQUEADEROS DEVUELVAN UN EVENTO
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Parqueadero parqueadero = parqueaderos.get(position);
                //Toast.makeText(getApplicationContext(), parqueadero.getNombre() + " is selected!", Toast.LENGTH_SHORT).show();
                //Intent conta = new Intent(this, parqueoAdmin.class)
                Intent intent = new Intent(view.getContext(), parqueoAdmin.class);
                intent.putExtra("parqueo", parqueadero.getId());
                intent.putExtra("pd", parqueadero.getPuestosDisponibles());
                //intent.putExtra("pt", parqueadero.getCantidadPT());
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
        for(int i=0;i<parqueaderos.size();i++){
            Parqueadero androidVersion = new Parqueadero();
            androidVersion.setNombre(parqueaderos.get(i).getNombre());
           // androidVersion.setImagen(parqueaderos.get(i).getImagen());
            android_version.add(androidVersion);
        }
        return android_version;
    }

}
