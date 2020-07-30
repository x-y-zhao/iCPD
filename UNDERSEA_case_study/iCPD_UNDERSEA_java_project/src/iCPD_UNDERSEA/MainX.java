package iCPD_UNDERSEA;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import _main.PRISMPSY_API_Runner;
import _main.Property;

public class MainX {


	public static void main(String[] args) {
		//testPRISM_PSY();
		run_UNDERSEA();
		System.out.println("All done...");
	}
	
	
	
	public static void testPRISM_PSY() {
		
		System.out.println("Testing PRISM-PSY with an example!");
		// get bounds for properties
		// get bounds for properties
		PRISMPSY_API_Runner runner = new PRISMPSY_API_Runner();
				
		String modelFile			= "models/UNDERSEA/sensors.sm";
		String propertiesFile		= "models/UNDERSEA/prop.csl";
		String paramsWithRanges	= "r1=2:6";

		//for each property, this function results its minimum and maximum values
		System.out.println("invoking PRISM-PSY");
				
		Property[] properties = runner.calculatePropertiesBounds(modelFile, propertiesFile, paramsWithRanges);
				
		for (Property p : properties) {
			System.out.println(Arrays.toString(p.getMinMax()));
		}

		Object[] results = runner.run(modelFile, propertiesFile, paramsWithRanges);
		System.out.println(Arrays.toString(results));
		System.out.println(results[0]);
		
		System.out.println("PRISM-PSY is working nicely!");
		
	}
	
public static void run_UNDERSEA() {
		
			
		try{
			PRISMPSY_API_Runner runner = new PRISMPSY_API_Runner();
			
			String modelFile			= "models/UNDERSEA/sensors.sm";
			String propertiesFile		= "models/UNDERSEA/prop.csl";
			
			//read write the CSV files
			String row;
			BufferedReader csvReader = new BufferedReader(new FileReader("iCPD_est_r_every_1_second.csv"));
			FileWriter csvWriter = new FileWriter("iCPD_est_r_every_1_second_verification_results.csv");
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				System.out.println(data[0]+':'+data[1]);
				
				String paramsWithRanges		= "r1=";
				paramsWithRanges=paramsWithRanges+data[0]+':'+data[1];
				
						
				Object[] results = runner.run(modelFile, propertiesFile, paramsWithRanges);
				System.out.println(Arrays.toString(results));
				
				csvWriter.append(results[0].toString());
				csvWriter.append(",");
				csvWriter.append(results[1].toString());
				csvWriter.append(",");
				csvWriter.append(results[2].toString());
				csvWriter.append(",");
				csvWriter.append(results[3].toString());
				//csvWriter.append(",");
				csvWriter.append("\n");
				
				
			}
			
			
			csvWriter.flush();
			csvWriter.close();
			csvReader.close();
			
			BufferedReader csvReader2 = new BufferedReader(new FileReader("IPSP_est_r_every_1_second.csv"));
			FileWriter csvWriter2 = new FileWriter("IPSP_est_r_every_1_second_verification_results.csv");
			while ((row = csvReader2.readLine()) != null) {
				String[] data = row.split(",");
				System.out.println(data[0]+':'+data[1]);
				
				String paramsWithRanges		= "r1=";
				paramsWithRanges=paramsWithRanges+data[0]+':'+data[1];
				
								
				Object[] results = runner.run(modelFile, propertiesFile, paramsWithRanges);
				System.out.println(Arrays.toString(results));
				
				csvWriter2.append(results[0].toString());
				csvWriter2.append(",");
				csvWriter2.append(results[1].toString());
				csvWriter2.append(",");
				csvWriter2.append(results[2].toString());
				csvWriter2.append(",");
				csvWriter2.append(results[3].toString());
				//csvWriter2.append(",");
				csvWriter2.append("\n");
				
				
			}
			
			
			csvWriter2.flush();
			csvWriter2.close();
			csvReader2.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage()+" Make sure it is under the root folder of the project.");
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		
	}
}
