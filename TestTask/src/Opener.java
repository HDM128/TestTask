import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Opener {
	
	public static String[] AllocateAllFiles(String location)
	{
	
		int i = 0;
		File dir1 = new File(location);
		String[] paths = new String[dir1.listFiles().length];
		
	
		if(dir1.isDirectory())
		{
			for(File item : dir1.listFiles())
			{
				if(item.isDirectory() == false)
				{
					paths[i] = item.getAbsolutePath();
					System.out.println(item.getName() +" "+ item.getAbsolutePath() +  " \t file");
					i++;
				}
			}
		}
		else
		{
			System.out.println("Указана некорректная директория!");
			System.exit(0);
		}
		
		return paths;
	
	}
	
	public static ArrayList LoadTheFile (String location) throws FileNotFoundException, IOException, ParseException
	{
		ArrayList<JSONObject> workers = new ArrayList<JSONObject>();
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(location));
		
		
		
		return workers;
	}
	
	public static void main(String[] args) {
		
		String[] targets = AllocateAllFiles("./jsons");
		
	}
}
