package com.example.demo.business

import com.example.demo.dao.ClientesRepository
import com.example.demo.dao.LocalidadRepository
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Clientes
import com.example.demo.model.Localidad
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class LocalidadBusiness: ILocalidadBusiness {

    @Autowired
    val localidadRepository: LocalidadRepository? = null

    @Throws(BusinessException::class)
    override fun getLocalidad(): List<Localidad> {
        try {
            return localidadRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    override fun getLocalidadById(idLocalidad: Int): Localidad {
        val opt: Optional<Localidad>
        try {
            opt=localidadRepository!!.findById(idLocalidad)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se Encontro la Localidad $idLocalidad")
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveLocalidad(localidad: Localidad): Localidad {
        try {
            if (localidad.nombre.isEmpty()) {
                throw BusinessException("El Nombre de la Localidad no puede estar Vacio")
            }
            return localidadRepository!!.save(localidad)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveLocalidades(localidades: List<Localidad>): List<Localidad> {
        try {
            for (l in localidades) {
                if (l.nombre.isEmpty()) {
                    throw BusinessException("El Nombre de la Localidad no puede estar Vacio")
                }
            }
            return localidadRepository!!.saveAll(localidades)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeLocalidad(idLocalidad: Int) {
        val opt:Optional<Localidad>
        try {
            opt = localidadRepository!!.findById(idLocalidad)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la Localidad con el id $idLocalidad")
        }
        else{
            try {
                localidadRepository!!.deleteById(idLocalidad)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateLocalidad(localidad: Localidad): Localidad {
        val opt:Optional<Localidad>
        try {
            opt = localidadRepository!!.findById(localidad.idLocalidad)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona con el id ${localidad.idLocalidad}")
        }
        else{
            try {
                //reglas del negocio
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                var localidadExiste = Localidad(localidad.nombre, localidad.codigoPostal)
                return localidadRepository!!.save(localidad)

            }catch (e1:Exception){
                throw BusinessException(e1.message)
            }
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getLocalidadByNombre(nombreLocalidad: String): Localidad {
        val opt: Optional<Localidad>
        try {
            opt=localidadRepository!!.findByNombre(nombreLocalidad)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro la Localidad $nombreLocalidad")
        return opt.get()
    }
}