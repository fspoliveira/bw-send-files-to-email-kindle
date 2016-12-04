package br.com.bitwaysystem.file;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAllFiles {
	
	private Map<String,List<File>> mapFilesByType = new HashMap<String,List<File>>();	
	
	public Map<String,List<File>> listf(String directoryName) {
	    
	    File directory = new File(directoryName);

	    // get all the files from a directory
	    File[] fList = directory.listFiles();

	    for (File file : fList) {
	        if (file.isFile()) {	            
	            
	           if(!mapFilesByType.containsKey(FileUtils.getFileExtension(file))){
	        	   mapFilesByType.put(FileUtils.getFileExtension(file), new ArrayList<File>());
	           }
	           
	           mapFilesByType.get(FileUtils.getFileExtension(file)).add(file);
	            
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath());
	        }
	    }    
	    
	    return mapFilesByType;
	} 
}
