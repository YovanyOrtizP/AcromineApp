package com.example.acromineapp.data.repository

import com.example.acromineapp.data.model.AcromineResponse
import com.example.acromineapp.util.UIState
import kotlinx.coroutines.flow.Flow

interface AcromineRepository {

    /**
     * We create the interface to get the information from the repository implementation
     * With the interface we avoid that someone could modify the implementation
     */
    fun getAcromineFlow(sf: String): Flow<UIState<List<AcromineResponse>>>
}