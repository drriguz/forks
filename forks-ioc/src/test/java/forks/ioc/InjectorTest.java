package forks.ioc;

import com.riguz.forks.ioc.Bind;
import com.riguz.forks.ioc.Injector;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InjectorTest {

    public static class Config {

        @Bind
        String str() {
            return "hello world!";
        }

        @Bind
        Bar bar() {
            return new Bar(new Foo());
        }

    }

    public static class Foo {

    }

    public static class Bar {

        private Foo foo;

        public Bar(Foo foo) {

            this.foo = foo;
        }
    }

    @Test
    public void getInstance() {
        Injector injector = new Injector(new Config());
        String str = injector.getInstance(String.class);
        assertEquals("hello world!", str);

        Bar bar = injector.getInstance(Bar.class);
        assertNotNull(bar);
        assertNotNull(bar.foo);
    }

    @Test
    public void getInjectorInstance() {
        Injector injector = new Injector(new Config());
        Injector self = injector.getInstance(Injector.class);
        assertEquals(injector, self);
    }
}
