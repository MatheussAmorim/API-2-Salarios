package api2

class BootStrap {

    def init = { servletContext ->

        if (Cidade.count() == 0) {
            Cidade.withTransaction { status ->
                Cidade cidade1 = new Cidade(nome: "Sapiranga").save(flush: true)
                Cidade cidade2 = new Cidade(nome: "Novo Hamburgo").save(flush: true)
                Cidade cidade3 = new Cidade(nome: "Dois Irmãos").save(flush: true)
            }
        }
        if (Funcionario.count() == 0) {
            Funcionario.withTransaction { status ->
                Funcionario funcionario1 = new Funcionario(nome: "Matheus Amorim", cidade: 1).save(flush:true)
                Funcionario funcionario2 = new Funcionario(nome: "João Pedro", cidade: 2).save(flush:true)
            }
        }
        if (ReajusteSalario.count() == 0) {
            ReajusteSalario.withTransaction { status ->
                ReajusteSalario reajusteSalario1 = new ReajusteSalario(dataReajuste: "2022-02-01", valorSalario: 6500, funcionario: 2).save(flush:true)
            }
        }
    }
    def destroy = {

    }
}
