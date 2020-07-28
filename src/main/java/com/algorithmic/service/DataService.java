package com.algorithmic.service;

import com.algorithmic.model.Schedule;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class DataService {
    public Schedule readJSON(String pathFile) throws FileNotFoundException {
        URL url = getClass().getResource("/"+ pathFile);
        if(url != null) {
            String path = url.getPath();
            path = URLDecoder.decode(path, StandardCharsets.UTF_8);
            return new Gson().fromJson(new FileReader(new File(path)), Schedule.class);
        }
        return null;
    }
}
