import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Map {
    
    private static final String MAP_ROOT = "maps/";

	private static String[] map;

	/**
	 * Splits the world into a bunch of rectangles and basically fills in the rectangles tile style based off of csv.
	 * @return
	 */
	public static ArrayList<Block> init() {
		String[] mapStrArr = readFile(MAP_ROOT + "map0.csv").split("\n");

		int height = mapStrArr.length;

		int width = mapStrArr[height-1].split(",").length;

		System.out.println(width + ", " + height);

		float unitHeight = (float) (Joust.DEFAULT_HEIGHT)/height;
		float unitWidth = (float) (Joust.DEFAULT_WIDTH)/width;

		float[] unitArr = {unitWidth, unitHeight};

		Block.SIZE = unitArr; // Set the size of each block

		Block[][] map = new Block[height][width];
		ArrayList<Block> blockList = new ArrayList<Block>();

		for (int h = 0; h < height; h++) {
			String[] line = mapStrArr[h].split(",");
			for (int w = 0; w < width; w++) {
				if (line[w].equals("g")) {
					Block b = new Block(Joust.grassBlock, w*unitWidth + unitWidth/2, h*unitHeight + unitHeight/2);
					map[h][w] = b;
					blockList.add(b);
				}
			}
		}

		return blockList;

	}

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
