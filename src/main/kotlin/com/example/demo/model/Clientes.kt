package com.example.demo.model

import javax.persistence.*

@Entity
@Table(name = "cliente")//, catalog = "dbo"
data class Clientes(val numeroTel:Int = 0,
                    val nombreCompleto:String = "",
                    val direccion:String = "",
                    val fechaPedido:String = "",
                    val email:String = "",
                    var idProducto:Int = 0){
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}