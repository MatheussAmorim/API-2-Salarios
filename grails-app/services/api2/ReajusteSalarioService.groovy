package api2

import grails.gorm.services.Service

@Service(ReajusteSalario)
interface ReajusteSalarioService {

    ReajusteSalario get(Serializable id)

    List<ReajusteSalario> list(Map args)

    Long count()

    ReajusteSalario delete(Serializable id)

    ReajusteSalario save(ReajusteSalario reajusteSalario)

}
