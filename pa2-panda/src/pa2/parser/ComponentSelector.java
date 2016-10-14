package pa2.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import pa2.Component;

public class ComponentSelector {
	
	protected HashMap<String, List> selector;
	protected HashMap<String , ComponentNode> resource;
	
	public ComponentSelector(String filepath, HashMap<String , ComponentNode> map){
		selector = new HashMap<>();
		try{
			File f = new File(filepath);
			Scanner scan = new Scanner(f);
			
			while(scan.hasNextLine()){
				String tmp = scan.nextLine();
				
				String[] key_values = tmp.split(":");
				String key = key_values[0];
				String[] values = key_values[1].split(",");
				List<String> value = Arrays.asList(values);
				
				selector.put(key, value);
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<ComponentNode> select(String key){
		ArrayList<ComponentNode> result = new ArrayList<>();
		List<String> labels = selector.get(key);
		
		for(String label : labels){
			ComponentNode node = resource.get(label);
			result.add(node);
		}
		return result;
		
	}

}
