package com.kirasystems.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonUtilities {

	public static Properties readPropertiesFile(String fileName) {
		Properties prop = null;
		try (InputStream input = new FileInputStream(fileName)) 
		{
		prop = new Properties();
            prop.load(input);

        } 
		catch (IOException ex) 
		{
            ex.printStackTrace();
        }
		return prop;
	}
}