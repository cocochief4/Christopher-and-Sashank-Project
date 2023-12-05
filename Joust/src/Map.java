import java.io.BufferedReader;
import java.io.FileReader;

public class Map {
    
    private static final String MAP_ROOT = "maps/";

    private static String readFile(String filePath) {
		String data = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line = reader.readLine();
			while (line != null) {
				data += line + "\n";
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}
