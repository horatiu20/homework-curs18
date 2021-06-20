package ro.fasttrackit.homework.countries;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryReader {
	public static void main(String[] args) throws Exception {
		List<Country> countries = readFromFile();
	}

	public static List<Country> readFromFile() throws Exception {
		List<Country> countries = new ArrayList<>();
		BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/countries.txt"));
		String line;
		int id = 1;
		while ((line = fileReader.readLine()) != null) {
			countries.add(countryInfo(id++, line));
		}
		return countries;
	}

	private static Country countryInfo(int id, String countryInfo) {
		String[] countryData = countryInfo.split("\\|");
		String name = countryData[0];
		String capital = countryData[1];
		int population = Integer.parseInt(countryData[2]);
		int area = Integer.parseInt(countryData[3]);
		String continent = countryData[4];
		String neighbours = countryData[5];

		return new Country(id, name, capital, population, area, continent, neighbours);
	}
}
