package commons;

import com.riguz.commons.base.Arrays;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArraysTest {

    @Test
    public void concat() {
        Integer[] a = new Integer[]{1, 2, 3};
        Integer[] b = new Integer[]{4, 5, 6};
        Integer[] c = Arrays.concat(a, b);
        assertEquals(6, c.length);
    }
}
