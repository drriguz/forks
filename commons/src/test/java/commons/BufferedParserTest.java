package commons;

import com.riguz.commons.io.BufferedParser;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class BufferedParserTest extends BufferedParser {

    public BufferedParserTest() {
        super(5);
    }

    @Test
    public void testRead() throws IOException {
        Reader reader = new StringReader("123456789");
        this.reader = reader;
        boolean i = this.read();
        assertEquals(true, i);
        assertEquals(this.currentValue, '1');
        String str = "";
        str += (String.valueOf((char) this.currentValue));
        while (i) {
            i = this.read();
            if (i) {
                str += (String.valueOf((char) this.currentValue));
            }
        }
        assertEquals(-1, this.currentValue);
        assertEquals("123456789", str);
    }

    @Override
    protected void readValue() throws IOException {
        
    }
}
