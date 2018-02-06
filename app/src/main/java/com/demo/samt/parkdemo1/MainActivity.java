package com.demo.samt.parkdemo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted{

    ClienteRest clienteRest;
    public String ip="35.229.114.53:80";
    private int WS_CONSULTA = 1;
    private int WS_GUARDAR = 2;
    EditText usuario;
    EditText contrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.btningresar);
        btn1.setOnClickListener(this);

        usuario= (EditText)findViewById(R.id.txtusu);
        contrasena= (EditText)findViewById(R.id.txtpass);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btningresar:
                ingresar();
                break;
            default:
                break;
        }
    }

    @Override
    public void onTaskCompleted(int idSolicitud) {
        if(idSolicitud == WS_CONSULTA){
                if(!clienteRest.isCancelled()){
                    //LISTA DE USUARIOS
                    //A LA MISMA VAMOS A USAR LOS DATOS DEL USUARIO QUE GUARDAMOS EN UNA LISTA
                    //PARA A ESTAS USARLAS EN OTRA CONSULTA UPDATE Y ACTUALIZAR LA CANTIDAD DE PARQUEADEROS
                        Cliente2 usuarios=clienteRest.getResult(Cliente2.class);

                        if(!usuarios.equals(null)){
                            Intent intent = new Intent(this,parqueos.class);
                            //String listSerializedToJson = new Gson().toJson(usuarios);
                            //intent.putExtra("LIST_OF_OBJECTS", listSerializedToJson);
                            intent.putExtra("usuario",usuarios.getCi());
                            //showMensaje("esteeeeee"+usuarios.getCi());
                            startActivity(intent);
                        }else{
                            showMensaje("Usuario o contraseña incorrecto");
                        }
                }
        }

    }

    private void ingresar(){
        //CLIENTE REST PARA CONSULTAR DE QUE SI ME DEVUELVE UN OK EL USUARIO(DUEÑO PARQUEADERO F) ES VALIDO
        clienteRest = new ClienteRest(this);
        try {
            //CONSULTA AL WS
            String url="http://"+ip+"/24/srv/24find/login?usuarioCliente="+usuario.getText()+"&passCliente="+contrasena.getText();
            //GUARDAMOS EL RESULTADO DE LA CONSULTA
            clienteRest.doGet(url,null,WS_CONSULTA,true);
           // showMensaje(url);
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
