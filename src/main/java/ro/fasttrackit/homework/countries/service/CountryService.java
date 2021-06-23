package ro.fasttrackit.homework.countries.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.homework.countries.model.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

/*1)Read the countries from file. It has format:
	name|capital|population|area|continent|neighbour1~neighbour2,
also generate an ID for each country,
read it in CountryReader and populate the CountryService in its constructor (as in Course Code).

2) Build the country endpoints offering the following behavior:
	- list all countries: /countries -> returns a list of country objects
	- list all country names : /countries/names -> returns a list of strings
	- get capital of a country : /countries/<countryId>/capital -> returns a string
	- get population of a country : /countries/<countryId>/population -> returns a long
	- get countries in continent : /continents/<continentName>/countries -> returns list of Country objects
	- get country neighbours : /countries/<countryId>/neighbours -> returns list of Strings
	- get countries in <continent> with population larger than <population> : /continents/<continentName>/countries?minPopulation=<minimum population> -> returns list of Country objects
- get countries that neighbor X but not neighbor Y : /countries?includeNeighbour=<includedNeighbourCode>&excludeNeighbour=<excludedNeighbourCode> -> returns list of Country objects
	- get map from country to population : /countries/population -> returns map from String to Long
	- get map from continent to list of countries : /continents/countries  -> returns Map from String to List<Country>*/

@Service
public class CountryService {
	private final List<Country> countries = new ArrayList<>();

	public CountryService(CountryReader fileReader) throws Exception {
		countries.addAll(CountryReader.readFromFile());
	}

	public List<Country> allCountries() {
		return countries.stream()
				.filter(country -> country.getName() != null)
				.collect(toList());
	}

	public List<String> allCountryNames() {
		return countries.stream()
				.map(Country::getName).distinct()
				.collect(toList());
	}

	public Optional<String> getCapital(int countryId) {
		return countries.stream()
				.filter(country -> country.getId() == countryId)
				.map(Country::getCapital)
				.findAny();
	}

	public Optional<Long> getPopulation(int countryId) {
		return countries.stream()
				.filter(country -> country.getId() == countryId)
				.map(Country::getPopulation)
				.findAny();
	}

	public List<Country> getCountriesInContinent(String continentName) {
		return countries.stream()
				.filter(country -> country.getContinent().equalsIgnoreCase(continentName))
				.collect(toList());
	}

	public List<String> getCountryNeighbours(int countryId) {
		return countries.stream()
				.filter(country -> country.getId() == countryId)
				.map(Country::getNeighbours)
				.collect(toList());
	}

	public List<Country> getCountryBasedOnPopulation(String continentName, long minimumPopulation) {
		return countries.stream()
				.filter(country -> country.getContinent().equalsIgnoreCase(continentName))
				.filter(country -> country.getPopulation() >= minimumPopulation)
				.collect(toList());
	}

	public Map<String, Long> mapCountryToPopulation() {
		return countries.stream()
				.collect(toMap(Country::getName, Country::getPopulation));
	}

	public Map<String, List<Country>> mapContinentToCountries() {
		return countries.stream()
				.collect(groupingBy(Country::getContinent));
	}
}
