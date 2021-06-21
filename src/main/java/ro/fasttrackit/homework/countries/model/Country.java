package ro.fasttrackit.homework.countries.model;

import java.util.Objects;

public class Country {
	private final int id;
	private final String name;
	private final String capital;
	private final long population;
	private final int area;
	private final String continent;
	private final String neighbours;

	public Country(int id, String name, String capital, long population, int area, String continent, String neighbours) {
		this.id = id;
		this.name = name;
		this.capital = capital;
		this.population = population;
		this.area = area;
		this.continent = continent;
		this.neighbours = neighbours;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCapital() {
		return capital;
	}

	public long getPopulation() {
		return population;
	}

	public int getArea() {
		return area;
	}

	public String getContinent() {
		return continent;
	}

	public String getNeighbours() {
		return neighbours;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Country country = (Country) o;
		return id == country.id && population == country.population && area == country.area && Objects.equals(name, country.name) && Objects.equals(capital, country.capital) && Objects.equals(continent, country.continent) && Objects.equals(neighbours, country.neighbours);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, capital, population, area, continent, neighbours);
	}

	@Override
	public String toString() {
		return "Country{" +
				"id=" + id +
				", name='" + name + '\'' +
				", capital='" + capital + '\'' +
				", population=" + population +
				", area=" + area +
				", continent='" + continent + '\'' +
				", neighbours='" + neighbours + '\'' +
				'}';
	}
}
