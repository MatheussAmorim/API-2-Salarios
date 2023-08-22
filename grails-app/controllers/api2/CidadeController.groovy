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
class CidadeController {

    CidadeService cidadeService

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond cidadeService.list(params)
    }

    def get(Long id) {
        def cidade = cidadeService.get(id)
        if (!cidade) {
            render status: NOT_FOUND
            return
        }
        respond cidade, [status: OK, view: "show"]
    }

    @Transactional
    def save() {
        if (request.contentType != 'application/json' || request.method != 'POST') {
            render status: UNPROCESSABLE_ENTITY
            return
        }

        def cidade = new Cidade(request.JSON)

        if (cidade.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond cidade.errors, [status: UNPROCESSABLE_ENTITY]
            return
        }

        try {
            cidadeService.save(cidade)
        } catch (ValidationException e) {
            respond cidade.errors, [status: UNPROCESSABLE_ENTITY]
            return
        }
        respond cidade, [status: CREATED, view: "show"]
    }

    @Transactional
    def update(Long id) {
        if (request.contentType != 'application/json' || request.method != 'PUT') {
            render status: UNPROCESSABLE_ENTITY
            return
        }

        def cidade = cidadeService.get(id)
        if (!cidade) {
            render status: NOT_FOUND
            return
        }

        cidade.properties = request.JSON

        if (cidade.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond cidade.errors, [status: UNPROCESSABLE_ENTITY]
            return
        }

        cidadeService.save(cidade)
        respond cidade, [status: OK, view: "show"]
    }

    @Transactional
    def delete(Long id) {
        if (request.method != 'DELETE') {
            render status: UNPROCESSABLE_ENTITY
            return
        }

        def cidade = cidadeService.get(id)
        if (!cidade) {
            render status: NOT_FOUND
            return
        }

        cidadeService.delete(id)
        render status: NO_CONTENT
    }
}
