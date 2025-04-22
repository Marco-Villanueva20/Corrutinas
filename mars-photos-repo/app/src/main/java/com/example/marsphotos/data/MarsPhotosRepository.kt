package com.example.marsphotos.data

import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.network.MarsApiService


/**
 * Segundo: La interfaz que representa un repositorio de datos
 */
interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

/**
 * Tercero: Implementación del repositorio de datos que obtiene fotos de Marte del servicio web
 */
class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}