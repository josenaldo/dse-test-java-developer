package br.com.josenaldo.dsejavadeveloper.annotations;


import br.com.josenaldo.dsejavadeveloper.configuration.WebServerConfig;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ActiveProfiles("test")
@SpringBootTest(classes = WebServerConfig.class)
public @interface IntegrationTest {

}
