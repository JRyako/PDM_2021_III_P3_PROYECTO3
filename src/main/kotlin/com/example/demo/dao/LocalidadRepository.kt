package com.example.demo.dao

import com.example.demo.model.Localidad
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LocalidadRepository: JpaRepository<Localidad, Int> {
    fun findByNombre(nombreLocalidad:String): Optional<Localidad>
}