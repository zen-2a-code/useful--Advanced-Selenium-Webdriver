package com.herokuapp.theinternet.base;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * This class provides data from CSV files for TestNG tests.
 * It uses OpenCSV to read CSV files and supplies the data to test methods.
 */
public class CsvDataProvidersParallel {

    /**
     * A DataProvider method for TestNG that reads data from a CSV file.
     * The CSV file path is constructed based on the test method's class and name.
     *
     * @param method The test method that is requesting the data.
     * @return An iterator over arrays of objects, where each array contains a map of test data.
     */
    @DataProvider(name = "csvReader", parallel = true)
    public static Iterator<Object[]> csvReader(Method method) {
        // List to hold the test data read from the CSV file
        List<Object[]> list = new ArrayList<>();

        // Construct the path to the CSV file based on the test method's class and name
        String pathname = "src" + File.separator + "test" + File.separator + "resources" + File.separator
                + "DataProviders" + File.separator + method.getDeclaringClass().getSimpleName() + File.separator
                + method.getName() + ".csv";
        File file = new File(pathname);

        // Use try-with-resources to ensure FileReader and CSVReader are closed properly
        try (FileReader fileReader = new FileReader(file); CSVReader reader = new CSVReader(fileReader)) {
            // Read the first line of the CSV file to get the keys (column headers)
            String[] keys = reader.readNext();

            // If the keys are not null, read the remaining lines (rows of data)
            if (keys != null) {
                String[] dataParts;
                // Read each line of the CSV file
                while ((dataParts = reader.readNext()) != null) {
                    // Map to hold the test data for each row
                    Map<String, String> testData = new HashMap<>();
                    // Populate the map with key-value pairs from the CSV row
                    for (int i = 0; i < keys.length; i++) {
                        testData.put(keys[i], dataParts[i]);
                    }
                    // Add the map to the list as an array of objects
                    list.add(new Object[]{testData});
                }
            }
        } catch (FileNotFoundException e) {
            // Handle the case where the CSV file is not found
            throw new RuntimeException("File " + pathname + " was not found.", e);
        } catch (IOException e) {
            // Handle general I/O exceptions
            throw new RuntimeException("Could not read " + pathname + " file.", e);
        } catch (CsvValidationException e) {
            // Handle CSV validation exceptions from OpenCSV
            throw new RuntimeException(e);
        }

        // Return an iterator over the list of test data
        return list.iterator();
    }
}