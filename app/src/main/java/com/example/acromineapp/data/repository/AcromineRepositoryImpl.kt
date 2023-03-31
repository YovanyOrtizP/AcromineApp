package com.example.acromineapp.data.repository

import android.widget.Toast
import com.example.acromineapp.data.model.AcromineResponse
import com.example.acromineapp.data.remote.AcromineApi
import com.example.acromineapp.util.InformationException
import com.example.acromineapp.util.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class AcromineRepositoryImpl @Inject constructor(
    private val acromineApi: AcromineApi
) : AcromineRepository {

    override fun getAcromineFlow(sf: String): Flow<UIState<List<AcromineResponse>>> = flow {
        emit(UIState.LOADING)

        try {
            val response = acromineApi.getAcromine(sf)
            if (response.isSuccessful) {
                response.body()?.let { info ->
                    if (info.isNullOrEmpty()) {
                        throw Exception("NO FOUND")
                    } else {
                        emit(UIState.SUCCESS(info))
                    }
                } ?: throw InformationException()
            } else {
                throw Exception(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }
}