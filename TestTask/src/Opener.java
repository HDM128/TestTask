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
		//ArrayList<JSONObject> workers = new ArrayList<JSONObject>();
		ArrayList<JSONObject> emplys = new ArrayList();
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(location));
		
		JSONArray jsonArray = (JSONArray) jsonObject.get("employees");
		
		
		
		for (Object o: jsonArray)
		{
			emplys.add((JSONObject) o);
		}
		
		//System.out.println(emplys);
		
		return emplys;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		
		String[] targets = AllocateAllFiles("./jsons");
		ArrayList<JSONObject>[] empl_array = new ArrayList[targets.length];
		
		for(int i = 0; i < targets.length; i++)
		{
			empl_array[i] = LoadTheFile(targets[i]);
		}
		
		for(int i = 0; i < empl_array.length; i++)
		{
			for(JSONObject o : empl_array[i])
			{
				System.out.println(o.get("firstName"));
			}
		}
	}
}
