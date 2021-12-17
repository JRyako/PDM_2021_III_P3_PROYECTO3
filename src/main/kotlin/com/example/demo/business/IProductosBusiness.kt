package com.example.demo.business

import com.example.demo.model.Productos

interface IProductosBusiness {
    fun getProductos():List<Productos>
    fun getProductosById(idProducto: Int): Productos
    fun saveProducto(productos: Productos): Productos
    fun saveProductos(productos:List<Productos>): List<Productos>
    fun removeProductos(idProducto: Int)
    fun updateProductos(productos: Productos): Productos
    fun getProductosyNombre(nombreProductos: String): Productos
}