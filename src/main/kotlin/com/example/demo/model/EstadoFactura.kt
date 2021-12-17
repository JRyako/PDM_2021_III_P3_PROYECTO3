package com.example.demo.model

import javax.persistence.*

@Entity
@Table(name = "EstadoFactura")//, catalog = "dbo"
data class EstadoFactura(val nombre:String = ""){

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idEstadoFactura: Int = 0
}
