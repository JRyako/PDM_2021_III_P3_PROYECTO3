package com.example.demo.web

import com.example.demo.business.IComerciosBusiness
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Comercios
import com.example.demo.utils.Constants
import com.example.demo.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_COMERCIOS)
class ComerciosRestController {
   /* @Autowired
    val comerciosBusiness: IComerciosBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Comercios>> {
        return try {
            ResponseEntity(comerciosBusiness!!.getComercios(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idComercios: Int): ResponseEntity<Comercios> {
        return try {
            ResponseEntity(comerciosBusiness!!.getComerciosById(idComercios), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreRestaurantes: String): ResponseEntity<Comercios> {
        return try {
            ResponseEntity(comerciosBusiness!!.getComerciosByNombre(nombreRestaurantes), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addComercio")
    fun insert(@RequestBody persona: Comercios): ResponseEntity<Any> {
        return try {
            comerciosBusiness!!.saveComercios(persona)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_COMERCIOS + "/" + persona.id)
            ResponseEntity(persona,responseHeader, HttpStatus.CREATED)
        }catch (e: BusinessException){
            val apiError = RestApiError(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",
                    e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addPersonas")
    fun insert(@RequestBody personas: List<Comercios>): ResponseEntity<Any> {
        return try {
            ResponseEntity(comerciosBusiness!!.savePersonas(personas), HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody persona: Comercios): ResponseEntity<Any> {
        return try {
            comerciosBusiness!!.updatePersona(persona)
            ResponseEntity(persona, HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idPersona:Long): ResponseEntity<Any> {
        return try {
            personaBusiness!!.removePersona(idPersona)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }*/
}