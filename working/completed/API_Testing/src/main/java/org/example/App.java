package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class App {
    public static void main(String[] args)
    {
        WebDriver w1 = new ChromeDriver();
        w1.get("https://www.google.com");
        System.out.println("Hello World!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
