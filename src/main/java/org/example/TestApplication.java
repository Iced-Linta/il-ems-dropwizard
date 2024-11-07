package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.example.controllers.ClientController;
import org.example.controllers.SalesEmployeeController;
import org.example.controllers.DeliveryEmployeeController;
import org.example.controllers.TestController;
import org.example.daos.ClientDao;
import org.example.daos.DeliveryEmployeeDao;
import org.example.daos.SalesEmployeeDao;
import org.example.daos.TestDao;
import org.example.services.ClientService;
import org.example.services.DeliveryEmployeeService;
import org.example.services.SalesEmployeeService;
import org.example.services.TestService;
import org.example.validators.ClientValidator;
import org.example.validators.DeliveryEmployeeValidator;

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
