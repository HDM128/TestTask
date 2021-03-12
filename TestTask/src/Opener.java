import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Opener {
	
	public static String[][] AllocateAllFiles(String location) throws FileNotFoundException, IOException, ParseException
	{
	
		int i = 0;
		File dir1 = new File(location);
		String[][] paths = new String[dir1.listFiles().length][2];
		
	
		if(dir1.isDirectory())
		{
			for(File item : dir1.listFiles())
			{
				if(item.isDirectory() == false)
				{
					paths[i][0] = item.getAbsolutePath();
					paths[i][1] = GetCompanyName(item.getAbsolutePath());
					System.out.println("Company: " + paths[i][1] + " File: " + item.getName() +" Location: "+ item.getAbsolutePath());
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
	
	public static String GetCompanyName(String path) throws FileNotFoundException, IOException, ParseException
	{
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(path));
		
		String companyName = jsonObject.get("Name").toString();
		
		return companyName;
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
	
	public static JSONObject getEmployee(ArrayList<JSONObject>[] array, Integer CompanyId, String FirstName)
	{
		JSONObject employee = null;
		
		for(JSONObject o : array[CompanyId])
		{
			if(o.get("firstName").equals(FirstName))
			{
				employee = o;
			}
		}
		
		return employee;
	}
	
	
	//Перевод сотрудника из одной компании в другую
	public static void moveEmployee(String[][] targets, ArrayList<JSONObject>[] empl_array, String CompanyNameFrom, String CompanyNameTo, String FirstName)
	{
		Integer CompanyIdFrom = getCompanyId(targets, CompanyNameFrom);
		Integer CompanyIdTo = getCompanyId(targets, CompanyNameTo);
		
		JSONObject employeeToMove = getEmployee(empl_array, CompanyIdFrom, FirstName);
		
		/*for(JSONObject o : empl_array[CompanyIdFrom])
		{
			if(o.get("firstName").equals(FirstName))
			{
				empl_array[CompanyIdFrom].remove(o);
			}
		}*/
		
		empl_array[CompanyIdFrom].remove(employeeToMove);
		empl_array[CompanyIdTo].add(employeeToMove);		
	}
	
	public static int getCompanyId(String[][] array, String Name)
	{
		Integer CompanyId = null;
		
		for(int i = 0; i < array.length; i++)
		{
			if(array[i][1].equals(Name))
			{
				CompanyId = i;
			}
		}
		
		return CompanyId;
	}
	
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		
		String[][] targets = AllocateAllFiles("./jsons"); //Размерность массива ожидается [N][2], где N - количество найденных файлов (списков сотрудников)
														  //Столбцов всегда будет 2. Столбец 0 - путь к файлу, а 1 - название Компании
		ArrayList<JSONObject>[] empl_array = new ArrayList[targets.length];
		
		for(int i = 0; i < targets.length; i++)
		{
			empl_array[i] = LoadTheFile(targets[i][0]);
		}
		
		moveEmployee(targets, empl_array, "NaughtyDog", "Head&shoulders", "Neil");
		
		System.out.println(empl_array[0]);
		System.out.println(empl_array[1]);
	}
}
