package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;

public class Hooks {

    public static DriverFactory factory;

    @Before
    public void setup() {

        factory = new DriverFactory();
        factory.getDriver().manage().window().maximize();
        System.out.println("Browser Started");
    }

    @After
    public void tearDown() {

        factory.CloseDriver();
        System.out.println("Browser Closed");
    }
}