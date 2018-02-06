package com.demo.samt.parkdemo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.samt.parkdemo1.Modelo.Parqueadero;

import java.util.List;

public class parqueoAdmin extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted {

    private Button adicionar,restar;
    private TextView contadorText;
    private int contador=20;
    private int parqueaderoid;
    private int pd;
    private int pt;
    private List imagenes;
    private Parqueadero parqueadero;
    TextView txt;
    /*private int idpark;
    private int ptpark;
    private int pdpark;
    private String nombrepark;*/
    private int WS_CONSULTA = 1;
    private int WS_GUARDAR = 2;
    public String ip = "35.229.114.53:80";

    ClienteRest clienteRest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parqueo_admin);

        adicionar = (Button) findViewById(R.id.btnAdd);
        adicionar.setOnClickListener(this);

        restar = (Button) findViewById(R.id.btnMenos);
        restar.setOnClickListener(this);

        parqueaderoid = getIntent().getExtras().getInt("parqueo");
        pd= getIntent().getExtras().getInt("pd");
        //pt= getIntent().getExtras().getInt("pt");

        showMensaje(""+parqueaderoid);
        ingresar();


        txt= (TextView) findViewById(R.id.lblDisp);
        txt.setText(""+pd);

        //showMensaje(""+parqueadero.getCantidadPD());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                aumentar();
                pd = parqueadero.getPuestosDisponibles();
               // pt = parqueadero.getCantidadPT();
                txt.setText(pd+"/"+pt);
                break;
            case R.id.btnMenos:
                disminuir();
                pd = parqueadero.getPuestosDisponibles();
                //pt = parqueadero.getCantidadPT();
                txt.setText(String.valueOf(pd));
                break;
            default:
                txt.setText(""+parqueadero.getPuestosDisponibles());
                break;
        }
    }


    private void disminuir() {
        if(parqueadero.getPuestosDisponibles()>0){
                int cant = (parqueadero.getPuestosDisponibles()-1);
                guardar(cant);
        }else{
            showMensaje("Llego al minimo de parqueaderos");
        }
    }

    private void aumentar() {
        if(parqueadero.getPuestosDisponibles()<50){
            int cant = (parqueadero.getPuestosDisponibles()+1);
            guardar(cant);
        }else{
            showMensaje("Llego al limite de parqueadero");
        }
    }

    @Override
    public void onTaskCompleted(int idSolicitud) {
        if(idSolicitud == WS_CONSULTA){
            if(!clienteRest.isCancelled()){
                    parqueadero=clienteRest.getResult(Parqueadero.class);
            }
        }
        if(idSolicitud==WS_GUARDAR){
            if(!clienteRest.isCancelled()){
                Respuesta respuesta =clienteRest.getResult(Respuesta.class);
                showMensaje("GUARDADO: "+respuesta.getMensaje());
                }
            }
        }

    private void ingresar(){
        //CLIENTE REST PARA CONSULTAR DE QUE SI ME DEVUELVE UN OK EL USUARIO(DUEÑO PARQUEADERO F) ES VALIDO
        clienteRest = new ClienteRest(this);
        try {
            //CONSULTA AL WS

            String url = "http://"+ip+"/24/srv/24find/leerparqueadero?id="+parqueaderoid;
            //GUARDAMOS EL RESULTADO DE LA CONSULTA
            clienteRest.doGet(url,null,WS_CONSULTA,true);
        }catch (Exception e){
            showMensaje("Error al consultar");
            e.printStackTrace();
        }
    }
    //HACEMOS EL UPDATE DE LA CANTIDAD DE PARQUEADEROS
    private void guardar(int n){
        clienteRest = new ClienteRest(this);
        try {
            String url="http://"+ip+"/24/srv/24find/actualizarp";
            imagenes=parqueadero.getImangenesListP();
            //Parqueadero u = new Parqueadero();
            parqueadero.setPuestosDisponibles(n);
            parqueadero.setImangenesListP(imagenes);
            parqueadero.setTelefonoListP(parqueadero.getTelefonoListP());

            clienteRest.doPost(url,parqueadero,WS_GUARDAR,true);
        }catch (Exception e){
            showMensaje("Error al consultar");
            e.printStackTrace();
        }
    }

    private void showMensaje(String mensaje){
        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        toast.show();
    }
}
