package com.example.demo.business

import com.example.demo.model.Comercios

interface IComerciosBusiness {
    fun getComercios():List<Comercios>
    fun getComerciosById(idComercio: Int): Comercios
    fun saveComercio(comercios: Comercios): Comercios
    fun saveComercios(comercios:List<Comercios>): List<Comercios>
    fun removeComercios(idComercio: Int)
    fun updateComercios(comercios: Comercios): Comercios
    fun getComerciosByNombre(nombreComercios: String): Comercios
}