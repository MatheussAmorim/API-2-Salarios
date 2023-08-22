package api2

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import org.grails.datastore.mapping.core.Datastore
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class ReajusteServiceSpec extends Specification {

    ReajusteService reajusteService
    @Autowired Datastore datastore

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Reajuste(...).save(flush: true, failOnError: true)
        //new Reajuste(...).save(flush: true, failOnError: true)
        //Reajuste reajuste = new Reajuste(...).save(flush: true, failOnError: true)
        //new Reajuste(...).save(flush: true, failOnError: true)
        //new Reajuste(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //reajuste.id
    }

    void cleanup() {
        assert false, "TODO: Provide a cleanup implementation if using MongoDB"
    }

    void "test get"() {
        setupData()

        expect:
        reajusteService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Reajuste> reajusteList = reajusteService.list(max: 2, offset: 2)

        then:
        reajusteList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        reajusteService.count() == 5
    }

    void "test delete"() {
        Long reajusteId = setupData()

        expect:
        reajusteService.count() == 5

        when:
        reajusteService.delete(reajusteId)
        datastore.currentSession.flush()

        then:
        reajusteService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Reajuste reajuste = new Reajuste()
        reajusteService.save(reajuste)

        then:
        reajuste.id != null
    }
}
