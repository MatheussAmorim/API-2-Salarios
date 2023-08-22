package api2

class Funcionario {


    Long id
    String nome
    Cidade cidade

    static mapping = {
        id generator: 'sequence', params: [sequence: 'SEQ_FUNCIONARIO']
        version false
    }

    static belongsTo = [cidade: Cidade]

    static constraints = {
        nome nullable: false, maxSize: 50
    }
}
