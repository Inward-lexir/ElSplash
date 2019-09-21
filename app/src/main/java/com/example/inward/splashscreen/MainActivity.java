package com.example.inward.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {


    EditText txtUser,txtPass;
    String user, password;
    Button btnRegistrar, btnIniciarSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = findViewById(R.id.txtPass);
        txtPass = findViewById(R.id.txtPass);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });

    }



    public void login(View view) {

        user = txtUser.getText().toString().trim();
        password = txtPass.getText().toString();

        //Validar datos de la interfaz (usuario)
        if (TextUtils.isEmpty(user)) {

            txtUser.setError("Usuario vacio");
            txtUser.setFocusable(true);
            return;

        }
        //Validar datos de la interfaz (password)
        if (TextUtils.isEmpty(password)) {

            txtPass.setError("Contraseña vacia");
            txtPass.setFocusable(true);
            return;
        }

        //Nueva instancia de LoginRest
        new LoginRest().execute(user,password);

    }

    //Hacer una clase asincrona (AsyncTask)
    class LoginRest extends AsyncTask <String, Integer, String>{

        //variable de peticion de conexion
        URLConnection connection = null;

        //variable para el resultado de la peticion
        String result = "0";

        @Override

        //Cuando hay ..., indica que la variabe de enfrente es un arreglo
        protected String doInBackground(String... strings) {

            try {
                connection = new URL("http://172.18.26.67/"
                        + "cursoAndroid/vista/Usuario/"
                        + "crearUsuario.php"
                        + "?usuario= "+strings[0]+"&password="+strings[1]).openConnection();

                InputStream inputStream = (InputStream) connection.getContent();
                byte[] buffer = new byte[10000];
                //regresa el tamaño completo del set de datos
                //el int size indica el tamaño de la informacion de la cadena de respuesta
                int size = inputStream.read(buffer);

                result = new String(buffer, 0, size);
                //Para que imprima el resultado en la consola
                Log.i("result",result);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s.equals("1")){

                Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                startActivity(intent);

            }else {

                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();

            }

        }
    }

}