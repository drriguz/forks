package commons;

import com.riguz.commons.config.IniReader;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class IniReaderTest {

    @Test
    public void readIniFile() throws IOException {
        IniReader reader = new IniReader("config.ini");
        assertEquals("\"Hello World!\"", reader.get("example", "str"));
    }
}
