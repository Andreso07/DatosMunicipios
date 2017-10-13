package com.example.sistemas.datosmunicipios;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sistemas.datosmunicipios.api.DatosApi;
import com.example.sistemas.datosmunicipios.modelos.Municipio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    public final static String TAG="DATOS COLOMBIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit=new Retrofit.Builder().baseUrl("https://www.datos.gov.co/resource/").addConverterFactory(GsonConverterFactory.create()).build();

        obtenerDatos();
    }
     public void obtenerDatos(){

         DatosApi service=retrofit.create(DatosApi.class);
         final Call<List<Municipio>> municipioCall=service.obtenerListaMunicipios();

         municipioCall.enqueue(new Callback<List<Municipio>>() {
             @Override
             public void onResponse(Call<List<Municipio>> call, Response<List<Municipio>> response) {

                 if (response.isSuccessful()){
                     List municipios=response.body();
                     for (int i=0; i<municipios.size(); i++){
                         Municipio m=(Municipio) municipios.get(i);
                         Log.i(TAG,"Nombre: "+m.getNombreMunicipio()+"     Alcalde: "+m.getNombreAlcalde());
                     }
                 }
                 else {
                     Log.e(TAG,"OnResponse "+response.errorBody());
                 }
             }

             @Override
             public void onFailure(Call<List<Municipio>> call, Throwable t) {

                 Log.e(TAG,"OnFailure "+t.getMessage());
             }
         });
     }
}
