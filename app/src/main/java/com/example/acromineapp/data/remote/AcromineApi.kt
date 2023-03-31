package com.example.acromineapp.data.remote

import com.example.acromineapp.data.model.AcromineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcromineApi {

    /**
     * http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=asap
     * Add 2 parameters because we can use sf or lf to search the information
     */
    @GET(ENDPOINT)
    suspend fun getAcromine(
        @Query(PARAM_SF) sf: String
    ): Response<List<AcromineResponse>>

    companion object {
        const val BASE_URL = "http://www.nactem.ac.uk"
        const val ENDPOINT = "/software/acromine/dictionary.py"
        const val PARAM_SF = "sf"
    }
}