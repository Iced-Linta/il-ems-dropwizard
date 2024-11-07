package org.example;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.jsonwebtoken.Jwts;
import org.example.auth.JwtAuthenticator;
import org.example.auth.RoleAuthorisor;
import org.example.controllers.AuthController;
import org.example.controllers.SalesEmployeeController;
import org.example.controllers.ClientController;
import org.example.controllers.DeliveryEmployeeController;
import org.example.controllers.ProjectController;
import org.example.controllers.SalesEmployeeController;
import org.example.controllers.TestController;
import org.example.daos.AuthDao;
import org.example.daos.DeliveryEmployeeDao;
import org.example.daos.SalesEmployeeDao;
import org.example.daos.TestDao;
import org.example.models.JwtToken;
import org.example.services.AuthService;
import org.example.daos.ClientDao;
import org.example.daos.DeliveryEmployeeDao;
import org.example.daos.ProjectDao;
import org.example.daos.TestDao;
import org.example.services.ClientService;
import org.example.services.DeliveryEmployeeService;
import org.example.services.ProjectService;
import org.example.services.SalesEmployeeService;
import org.example.services.TestService;
import org.example.validators.ClientValidator;
import org.example.validators.DeliveryEmployeeValidator;

import javax.crypto.SecretKey;
import java.security.Key;

public class TestApplication extends Application<TestConfiguration> {
    public static void main(final String[] args) throws Exception {
        new TestApplication().run(args);
    }

    @Override
    public String getName() {
        return "Test";
    }


    @Override
    public void run(final TestConfiguration configuration,
                    final Environment environment) {
        SecretKey jwtKey = Jwts.SIG.HS256.key().build();

        environment.jersey().register(
                new AuthDynamicFeature(
                        new OAuthCredentialAuthFilter.Builder<JwtToken>()
                                .setAuthenticator(new JwtAuthenticator(jwtKey))
                                .setAuthorizer(new RoleAuthorisor())
                                .setPrefix("Bearer")
                                .buildAuthFilter()
                )
        );

        environment.jersey()
                .register(new TestController(new TestService(new TestDao())));

        environment.jersey()
                .register(
                        new SalesEmployeeController(
                                new SalesEmployeeService(
                                        new SalesEmployeeDao()
                                )
                        )
                );
        environment.jersey().register(new DeliveryEmployeeController(
                new DeliveryEmployeeService(new DeliveryEmployeeDao(),
                        new DeliveryEmployeeValidator())));

        environment.jersey().register(new ProjectController(
                new ProjectService(new ProjectDao())));
      
        environment.jersey().register(new AuthController(
                new AuthService(
                        new AuthDao(), jwtKey
                )
        ));
      
        environment.jersey().register(new ClientController(
                new ClientService(new ClientDao(),
                        new ClientValidator())));
    }

    @Override
    public void initialize(
            final Bootstrap<TestConfiguration> bootstrap) {
        bootstrap.addBundle(
                new SwaggerBundle<TestConfiguration>() {
                    @Override
                    protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                            final TestConfiguration configuration) {
                        return configuration.getSwagger();
                    }
                });
    }
}
