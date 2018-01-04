package ioc;

import com.riguz.forks.ioc.old.Bind;
import com.riguz.forks.ioc.old.Injector;
import javax.inject.Inject;
import javax.inject.Qualifier;
import org.junit.Test;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.junit.Assert.assertEquals;

public class CustomQualifierTest {

    @Qualifier
    @Retention(RUNTIME)
    public @interface FooImpl {

    }

    @Qualifier
    @Retention(RUNTIME)
    public @interface BarImpl {

    }

    public static class Config {

        @Bind
        @FooImpl
        public String foo() {
            return "foo";
        }

        @Bind
        @BarImpl
        public String bar() {
            return "bar";
        }

        @Bind
        public String str(@FooImpl String foo, @BarImpl String bar) {
            return "default" + foo + bar;
        }
    }

    public static class Controller {

        @Inject
        @FooImpl
        private String foo;

        @Inject
        @BarImpl
        private String bar;

        @Inject
        private String str;
    }

    @Test
    public void qualifierInject() {
        Injector injector = new Injector(new Config());
        Controller controller = new Controller();
        injector.injectFields(controller);

        assertEquals("foo", controller.foo);
        assertEquals("bar", controller.bar);
        assertEquals("defaultfoobar", controller.str);
    }
}
