package com.yash.main;

import java.util.ArrayList;
import java.util.List;
import org.testng.TestNG;

public class Start {

    public static String TEST_NG_SUIT_FILE_XML = null;
    public static String DOCKER_RUN = null;

    public static void main(String[] args) {
        TEST_NG_SUIT_FILE_XML = args[0];
        DOCKER_RUN = args[1];
        String user_dir = System.getProperty("user.dir");
        List<String> suites = new ArrayList<String>();
        suites.add(TEST_NG_SUIT_FILE_XML);
        TestNG testng = new TestNG();
        testng.setTestSuites(suites);
        testng.run();
        System.exit(testng.getStatus());
    }
}