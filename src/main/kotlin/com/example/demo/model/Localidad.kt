package com.example.demo.model

import javax.persistence.*

@Entity
@Table(name = "Localidad")//, catalog = "dbo"
data class Localidad(val nombre:String = "", val codigoPostal:String = ""){
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idLocalidad: Int = 0
}
