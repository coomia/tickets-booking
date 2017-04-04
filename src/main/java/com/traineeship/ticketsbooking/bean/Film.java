package com.traineeship.ticketsbooking.bean;

import java.util.ArrayList;
import java.util.List;

public class Film {
	private String name;
	private List<Seance> seances;

	public Film() {
		seances = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Seance> getSeances() {
		return seances;
	}

	public void setSeances(List<Seance> seances) {
		this.seances = seances;
	}

	@Override
	public String toString() {
		return "\n\t\tНазвание: " + name + "\n\t\t\tСеансы: " + seances;
	}
}
