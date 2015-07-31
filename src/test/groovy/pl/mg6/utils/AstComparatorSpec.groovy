package pl.mg6.utils

import spock.lang.Specification

import static pl.mg6.utils.AstComparator.assertThat

class AstComparatorSpec extends Specification {

    def "should fail on different class names"() {
        def test = new AstResourcesHelper().loadTest("/astcomparator/DifferentClassNames")
        when:
        assertThat test[0] isEqualTo test[1]
        then:
        def e = thrown(RuntimeException)
        e.message == "Different class names. \nActual: ActualClassName \nExpect: ExpectedClassName "
    }

    def "should fail on different package names"() {
        def test = new AstResourcesHelper().loadTest("/astcomparator/DifferentPackageNames")
        when:
        assertThat test[0] isEqualTo test[1]
        then:
        def e = thrown(RuntimeException)
        e.message == "Different class names. \nActual: actual.ExpectedClassName \nExpect: expected.ExpectedClassName "
    }

    def "should fail on different number of classes"() {
        def test = new AstResourcesHelper().loadTest("/astcomparator/DifferentNumberOfClasses")
        when:
        assertThat test[0] isEqualTo test[1]
        then:
        def e = thrown(RuntimeException)
        e.message.startsWith "Node count different 3 != 2. \nActual: "
    }

    def "Should fail on different methods names"() {
        def test = new AstResourcesHelper().loadTest('/astcomparator/DifferentMethodName')
        when:
        assertThat test[0] isEqualTo test[1]
        then:
        def e = thrown(RuntimeException)
        e.message.startsWith "Different method names. \nActual:"
    }

    def "Should fail on different methods arg types"() {
        def test = new AstResourcesHelper().loadTest('/astcomparator/DifferentMethodArgType')
        when:
        assertThat test[0] isEqualTo test[1]
        then:
        def e = thrown(RuntimeException)
        e.message.startsWith "Different method names. \nActual:"
    }

    def "Should fail on different methods bodies"() {
        def test = new AstResourcesHelper().loadTest('/astcomparator/DifferentMethodBody')
        when:
        assertThat test[0] isEqualTo test[1]
        then:
        def e = thrown(RuntimeException)
        e.message.startsWith "Different types of nodes. \nActual:"
    }
}
