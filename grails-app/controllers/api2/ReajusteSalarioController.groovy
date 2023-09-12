package api2

import traits.ExceptionHandlers
import grails.rest.*
import grails.converters.*

class ReajusteSalarioController implements ExceptionHandlers {

    ReajusteSalarioService reajusteSalarioService

    static responseFormats = ["json"]
    static defaultAction = "get"
    static allowedMethods = [
            save: "POST",
            list: "GET",
            update: "PUT",
            delete: "DELETE",
            get: "GET"
    ]

    def save() {
        Map retorno = reajusteSalarioService.save()
        respond(retorno)
    }

    def list() {
        Map retorno = reajusteSalarioService.list()
        respond(retorno)
    }

    def update() {
        Map retorno = reajusteSalarioService.update()
        respond(retorno)
    }

    def get(long id) {
        Map retorno = reajusteSalarioService.get(id)
        respond(retorno)
    }

    def delete(long id) {
        Map retorno = reajusteSalarioService.delete(id)
        respond(retorno)
    }


}