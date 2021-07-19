package forks.ioc;

import com.riguz.forks.ioc.InjectType;
import javax.inject.Qualifier;
import org.junit.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class InjectTypeTest {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CustomQualifier {

    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface AnotherCustomQualifier {

    }

    public interface Foo {

    }

    @CustomQualifier
    public static class Foo1 implements Foo {

    }

    public static class Foo2 implements Foo {

    }

    @AnotherCustomQualifier
    public static class Foo3 implements Foo {

    }

    public static class Config {

        @CustomQualifier
        private String str1;

        private String str2;

        @AnotherCustomQualifier
        private String str3;
    }

    @Test
    public void qualifierEquals() throws NoSuchFieldException {
        InjectType<?> t1 = InjectType.of(Foo1.class);
        InjectType<?> t2 = InjectType.of(Foo2.class);
        InjectType<?> t3 = InjectType.of(Foo2.class);
        InjectType<?> t4 = InjectType.of(Foo3.class);
        assertNotEquals(t1, t2);
        assertEquals(t2, t3);
        assertNotEquals(t1, t4);
        assertNotEquals(t2, t4);

        InjectType<?> f1 = InjectType.of(Config.class.getDeclaredField("str1"));
        InjectType<?> f2 = InjectType.of(Config.class.getDeclaredField("str2"));
        InjectType<?> f3 = InjectType.of(Config.class.getDeclaredField("str3"));
        assertNotEquals(f1, f2);
        assertNotEquals(f2, f3);
        assertNotEquals(f1, f3);
    }
}
