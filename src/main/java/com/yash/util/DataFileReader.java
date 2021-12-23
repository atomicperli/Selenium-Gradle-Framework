package com.yash.util;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;


public class DataFileReader {

    public JSONObject getJson(String fileName) throws IOException, ParseException {
        JSONParser parser= new JSONParser();
        Object object= parser.parse(new FileReader(fileName));
        JSONObject jsonObject = (JSONObject) object;
        return jsonObject;
    }

    public JSONObject mergeJsonFiles(JSONObject[] arr) {
        JSONObject merged = new JSONObject();
        for (JSONObject obj : arr) {
            merged.putAll(obj);
            }
        return merged;
    }
}
