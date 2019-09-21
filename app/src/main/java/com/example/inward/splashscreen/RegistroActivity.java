package com.example.inward.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import modelos.Usuario;

public class RegistroActivity extends AppCompatActivity {

    //Declaracion de los elementos del activity
    EditText txtNombre, txtUsuario, txtCorreo, txtDireccion, txtPass;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtUsuario = findViewById(R.id.txtUser);
        txtNombre = findViewById(R.id.txtNombre);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtPass = findViewById(R.id.txtPass);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //mandar llamar la validacion de datos
                if (validarDatos(txtUsuario.getText().toString().trim(),
                        txtNombre.getText().toString().trim(),
                        txtCorreo.getText().toString().trim(),
                        txtDireccion.getText().toString().trim(),
                        txtPass.getText().toString())

                ){

                    //Crear objeto de tipo usuario
                    Usuario usuario = new Usuario();
                    usuario.setUsuario(txtUsuario.getText().toString().trim());
                    usuario.setNombre(txtNombre.getText().toString().trim());
                    usuario.setCorreo(txtCorreo.getText().toString().trim());
                    usuario.setDireccion(txtDireccion.getText().toString().trim());
                    usuario.setPass(txtPass.getText().toString());

                    //Hacer la instancia de la subClase asyncTask para realizar la conexion
                    new Registrar().execute(usuario);
                }
            }
        });
    }

    //Definicion del metodo
    public boolean validarDatos(String usuario, String nombre, String correo, String direccion, String pass){

        //Validar que los datos no esten vacios
        if (nombre.isEmpty()){

            txtNombre.setError("Campo vacio");
            txtNombre.setFocusable(true);
            return false;

        }

        if (usuario.isEmpty()){

            txtUsuario.setError("Campo vacio");
            txtUsuario.setFocusable(true);
            return false;

        }

        if (correo.isEmpty()){

            txtCorreo.setError("Campo vacio");
            txtCorreo.setFocusable(true);
            return false;

        }

        if (direccion.isEmpty()){

            txtDireccion.setError("Campo vacio");
            txtDireccion.setFocusable(true);
            return false;

        }

        if (pass.isEmpty()){

            txtPass.setError("Campo vacio");
            txtPass.setFocusable(true);
            return false;

        }

        return true;

    }


    //Crear una subclase
    //Crea un hilo para obtener los datos de manera asincrona
    class Registrar extends AsyncTask<Usuario, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(Usuario... usuarios) {

            //Preparar los datos de insersion
            String params = "";
            params = "Usuario =" + usuarios[0].getUsuario()+"&" +
                        "Nombre =" + usuarios[0].getNombre()+"&" +
                        "Correo =" + usuarios[0].getCorreo()+"&" +
                        "Direccion" + usuarios[0].getDireccion()+"&" +
                        "Contraseña" + usuarios[0].getPass();

            //Preparar la conexion
            try {
                URL url = new URL ("http://172.18.26.67/cursoAndroid/vista/Usuario/crearUsuario.php");

                //Abrir conexion
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                //Cambia la forma de llevar la informacion a traves del post y no un get
                connection.setRequestMethod("POST");

                //Indicar a la url que se van a mandar datos
                connection.setDoInput(true);

                //Regrese un dato de salida
                connection.setDoOutput(true);


                //Variable que lleva el valor que vamos a escribir
                OutputStream outputStream = connection.getOutputStream();

                //Elemento que haga la insersion de datos y que mande la informacion en UTF-8
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));


                writer.write(params);

                //Eliminar los datos que pueden estar anteriores
                writer.flush();

                //Uso inapropiado de la memoria y cierre de la conexion
                writer.close();

                connection.connect();

                int responseCode = connection.getResponseCode();


                if (responseCode == HttpURLConnection.HTTP_OK){

                    //Si todo sale bien, que me mande un mensaje de que el registro fue exitoso
                    //No se puede usar un Toast porque no hay contexto de una tarea en segudo plano
                    Log.i("AddUser", "Uuario agregado con éxito");
                    return true;

                }else {return false;}


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //false: para que en caso de que haga algo muy excepcional, regrese un falso
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean){

                //Mensaje de usuario agregado
                Toast.makeText(RegistroActivity.this, "Usuario agregado con éxito", Toast.LENGTH_SHORT).show();

                //Cambio de pantalla
                finish();

            }else{

                //Mensaje de error
                Toast.makeText(RegistroActivity.this, "Usuario no agregado, intente nuevamente", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
