import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {
    private static OffByN ob5 = new OffByN(5);
    private static OffByN ob3 = new OffByN(3);

    @Test
    public void testTestOffByN5() {
        assertTrue(ob5.equalChars('a', 'f'));
        assertTrue(ob3.equalChars('g', 'd'));
        assertFalse(ob5.equalChars('m', 'm'));
        assertFalse(ob3.equalChars('o', 'n'));
    }
}
