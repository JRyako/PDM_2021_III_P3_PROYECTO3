package com.example.demo.business

import com.example.demo.model.EstadoFactura

interface IEstadoFacturaBusiness {
    fun getEstadoFactura():List<EstadoFactura>
    fun getEstadoFacturaById(idEstadoFactura: Int): EstadoFactura
    fun saveEstadoFactura(estadofactura: EstadoFactura): EstadoFactura
    fun saveEstadoFacturas(estadofacturas:List<EstadoFactura>): List<EstadoFactura>
    fun removeEstadoFactura(idEstadoFactura: Int)
    fun updateEstadoFactura(estadofactura: EstadoFactura): EstadoFactura
    fun getEstadoFacturaByNombre(nombreEstadoFactura: String): EstadoFactura
}