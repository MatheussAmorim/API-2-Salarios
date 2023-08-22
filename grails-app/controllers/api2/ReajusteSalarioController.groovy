package api2

import grails.validation.ValidationException
import java.time.LocalDate

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class ReajusteSalarioController {

    ReajusteSalarioService reajusteSalarioService

    String formatLocalDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def reajusteSalarios = reajusteSalarioService.list(params)

        def listaReajusteSalario = reajusteSalarios.collect { reajusteSalario ->
            [
                    id: reajusteSalario.id,
                    valorSalario: reajusteSalario.valorSalario,
                    funcionario: reajusteSalario.funcionario.id,
                    dataReajuste: reajusteSalario.dataReajuste.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            ]
        }
        respond listaReajusteSalario, model: [reajusteSalarioCount: reajusteSalarioService.count()]
    }

    def get(Long id) {
        def reajusteSalario = reajusteSalarioService.get(id)
        if (!reajusteSalario) {
            render status: NOT_FOUND
            return
        }
        def reajusteSalarioGet = [
                id: reajusteSalario.id,
                valorSalario: reajusteSalario.valorSalario,
                funcionario: reajusteSalario.funcionario.id,
                dataReajuste: reajusteSalario.dataReajuste.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        ]
        respond reajusteSalarioGet, model: [reajusteSalarioCount: reajusteSalarioService.count()]
    }


    @Transactional
    def save() {
        if (request.contentType != 'application/json' || request.method != 'POST') {
            render status: UNPROCESSABLE_ENTITY
            return
        }
        def reajusteSalario = new ReajusteSalario(request.JSON)
        if (reajusteSalario.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond reajusteSalario.errors, [status: UNPROCESSABLE_ENTITY]
            return
        }
        try {
            reajusteSalarioService.save(reajusteSalario)
        } catch (ValidationException e) {
            respond reajusteSalario.errors, [status: UNPROCESSABLE_ENTITY]
            return
        }
        respond reajusteSalario, [status: CREATED, view: "show"]
    }

    @Transactional
    def update(Long id) {
        if (request.contentType != 'application/json' || request.method != 'PUT') {
            render status: UNPROCESSABLE_ENTITY
            return
        }

        def reajusteSalario = reajusteSalarioService.get(id)
        if (!reajusteSalario) {
            render status: NOT_FOUND
            return
        }

        reajusteSalario.properties = request.JSON

        if (reajusteSalario.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond reajusteSalario.errors, [status: UNPROCESSABLE_ENTITY]
            return
        }

        reajusteSalarioService.save(reajusteSalario)
        respond reajusteSalario, [status: OK, view: "show"]
    }

    @Transactional
    def delete(Long id) {
        if (request.method != 'DELETE') {
            render status: UNPROCESSABLE_ENTITY
            return
        }
        def reajusteSalario = reajusteSalarioService.get(id)
        if (!reajusteSalario) {
            render status: NOT_FOUND
            return
        }
        reajusteSalarioService.delete(id)
        render status: NO_CONTENT
    }
}
