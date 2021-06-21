package ro.fasttrackit.homework.countries.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.homework.countries.model.Country;
import ro.fasttrackit.homework.countries.service.CountryService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping()
public class CountryController {
	private final CountryService countryService;

	public CountryController(CountryService countryService) {
		this.countryService = countryService;
	}

	@GetMapping("/countries")
	List<Country> allCountries() {
		return countryService.allCountries();
	}

	@GetMapping("/countries/names")
	List<String> allCountryNames() {
		return countryService.allCountryNames();
	}

	@GetMapping("/countries/{countryId}/capital")
	Optional<String> getCapital(@PathVariable int countryId) {
		return countryService.getCapital(countryId);
	}

	@GetMapping("/countries/{countryId}/population")
	Optional<Long> getPopulation(@PathVariable int countryId) {
		return countryService.getPopulation(countryId);
	}

	@GetMapping("/continents/{continentName}/countries")
	List<Country> getCountriesInContinent(@PathVariable String continentName) {
		return countryService.getCountriesInContinent(continentName);

	}

	@GetMapping("/countries/{countryId}/neighbours")
	List<String> getCountryNeighbours(@PathVariable int countryId) {
		return countryService.getCountryNeighbours(countryId);
	}

	@GetMapping("/continents/{continentName}/countries?minPopulation={minimumPopulation}")
	List<Country> getCountryBasedOnPopulation(@PathVariable String continentName, @PathVariable long minimumPopulation) {
		return countryService.getCountryBasedOnPopulation(continentName, minimumPopulation);
	}

	@GetMapping("/countries/population")
	Map<String, Long> mapCountryToPopulation() {
		return countryService.mapCountryToPopulation();
	}

	@GetMapping("/countries/countries")
	Map<String, List<Country>> mapContinentToCountries() {
		return countryService.mapContinentToCountries();
	}
}
