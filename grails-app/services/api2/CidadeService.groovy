package api2

import grails.gorm.services.Service

@Service(Cidade)
interface CidadeService {

    Cidade get(Serializable id)

    List<Cidade> list(Map args)

    Long count()

    Cidade delete(Serializable id)

    Cidade save(Cidade cidade)

}
