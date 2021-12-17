package com.example.demo.business

import com.example.demo.model.Localidad

interface ILocalidadBusiness {
    fun getLocalidad():List<Localidad>
    fun getLocalidadById(idLocalidad: Int): Localidad
    fun saveLocalidad(localidad: Localidad): Localidad
    fun saveLocalidades(localidad:List<Localidad>): List<Localidad>
    fun removeLocalidad(idLocalidad: Int)
    fun updateLocalidad(localidad: Localidad): Localidad
    fun getLocalidadByNombre(nombreLocalidad: String): Localidad
}