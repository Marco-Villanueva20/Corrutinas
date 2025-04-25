package com.example.amphibians.network

import com.example.amphibians.model.Amphibian
import retrofit2.http.GET

interface AmphibianApiService {
    @GET
    suspend fun getAmphibians(): List<Amphibian>
}