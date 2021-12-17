package com.example.demo.business

import com.example.demo.dao.ClientesRepository
import com.example.demo.dao.ComerciosRepository
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Clientes
import com.example.demo.model.Comercios
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class ComerciosBusiness: IComerciosBusiness {

    @Autowired
    val comerciosRepository: ComerciosRepository? = null

    @Throws(BusinessException::class)
    override fun getComercios(): List<Comercios> {
        try {
            return comerciosRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getComerciosById(idComercio: Int): Comercios {
        val opt: Optional<Comercios>
        try {
            opt=comerciosRepository!!.findById(idComercio)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se Encontro el Restaurante con el Id: $idComercio")
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveComercio(comercios: Comercios): Comercios {
        try {
            if (comercios.nombreRestaurantes.isEmpty()){
                throw BusinessException("El Nombre del Restaurante no puede estar Vacio")
            }
            if (comercios.nombreRestaurantes.length <= 2){
                throw BusinessException("Ingrese mas de 2 Characters")
            }
            return comerciosRepository!!.save(comercios)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveComercios(comercios: List<Comercios>): List<Comercios> {
        try {
            for (c in comercios) {
                if (c.nombreRestaurantes.isEmpty()) {
                    throw BusinessException("El Nombre del Restaurante no puede estar Vacio")
                }
                if (c.nombreRestaurantes.length <= 2) {
                    throw BusinessException("Ingrese mas de 2 Characters")
                }
            }
            return comerciosRepository!!.saveAll(comercios)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun removeComercios(idComercio: Int) {
        val opt:Optional<Comercios>
        try {
            opt = comerciosRepository!!.findById(idComercio)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el Restaurante con el id $idComercio")
        }
        else{
            try {
                comerciosRepository!!.deleteById(idComercio)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateComercios(comercios: Comercios): Comercios {
        val opt:Optional<Comercios>
        try {
            opt = comerciosRepository!!.findById(comercios.idComercio)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el Restaurante con el id ${comercios.idComercio}")
        }
        else{
            try {
                //reglas del negocio
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                var comerciosExiste = Comercios(comercios.nombreRestaurantes)
                return comerciosRepository!!.save(comercios)

            }catch (e1:Exception){
                throw BusinessException(e1.message)
            }
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getComerciosByNombre(nombreComercios: String): Comercios {
        val opt: Optional<Comercios>
        try {
            opt=comerciosRepository!!.findByNombre(nombreComercios)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el Restaurante $nombreComercios")
        return opt.get()
    }
}
