package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.charset.Charset.forName;

public class CSVUtility {

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(new FileReader(new File("Vehicles.csv")));
			BufferedWriter writer = Files.newBufferedWriter(Paths.get("VehicleOptions.csv"), forName("US-ASCII"));

			while (scanner.hasNextLine()) {
				String[] split = scanner.nextLine().split(",");
				if (split.length > 1 && split[0].length() > 0) {
					for (int i = 1; i <= 84; i++) {
						if (Math.random() > .75) {
							String line = split[0] + "," + i + "\n";
							writer.write(line, 0, line.length());
						}
					}
				}
			}

			writer.flush();
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
