package forks.ioc;

import com.riguz.forks.ioc.Bind;
import com.riguz.forks.ioc.InjectConfig;
import com.riguz.forks.ioc.Injector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InjectorTest {

    public static class Config implements InjectConfig {

        @Bind
        String str() {
            return "hello world!";
        }
    }

    @Test
    public void getInstance() {
        Injector injector = new Injector(new Config());
        String str = injector.getInstance(String.class);
        assertEquals("hello world!", str);
    }
}
