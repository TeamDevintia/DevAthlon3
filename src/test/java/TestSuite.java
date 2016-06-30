import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Martin on 30.06.2016.
 */
public class TestSuite {

    @Test
    public void testTest() {
        assertThat( true, is( true ) );
    }
}
