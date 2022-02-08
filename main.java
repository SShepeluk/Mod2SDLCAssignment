//Sharlton Shepeluk

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;

public class main {

	public static void main(String[] args) {

		Path path = Paths.get("C:\\Users\\sharl\\Desktop\\2022HW\\SoftwareDev\\Mod2SDLC\\poem.txt");

		try {

			String poem = Files.readString(path);

			poem = poem.toLowerCase();
			Pattern p = Pattern.compile("[a-z]+"); // creates pattern that ignores symbols
			Matcher m = p.matcher(poem);

			TreeMap<String, Integer> freq = new TreeMap<>();

			while (m.find()) {
				String word = m.group();

				if (freq.containsKey(word)) {
					freq.computeIfPresent(word, (w, c) -> Integer.valueOf(c.intValue() + 1));
				} else {
					freq.computeIfAbsent(word, (w) -> Integer.valueOf(1));
				}
			}

			// Sorts treemap into descending order with use of a linked hashmap
			LinkedHashMap<String, Integer> sortedFreq = new LinkedHashMap<>();
			freq.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
					.forEachOrdered(x -> sortedFreq.put(x.getKey(), x.getValue()));

			// Outputs treemap
			for (String word : sortedFreq.keySet()) {
				String key = word.toString();
				String value = sortedFreq.get(word).toString();
				System.out.println(key + ", " + value);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
