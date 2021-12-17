package com.example.demo.web

import com.example.demo.business.IClientesBusiness
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Clientes
import com.example.demo.utils.Constants
import com.example.demo.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_CLIENTES)
class ClientesRestController {
    @Autowired
    val clientesBusiness: IClientesBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Clientes>> {
        return try {
            ResponseEntity(clientesBusiness!!.getClientes(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") id: Long): ResponseEntity<Clientes> {
        return try {
            ResponseEntity(clientesBusiness!!.getClientesById(id), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreClientes:String): ResponseEntity<Clientes> {
        return try {
            ResponseEntity(clientesBusiness!!.getClientesByNombre(nombreClientes), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addCliente")
    fun insert(@RequestBody clientes: Clientes): ResponseEntity<Any> {
        return try {
            clientesBusiness!!.saveCliente(clientes)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_CLIENTES + "/" + clientes.id)
            ResponseEntity(clientes,responseHeader, HttpStatus.CREATED)
        }catch (e: BusinessException){
            val apiError = RestApiError(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",
                    e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addClientes")
    fun insert(@RequestBody clientes: List<Clientes>): ResponseEntity<Any> {
        return try {
            ResponseEntity(clientesBusiness!!.saveClientes(clientes), HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody clientes: Clientes): ResponseEntity<Any> {
        return try {
            clientesBusiness!!.updateClientes(clientes)
            ResponseEntity(clientes, HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Any> {
        return try {
            clientesBusiness!!.removeClientes(id)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}