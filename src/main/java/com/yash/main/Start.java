package com.yash.main;

import java.util.ArrayList;
import java.util.List;
import org.testng.TestNG;

public class Start {

    public static String TEST_NG_SUIT_FILE_XML = null;

    public static void main(String[] args) {
        String user_dir = System.getProperty("user.dir");
        TEST_NG_SUIT_FILE_XML = user_dir + "/testng.xml";
        List<String> suites = new ArrayList<String>();
        suites.add(TEST_NG_SUIT_FILE_XML);
        TestNG testng = new TestNG();
        testng.setTestSuites(suites);
        testng.run();
        System.exit(testng.getStatus());
    }
}