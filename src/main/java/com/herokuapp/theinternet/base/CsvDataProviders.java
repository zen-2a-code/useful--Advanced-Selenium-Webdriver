package com.herokuapp.theinternet.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opencsv.exceptions.CsvValidationException;
import org.testng.annotations.DataProvider;

import com.opencsv.CSVReader;

public class CsvDataProviders {

    @DataProvider(name = "csvReader")
    public static Iterator<Object[]> csvReader(Method method) {
        List<Object[]> list = new ArrayList<Object[]>();
        String pathname = "src" + File.separator + "test" + File.separator + "resources" + File.separator
                + "DataProviders" + File.separator + method.getDeclaringClass().getSimpleName() + File.separator
                + method.getName() + ".csv";
        File file = new File(pathname);

        // Using try-with-resources to ensure resources are closed properly
        try (FileReader fileReader = new FileReader(file); CSVReader reader = new CSVReader(fileReader)) {
            String[] keys = reader.readNext();
            if (keys != null) {
                String[] dataParts;
                while ((dataParts = reader.readNext()) != null) {
                    Map<String, String> testData = new HashMap<String, String>();
                    for (int i = 0; i < keys.length; i++) {
                        testData.put(keys[i], dataParts[i]);
                    }
                    list.add(new Object[] { testData });
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + pathname + " was not found.", e);
        } catch (IOException e) {
            throw new RuntimeException("Could not read " + pathname + " file.", e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return list.iterator();
    }
}