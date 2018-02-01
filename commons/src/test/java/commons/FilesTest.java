package commons;

import com.riguz.commons.io.Files;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FilesTest {

    @Test
    public void readIniFile() throws IOException {
        List<String> strs = Files.readLines("config.ini");
        assertEquals(false, strs.isEmpty());
    }
}
