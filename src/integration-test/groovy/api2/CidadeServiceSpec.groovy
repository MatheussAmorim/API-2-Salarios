package api2

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import org.grails.datastore.mapping.core.Datastore
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class CidadeServiceSpec extends Specification {

    CidadeService cidadeService
    @Autowired Datastore datastore

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Cidade(...).save(flush: true, failOnError: true)
        //new Cidade(...).save(flush: true, failOnError: true)
        //Cidade cidade = new Cidade(...).save(flush: true, failOnError: true)
        //new Cidade(...).save(flush: true, failOnError: true)
        //new Cidade(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //cidade.id
    }

    void cleanup() {
        assert false, "TODO: Provide a cleanup implementation if using MongoDB"
    }

    void "test get"() {
        setupData()

        expect:
        cidadeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Cidade> cidadeList = cidadeService.list(max: 2, offset: 2)

        then:
        cidadeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        cidadeService.count() == 5
    }

    void "test delete"() {
        Long cidadeId = setupData()

        expect:
        cidadeService.count() == 5

        when:
        cidadeService.delete(cidadeId)
        datastore.currentSession.flush()

        then:
        cidadeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Cidade cidade = new Cidade()
        cidadeService.save(cidade)

        then:
        cidade.id != null
    }
}
