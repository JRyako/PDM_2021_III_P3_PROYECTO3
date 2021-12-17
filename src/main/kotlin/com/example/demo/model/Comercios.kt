package com.example.demo.model

import javax.persistence.*

@Entity
@Table(name = "Comercios")//, catalog = "dbo"
data class Comercios(val nombreRestaurantes:String = ""){
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idComercio: Int = 0
}
