package com.demo.samt.parkdemo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.samt.parkdemo1.Modelo.Local;


public class InformacionLocales extends AppCompatActivity implements OnTaskCompleted,View.OnClickListener{

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private Button guardar;
    public String ip = "35.229.114.53:80";
    ClienteRest clienteRest;
    int local_id;
    Switch estado;
    int posicion;
    private int WS_CONSULTA = 1;
    private int WS_GUARDAR = 2 ;
    private Local local;
    public EditText tvtnombre;
    public EditText tvtdescripcion;
    public EditText tvthorarios;
    public EditText tvtdireccion;
    public Button btnmapa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locales);
        guardar = (Button) findViewById(R.id.btnmapa);
        guardar.setOnClickListener(this);



        local_id = getIntent().getExtras().getInt("uno");
        posicion=getIntent().getExtras().getInt("posicion");

        ingresar();




    }
    private void ingresar(){
        //CLIENTE REST PARA CONSULTAR los Locales que existen
        clienteRest = new ClienteRest(this);
        try {
            //CONSULTA AL WS
            //se aplica un filtro para que muestre  todos los locales segun los subcategorias
            String url="http://"+ip+"/24/srv/24find/leerlocal?id="+local_id;

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
                local=clienteRest.getResult(Local.class);
                //showMensaje(""+locales);
                initViews();
                //showMensaje("TOTAL: "+local.size());
            }
        }
        if(idSolicitud==WS_GUARDAR){
            if(!clienteRest.isCancelled()){
                Respuesta respuesta =clienteRest.getResult(Respuesta.class);

                showMensaje("GUARDADO: "+respuesta.getMensaje());
            }
        }
    }

    private void showMensaje(String mensaje){
        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        toast.show();
    }
    private void initViews(){
        //agregamos la informacion que nos devolvio el ws y lo ingresamos en  sus cuadros de texto
        EditText tvtnombre = (EditText) findViewById(R.id.tvtNombreL);
        EditText tvtdescripcion = (EditText) findViewById(R.id.tvtDescripcionL);
        //TextView tvthorarios = (TextView) findViewById(R.id.tvtHorariosA);
        EditText tvtdireccion = (EditText) findViewById(R.id.tvtDireccionA);
        Switch estado = (Switch) findViewById(R.id.estado);
        EditText tvtlatitud = (EditText) findViewById(R.id.tvtLatitudI);
        EditText tvtlongitud = (EditText) findViewById(R.id.tvtLongitudI);
        TextView tvtcomentarios = (TextView) findViewById(R.id.tvtComentariosA);


//        estado.setChecked(local.getEstado());
        tvtnombre.setText(local.getNombre());
        tvtdescripcion.setText(local.getDescripcion());
        tvtlatitud.setText(local.getLat());
        tvtlongitud.setText(local.getLng());
       // tvthorarios.setText("9:00am/17:30pm");
    //    tvtcomentarios.setText(local.getPromociones());
        tvtdireccion.setText(local.getDireccion());



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnmapa:


                guardar();

                break;


        }
    }

    private void guardar(){
        clienteRest = new ClienteRest(this);
        try {
            String url="http://"+ip+"/24/srv/24find/actualizarl";
            //local u = new local();
            EditText tvtnombre = (EditText) findViewById(R.id.tvtNombreL);
            EditText tvtdescripcion = (EditText) findViewById(R.id.tvtDescripcionL);
           // TextView tvthorarios = (TextView) findViewById(R.id.tvtHorariosA);
            EditText tvtdireccion = (EditText) findViewById(R.id.tvtDireccionA);
            Switch estado = (Switch) findViewById(R.id.estado);

            Boolean estadoL=estado.isChecked();
            String nombre=(tvtnombre.getText().toString());
            String descripcion=(tvtdescripcion.getText().toString());;
            String direccion=(tvtdireccion.getText().toString());

            local.setNombre(nombre);
            local.setDescripcion(descripcion);
            local.setDireccion(direccion);
            local.setLat(local.getLat());
            local.setLng(local.getLng());

            //local.setEstado(estadoL);

            //local.setNombre("juanisto");
            //local.setDescripcion("Venta de video juegs");
            //local.setDireccion("los andres");


            clienteRest.doPost(url,local,WS_GUARDAR,true);
        }catch (Exception e){
            showMensaje("Error al consultar");
            e.printStackTrace();
        }
    }
}
