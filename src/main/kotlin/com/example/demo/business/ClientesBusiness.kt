package com.example.demo.business

import com.example.demo.dao.ClientesRepository
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Clientes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class ClientesBusiness: IClientesBusiness {

    @Autowired
    val clientesRepository: ClientesRepository? = null

    @Throws(BusinessException::class)
    override fun getClientes(): List<Clientes> {
        try {
            return clientesRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getClientesById(id: Long): Clientes {
        val opt: Optional<Clientes>
        try {
            opt=clientesRepository!!.findById(id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se Encontro el Cliente con Id: $id")
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveCliente(cliente: Clientes): Clientes {

        try {
            if (cliente.numeroTel.toString().length <= 1){
                throw BusinessException("El Numero de Telefono no puede estar Vacio")
            }
            if (cliente.numeroTel.toString().length > 8){
                throw BusinessException("El Numero de Telefono no puede ser mayor a 8 Digitos")
            }
            if (cliente.nombreCompleto.isEmpty()){
                throw BusinessException("El Nombre del Cliente no puede estar Vacio")
            }
            if (cliente.nombreCompleto.length < 5){
                throw BusinessException("Ingrese mas de 5 Characters")
            }
            if (cliente.direccion.isEmpty()){
                throw BusinessException("La Direccion no puede estar Vacia")
            }
            if (cliente.direccion.length <= 5){
                throw BusinessException("Favor Agregue mas Informacion a su Direccion")
            }
            if (cliente.email.isEmpty()){
                throw BusinessException("El Correo no puede estar Vacio")
            }
            return clientesRepository!!.save(cliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveClientes(clientes: List<Clientes>): List<Clientes> {
        try {
            for (c in clientes){
                if (c.numeroTel.toString().length <= 1){
                    throw BusinessException("El Numero de Telefono no puede estar Vacio")
                }
                if (c.numeroTel.toString().length > 8){
                    throw BusinessException("El Numero de Telefono no puede ser mayor a 8 Digitos")
                }
                if (c.nombreCompleto.isEmpty()){
                    throw BusinessException("El Nombre del Cliente no puede estar Vacio")
                }
                if (c.nombreCompleto.length < 5){
                    throw BusinessException("Ingrese mas de 5 Characters")
                }
                if (c.direccion.isEmpty()){
                    throw BusinessException("La Direccion no puede estar Vacia")
                }
                if (c.direccion.length < 10){
                    throw BusinessException("Favor Agregue mas Informacion a su Direccion")
                }
                if (c.email.isEmpty()){
                    throw BusinessException("El Correo no puede estar Vacio")
                }
            }
            return clientesRepository!!.saveAll(clientes)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun removeClientes(id: Long) {
        val opt:Optional<Clientes>
        try {
            opt = clientesRepository!!.findById(id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el Cliente con el id $id")
        }
        else{
            try {
                clientesRepository!!.deleteById(id)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateClientes(clientes: Clientes): Clientes {
        val opt:Optional<Clientes>
        try {
            opt = clientesRepository!!.findById(clientes.id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona con el id ${clientes.id}")
        }
        else{
            try {
                //reglas del negocio
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                var clienteExiste = Clientes(clientes.numeroTel, clientes.nombreCompleto, clientes.direccion, clientes.fechaPedido, clientes.email)
                return clientesRepository!!.save(clientes)

            }catch (e1:Exception){
                throw BusinessException(e1.message)
            }
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getClientesByNombre(nombreClientes: String): Clientes {
        val opt: Optional<Clientes>
        try {
            opt=clientesRepository!!.findByNombreCompleto(nombreClientes)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el Cliente $nombreClientes")
        return opt.get()
    }

}