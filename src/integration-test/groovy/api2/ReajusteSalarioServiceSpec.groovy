package api2

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import org.grails.datastore.mapping.core.Datastore
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class ReajusteSalarioServiceSpec extends Specification {

    ReajusteSalarioService reajusteSalarioService
    @Autowired Datastore datastore

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ReajusteSalario(...).save(flush: true, failOnError: true)
        //new ReajusteSalario(...).save(flush: true, failOnError: true)
        //ReajusteSalario reajusteSalario = new ReajusteSalario(...).save(flush: true, failOnError: true)
        //new ReajusteSalario(...).save(flush: true, failOnError: true)
        //new ReajusteSalario(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //reajusteSalario.id
    }

    void cleanup() {
        assert false, "TODO: Provide a cleanup implementation if using MongoDB"
    }

    void "test get"() {
        setupData()

        expect:
        reajusteSalarioService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ReajusteSalario> reajusteSalarioList = reajusteSalarioService.list(max: 2, offset: 2)

        then:
        reajusteSalarioList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        reajusteSalarioService.count() == 5
    }

    void "test delete"() {
        Long reajusteSalarioId = setupData()

        expect:
        reajusteSalarioService.count() == 5

        when:
        reajusteSalarioService.delete(reajusteSalarioId)
        datastore.currentSession.flush()

        then:
        reajusteSalarioService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ReajusteSalario reajusteSalario = new ReajusteSalario()
        reajusteSalarioService.save(reajusteSalario)

        then:
        reajusteSalario.id != null
    }
}
