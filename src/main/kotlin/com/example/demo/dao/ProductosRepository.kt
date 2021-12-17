package com.example.demo.dao

import com.example.demo.model.Productos
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductosRepository: JpaRepository<Productos, Int> {
    fun findByNombre(nombreProductos:String): Optional<Productos>
}