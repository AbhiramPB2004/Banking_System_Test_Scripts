package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )

    {

        WebDriver w1 = new EdgeDriver();
        w1.get("www.google.com");
        System.out.println( "Hello World!" );
    }
}
