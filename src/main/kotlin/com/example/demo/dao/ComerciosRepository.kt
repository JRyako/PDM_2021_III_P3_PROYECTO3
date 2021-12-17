package com.example.demo.dao

import com.example.demo.model.Comercios
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ComerciosRepository: JpaRepository<Comercios, Int> {
    fun findByNombre(nombreComercio:String): Optional<Comercios>
}