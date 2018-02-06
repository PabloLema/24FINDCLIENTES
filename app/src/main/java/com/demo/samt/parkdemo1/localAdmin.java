package com.demo.samt.parkdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.samt.parkdemo1.Modelo.Local;

public class localAdmin extends AppCompatActivity  implements View.OnClickListener, OnTaskCompleted {

    private Button guardar;
    private TextView contadorText;
    private int contador=20;
    private int localid;
    private String pd;
    private String pt;
    private Local local;
    EditText editar;
    TextView txtmostrar;
    TextView txtnombre;
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
        setContentView(R.layout.activity_local_admin);

        guardar = (Button) findViewById(R.id.btndetalle);
        guardar.setOnClickListener(this);



        localid = getIntent().getExtras().getInt("parqueo");
        pd= getIntent().getExtras().getString("descripcion");
        pt= getIntent().getExtras().getString("nombre");

        showMensaje(""+localid);
        ingresar();
        editar=(EditText) findViewById(R.id.editText);
        txtnombre= (TextView) findViewById(R.id.txtnombre);
        txtnombre.setText(pt);

        txtmostrar= (TextView) findViewById(R.id.txtmostrar);
        txtmostrar.setText(pd);




    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btndetalle:
                guardarcambios();
                pd = local.getDescripcion();
                txtmostrar.setText(pd);
                break;

            default:
                txtmostrar.setText(local.getDescripcion());
                txtnombre.setText(local.getNombre());
                break;
        }
    }


    /*private void disminuir() {
        if(local.getCantidadPD()>0){
            int cant = (local.getCantidadPD()-1);
            guardar(cant);
        }else{
            showMensaje("Llego al minimo de parqueaderos");
        }
    }

    private void aumentar() {
        if(local.getCantidadPD()<local.getCantidadPT()){
            int cant = (local.getCantidadPD()+1);
            guardar(cant);
        }else{
            showMensaje("Llego al limite de local");
        }
    }*/
    private void guardarcambios() {

        String editar1="hola esto es una prueba";
        guardar(editar1);
    }


    @Override
    public void onTaskCompleted(int idSolicitud) {
        if(idSolicitud == WS_CONSULTA){
            if(!clienteRest.isCancelled()){
                local=clienteRest.getResult(Local.class);
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
        //CLIENTE REST PARA CONSULTAR DE QUE SI ME DEVUELVE UN OK EL USUARIO(DUEÑO local F) ES VALIDO
        clienteRest = new ClienteRest(this);
        try {
            //CONSULTA AL WS

            String url = "http://"+ip+"/24FINDAPP_/srv/localesapp/leer?id="+localid;
            //GUARDAMOS EL RESULTADO DE LA CONSULTA
            clienteRest.doGet(url,null,WS_CONSULTA,true);
        }catch (Exception e){
            showMensaje("Error al consultar");
            e.printStackTrace();
        }
    }
    //HACEMOS EL UPDATE DE LA CANTIDAD DE PARQUEADEROS
    private void guardar(String n){
        clienteRest = new ClienteRest(this);
        try {
            String url="http://"+ ip +"/24FINDAPP_/srv/localesapp/actualizar";
            //local u = new local();
            local.setDescripcion("esto es una prueba");
            clienteRest.doPost(url,local,WS_GUARDAR,true);
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
