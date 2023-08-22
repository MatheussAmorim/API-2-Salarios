package api2

import java.time.LocalDate

class ReajusteSalario {

    Long id
    LocalDate dataReajuste
    BigDecimal valorSalario
    Funcionario funcionario

    static mapping = {
        id generator: 'sequence', params: [sequence: 'SEQ_REAJUSTE_SALARIO']
        version false
    }

    static belongsTo = [funcionario: Funcionario]

    static constraints = {
        dataReajuste nullable: false
        valorSalario nullable: false
        funcionario nullable: false
    }
}
