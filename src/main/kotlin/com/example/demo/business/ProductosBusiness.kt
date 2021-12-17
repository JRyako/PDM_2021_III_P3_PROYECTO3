package com.example.demo.business

import com.example.demo.dao.ClientesRepository
import com.example.demo.dao.ProductosRepository
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Clientes
import com.example.demo.model.Productos
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class ProductosBusiness: IProductosBusiness {

    @Autowired
    val productosRepository: ProductosRepository? = null

    @Throws(BusinessException::class)
    override fun getProductos(): List<Productos> {
        try {
            return productosRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getProductosById(idProducto: Int): Productos {
        val opt: Optional<Productos>
        try {
            opt=productosRepository!!.findById(idProducto)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se Encontro el Producto $idProducto")
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveProducto(productos: Productos): Productos {
        try {
            if (productos.nombreProducto.isEmpty()) {
                throw BusinessException("El Nombre del Producto no puede estar Vacio")
            }
            if (productos.nombreProducto.length <= 1) {
                throw BusinessException("El Nombre del Producto no puede contener solo 1 caracter.")
            }
            if (productos.precioU.isEmpty()) {
                throw BusinessException("El Precio no puede estar Vacio")
            }
            if (productos.descripcion.isEmpty()) {
                throw BusinessException("la Descripcion no puede estar vacia")
            }
            if (productos.categoria.isEmpty()) {
                throw BusinessException("La Categoria no puede esta vacia")
            }
            if (productos.cantidad.toString().isEmpty()) {
                throw BusinessException("La catidad no puede estar vacia")
            }
            if (productos.cantidad.toString().length < 0) {
                throw BusinessException("la cantidad no puede ser menor a 0")
            }
            return productosRepository!!.save(productos)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveProductos(productos: List<Productos>): List<Productos> {
        try {
            for (p in productos){
            if (p.nombreProducto.isEmpty()) {
                throw BusinessException("El Nombre del Producto no puede estar Vacio")
            }
            if (p.nombreProducto.length <= 1) {
                throw BusinessException("El Nombre del Producto no puede contener solo 1 caracter.")
            }
            if (p.precioU.isEmpty()) {
                throw BusinessException("El Precio no puede estar Vacio")
            }
            if (p.descripcion.isEmpty()) {
                throw BusinessException("la Descripcion no puede estar vacia")
            }
            if (p.categoria.isEmpty()) {
                throw BusinessException("La Categoria no puede esta vacia")
            }
            if (p.cantidad.toString().isEmpty()) {
                throw BusinessException("La catidad no puede estar vacia")
            }
            if (p.cantidad.toString().length < 0) {
                throw BusinessException("la cantidad no puede ser menor a 0")
            }
            }
            return productosRepository!!.saveAll(productos)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun removeProductos(idProducto: Int) {
        val opt:Optional<Productos>
        try {
            opt = productosRepository!!.findById(idProducto)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el Producto con el id $idProducto")
        }
        else{
            try {
                productosRepository!!.deleteById(idProducto)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateProductos(productos: Productos): Productos {
        val opt:Optional<Productos>
        try {
            opt = productosRepository!!.findById(productos.idProducto)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona con el id ${productos.idProducto}")
        }
        else{
            try {
                //reglas del negocio
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                var productoExiste = Productos(productos.nombreProducto, productos.precioU, productos.descripcion, productos.categoria,
                                    productos.cantidad)
                return productosRepository!!.save(productos)

            }catch (e1:Exception){
                throw BusinessException(e1.message)
            }
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getProductosyNombre(nombreProductos: String): Productos {
        val opt: Optional<Productos>
        try {
            opt=productosRepository!!.findByNombre(nombreProductos)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el Producto $nombreProductos")
        return opt.get()
    }
}