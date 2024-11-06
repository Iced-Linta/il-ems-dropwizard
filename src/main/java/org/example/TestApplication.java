package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.example.controllers.TestController;
import org.example.daos.DeliveryEmployeeDao;
import org.example.daos.TestDao;
import org.example.services.DeliveryEmployeeService;
import org.example.services.TestService;
import org.example.validators.DeliveryEmployeeValidator;
import org.example.controllers.DeliveryEmployeeController;

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

        environment.jersey().register(new DeliveryEmployeeController(
                new DeliveryEmployeeService(new DeliveryEmployeeDao(), new DeliveryEmployeeValidator())));
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
