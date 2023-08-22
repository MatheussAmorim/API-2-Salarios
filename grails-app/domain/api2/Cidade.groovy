package api2

class Cidade {

    Long id
    String nome


    static mapping = {
        id generator: 'sequence', params: [sequence: 'SEQ_CIDADE']
        version false
    }

    static constraints = {
        nome nullable: false, blank: false, maxSize: 50
    }
}
