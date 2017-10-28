package com.assignment.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.assingment.entity.Deal;



public class ReadCsvFile {

	public static void main(String[] args) throws FileNotFoundException {
		//processInputFile("D://demo2.csv");
		
		//Matcher d = Pattern.compile("^[1-9][0-9]{0,5}$").matcher("888888");
		
		/*Matcher d = Pattern.compile("^null|$").matcher(",null");
		
		 System.out.println(d.find());
*/
		ArrayList<String> animals = new ArrayList<String>();
		animals.add("bat");
		animals.add("owl");
		animals.add("bat");
		animals.add("bat");
		
		ArrayList<String> c = new ArrayList<String>();
		c.add("bat");
		c.add("owl");
		c.add("bat");
		c.add("cat");
		
		animals.addAll(c);
		
		Map<String, Long> counts =  animals.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		
		System.out.println(counts);
		
		for (int j = 0; j < counts.size(); j++) {
			System.out.println(counts.get(j));
		}
	}

	private static List<Deal> processInputFile(final String inputFilePath) throws FileNotFoundException {
		List<Deal> inputList = new ArrayList<Deal>();
		try {
			File inputF = new File("D://demo2.csv");
			InputStream inputFS = new FileInputStream(inputF);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

			// skip the header of the csv
			inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
			
			System.out.println(inputList);
			br.close();
		} catch (IOException e) {

		}
		return inputList;
	}
	
	private static Function<String, Deal> mapToItem = (line) -> {
		
		String[] p = line.split(",");// a CSV has comma separated lines
		
		//System.out.println(line);
		Deal dto = new Deal();
		
		try {if (p[0] != null && p[0].trim().length() > 0) {
			dto.setDeal_id(p[0]);
		}else{
			 System.out.println("in else 0");
		}
		if (p[1] != null && p[1].trim().length() > 0) {
			dto.setFrom_currency_iso_code(p[1]);
		}else{
			System.out.println("in else 1");
		}
		
		if (p[2] != null && p[2].trim().length() > 0) {
			dto.setTo_currency_iso_code(p[2]);
		}else{
			System.out.println("in else 2");
		}
		if (p[3] != null && p[3].trim().length() > 0) {
			dto.setTime_stamp(p[3]);
		}else{
			System.out.println("in else 3");
		}
		if (p[4] != null && p[4].trim().length() > 0) { //
			dto.setAmount_ordering_currency(p[4]);
		}else{
			System.out.println("in else 4");
		}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return dto;
		  
		};

}
