package com.example.demo.utils

class Constants {
    companion object{
        private const val URL_API_BASE    = "/api"
        private const val URL_API_VERSION = "/v1"
        private const val URL_BASE = URL_API_BASE + URL_API_VERSION
        const val URL_BASE_CLIENTES         = "$URL_BASE/clientes"
        const val URL_BASE_COMERCIOS        = "$URL_BASE/Comercios"
        const val URL_BASE_ESSTADOFACTURA   = "$URL_BASE/EstadoFactura"
        const val URL_BASE_LOCALIDAD        = "$URL_BASE/Localidad"
        const val URL_BASE_PRODUCTOS        = "$URL_BASE/Productos"

    }


}