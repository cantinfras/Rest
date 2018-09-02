package com.example.elton.rest.servico;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


       AppEndpoint appService = retrofit.create(AppEndpoint.class);
        Call<Daruinda> call = appService.obterpost(1);

        call.enqueue(new Callback<Daruinda>() {
            @Override
            public void onResponse(Call<Daruinda> call, Response<Daruinda> response) {
                //Chamada assíncrona
                int statusCode = response.code();
                Daruinda daruinda = response.body();
                Log.i("teste", "title: " + daruinda.getTitle().toString());
            }


            @Override
            public void onFailure(Call<Daruinda> call, Throwable t) {
                //Log error heresice request failed
                Log.i("teste", t.toString());
            }
        });
    }
}
