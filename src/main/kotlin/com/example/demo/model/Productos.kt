package com.example.demo.model

import javax.persistence.*

@Entity
@Table(name = "Productos")//, catalog = "dbo"
data class Productos(val nombreProducto: String = "", val precioU: String = "", val descripcion: String = "", val categoria: String = "",
                     val cantidad: Int = 0, var idComercio: Int = 0){
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idProducto: Int = 0
}

