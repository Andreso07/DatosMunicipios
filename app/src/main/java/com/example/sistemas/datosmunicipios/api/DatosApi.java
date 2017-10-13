package com.example.sistemas.datosmunicipios.api;


import com.example.sistemas.datosmunicipios.modelos.Municipio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sistemas on 9/10/17.
 */

public interface DatosApi {
    //xmcd-xh4d.json
    @GET("pfet-63uw.json")
    Call<List<Municipio>> obtenerListaMunicipios();
}
