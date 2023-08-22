package api2

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class FuncionarioController {

    FuncionarioService funcionarioService

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond funcionarioService.list(params)
    }

    def get(Long id) {
        def funcionario = funcionarioService.get(id)
        if (!funcionario) {
            render status: NOT_FOUND
            return
        }
        respond funcionario, [status: OK, view: "show"]
    }

    @Transactional
    def save() {
        if (request.contentType != 'application/json' || request.method != 'POST') {
            render status: UNPROCESSABLE_ENTITY
            return
        }

        def funcionario = new Funcionario(request.JSON)

        if (funcionario.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond funcionario.errors, [status: UNPROCESSABLE_ENTITY]
            return
        }

        try {
            funcionarioService.save(funcionario)
        } catch (ValidationException e) {
            respond funcionario.errors, [status: UNPROCESSABLE_ENTITY]
            return
        }
        respond funcionario, [status: CREATED, view: "show"]
    }

    @Transactional
    def update(Long id) {
        if (request.contentType != 'application/json' || request.method != 'PUT') {
            render status: UNPROCESSABLE_ENTITY
            return
        }

        def funcionario = funcionarioService.get(id)
        if (!funcionario) {
            render status: NOT_FOUND
            return
        }

        funcionario.properties = request.JSON

        if (funcionario.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond funcionario.errors, [status: UNPROCESSABLE_ENTITY]
            return
        }

        funcionarioService.save(funcionario)
        respond funcionario, [status: OK, view: "show"]
    }

    @Transactional
    def delete(Long id) {
        if (request.method != 'DELETE') {
            render status: UNPROCESSABLE_ENTITY
            return
        }

        def funcionario = funcionarioService.get(id)
        if (!funcionario) {
            render status: NOT_FOUND
            return
        }

        funcionarioService.delete(id)
        render status: NO_CONTENT
    }
}
