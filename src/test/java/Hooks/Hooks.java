package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.CleanupUtility;
import utils.DriverFactory;
import utils.ScreenShotUtil;

public class Hooks {
    public static DriverFactory factory;

    @Before
    public void setup() {
        factory = new DriverFactory();
        factory.getDriver().manage().window().maximize();
        System.out.println("Browser Started");
    }

    @After
    public void tearDown(io.cucumber.java.Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Scenario Failed: " + scenario.getName());
            ScreenShotUtil screenShotUtil = new ScreenShotUtil();
            screenShotUtil.TakeScreenShot(factory.getDriver(), scenario.getName());
        }

//        try{
//            //CleanupUtility.revertTestData();
//
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }

        factory.CloseDriver();

        System.out.println(
                "Browser Closed"
        );
    }
}
