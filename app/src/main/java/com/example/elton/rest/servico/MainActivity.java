package com.example.elton.rest.servico;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.elton.rest.dominio.Daruinda;
import com.example.elton.rest.R;
import com.example.elton.rest.endpoint.AppEndpoint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void criarJson() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        AppEndpoint appService = retrofit.create(AppEndpoint.class);
        EditText entrada = findViewById(R.id.edtxtId);
        Call<Daruinda> call = appService.obterpost(Integer.parseInt(entrada.getText().toString()));


        call.enqueue(new Callback<Daruinda>() {
            @Override
            public void onResponse(Call<Daruinda> call, Response<Daruinda> response) {
                //Chamada assíncrona
                int statusCode = response.code();
                Daruinda daruinda = response.body();
                TextView titulo = findViewById(R.id.txtView1);
                titulo.setText(daruinda.getTitle());
                //Log.i("teste", "mensagem: " + daruinda.getTitle().toString());
            }


            @Override
            public void onFailure(Call<Daruinda> call, Throwable t) {
                //Log error heresice request failed
                Log.i("teste", t.toString());
            }
        });
    }

    public void acaoBotao(View v) {
        criarJson();
    }
}


