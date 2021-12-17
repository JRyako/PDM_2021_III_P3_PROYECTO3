package com.example.demo.business

import com.example.demo.model.Clientes

interface IClientesBusiness {
    fun getClientes():List<Clientes>
    fun getClientesById(id: Long): Clientes
    fun saveCliente(cliente: Clientes): Clientes
    fun saveClientes(clientes:List<Clientes>): List<Clientes>
    fun removeClientes(id: Long)
    fun updateClientes(clientes: Clientes): Clientes
    fun getClientesByNombre(nombreClientes: String):Clientes
}