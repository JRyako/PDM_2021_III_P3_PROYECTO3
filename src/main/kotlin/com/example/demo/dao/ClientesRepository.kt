package com.example.demo.dao

import com.example.demo.model.Clientes
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClientesRepository:JpaRepository<Clientes, Long> {
    fun findByNombreCompleto(nombreCliente:String): Optional<Clientes>
}