package com.bank.qa;

import org.junit.jupiter.api.*;

public abstract class BaseTest {

    @BeforeAll
    static void globalSetup(){
        System.out.println(">>> Initializing Bank Automation Framework...");
    }

    @AfterAll
    static void globalTearDown(){
        System.out.println(">>> Test Execution Finished. Cleaning up...");
    }

    @BeforeEach
    void logTestStart(TestInfo testInfo){
        System.out.println("Starting Test: " + testInfo.getDisplayName());
    }
}
