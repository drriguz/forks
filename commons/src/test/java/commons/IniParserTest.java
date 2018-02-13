package commons;

import com.riguz.commons.config.IniParser;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class IniParserTest {

    @Test
    public void parseNormalIni() throws IOException {
        IniParser iniParser = new IniParser();
        Reader reader = new StringReader("[datasource]\nurl=http://localhost\nname=lee\n");
        iniParser.parse(reader);
    }
}
