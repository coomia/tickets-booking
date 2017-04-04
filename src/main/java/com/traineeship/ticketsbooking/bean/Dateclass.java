package com.traineeship.ticketsbooking.bean;

import java.util.ArrayList;
import java.util.List;

public class Dateclass {
	private String date;
	private List<Film> films;

	public Dateclass() {
		films = new ArrayList<>();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	@Override
	public String toString() {
		return "\n\tДата: " + date + "\n\t\tФильмы: " + films;
	}
}
