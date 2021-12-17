package com.example.demo.dao

import com.example.demo.model.EstadoFactura
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EstadoFacturaRepository: JpaRepository<EstadoFactura, Int> {
    fun findByNombre(nombreFactura:String): Optional<EstadoFactura>
}