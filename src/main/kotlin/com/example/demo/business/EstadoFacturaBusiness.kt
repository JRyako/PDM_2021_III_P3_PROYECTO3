package com.example.demo.business

import com.example.demo.dao.ClientesRepository
import com.example.demo.dao.EstadoFacturaRepository
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Clientes
import com.example.demo.model.EstadoFactura
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class EstadoFacturaBusiness: IEstadoFacturaBusiness {

    @Autowired
    val estadoFacturaRepository: EstadoFacturaRepository? = null

    @Throws(BusinessException::class)
    override fun getEstadoFactura(): List<EstadoFactura> {
        try {
            return estadoFacturaRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getEstadoFacturaById(idEstadoFactura: Int): EstadoFactura {
        val opt: Optional<EstadoFactura>
        try {
            opt=estadoFacturaRepository!!.findById(idEstadoFactura)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se Encontro el Estado de Factura numero: $idEstadoFactura")
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveEstadoFactura(estadofactura: EstadoFactura): EstadoFactura {
        try {
            if (estadofactura.nombre.isEmpty()) {
                throw BusinessException("El Nombre de la Factura no puede estar Vacio")
            }
                return estadoFacturaRepository!!.save(estadofactura)
        }catch (e:Exception){
                throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveEstadoFacturas(estadofacturas: List<EstadoFactura>): List<EstadoFactura> {
        try {
            for (e in estadofacturas) {
                if (e.nombre.isEmpty()) {
                    throw BusinessException("El Nombre de la Factura no puede estar Vacio")
                }
            }
            return estadoFacturaRepository!!.saveAll(estadofacturas)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun removeEstadoFactura(idEstadoFactura: Int) {
        val opt:Optional<EstadoFactura>
        try {
            opt = estadoFacturaRepository!!.findById(idEstadoFactura)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el estado de la Factura con el id $idEstadoFactura")
        }
        else{
            try {
                estadoFacturaRepository!!.deleteById(idEstadoFactura)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateEstadoFactura(estadofactura: EstadoFactura): EstadoFactura {
        val opt:Optional<EstadoFactura>
        try {
            opt = estadoFacturaRepository!!.findById(estadofactura.idEstadoFactura)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la la Factura con el id ${estadofactura.idEstadoFactura}")
        }
        else{
            try {
                //reglas del negocio
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                var estadoFacturaExiste = EstadoFactura(estadofactura.nombre)
                return estadoFacturaRepository!!.save(estadofactura)

            }catch (e1:Exception){
                throw BusinessException(e1.message)
            }
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getEstadoFacturaByNombre(nombreEstadoFactura: String): EstadoFactura {
        val opt: Optional<EstadoFactura>
        try {
            opt=estadoFacturaRepository!!.findByNombre(nombreEstadoFactura)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro la Factura $nombreEstadoFactura")
        return opt.get()
    }
}