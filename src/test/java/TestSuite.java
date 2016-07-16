import io.github.teamdevintia.devathlon3.DirectionUtilTest;
import org.junit.Test;
import org.junit.runners.Suite;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Martin on 30.06.2016.
 */
@Suite.SuiteClasses( value = { DirectionUtilTest.class } )
public class TestSuite {

    @Test
    public void testTest() {
        assertThat( true, is( true ) );
    }
}
