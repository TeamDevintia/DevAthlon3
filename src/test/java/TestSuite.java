import io.github.teamdevintia.devathlon3.DirectionUtilTest;
import org.junit.Test;
import org.junit.runners.Suite;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test suite for all tests
 *
 * @author MiniDigger
 */
@Suite.SuiteClasses(value = {DirectionUtilTest.class})
public class TestSuite {

    @Test
    public void testTest() {
        // unit testing minecraft is hard...
        assertThat(thisTestIsUseless(), is(true));
    }

    private boolean thisTestIsUseless(){
        return true;
    }
}
