package com.example.infrastructure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.example.domain.FileLoader;

@Component
public class FileLoaderImpl implements FileLoader {

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public String loadFile(String fileName) throws IOException {

        Resource resource = resourceLoader.getResource("classpath:" + fileName);
        File file = resource.getFile();

        StringBuffer sb = new StringBuffer();
        try (FileReader fileReader = new FileReader(file); BufferedReader br = new BufferedReader(fileReader)) {

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            throw e;
        }
        return sb.toString();

    }

}
