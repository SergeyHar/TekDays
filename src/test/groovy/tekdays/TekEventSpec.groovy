package tekdays

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TekEvent)
class TekEventSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

//    void "test something"() {
//        expect:"fix me"
//            true == false
//    }
    void "test create"() {
        when:
        controller.create()

        then:
        response.text == 'Groovy One, San Francisco, CA'
    }
//   void toString() {
//        def tekEvent = new TekEvent(name: 'Groovy One' ,
//                city: 'San Francisco, CA' ,
//                organizer: 'John Doe' ,
//                venue: 'Moscone Center' ,
//                startDate: new Date('6/2/2009' ),
//                endDate: new Date('6/5/2009' ),
//                description: 'This conference will cover all...' )
//        expect:
//        'Groovy One, San Francisco, CA' == tekEvent.toString()
//    }
}
