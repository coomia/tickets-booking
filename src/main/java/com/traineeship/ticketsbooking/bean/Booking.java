package com.traineeship.ticketsbooking.bean;

import java.util.List;

public class Booking {
	private String id;
	private String name;
	private String date;
	private String film;
	private String seance;
	private List<String> places;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}

	public String getSeance() {
		return seance;
	}

	public void setSeance(String seance) {
		this.seance = seance;
	}

	public List<String> getPlaces() {
		return places;
	}

	public void setPlaces(List<String> places) {
		this.places = places;
	}

	@Override
	public String toString() {
		return "\n\tБронь: " + "\n\t\tНомер брони: " + id + "\n\t\tИмя: "
				+ name + "\n\t\tДата: " + date + "\n\t\tФильм: " + film
				+ "\n\t\tСеанс: " + seance + "\n\t\tМеста: " + places;
	}
}
