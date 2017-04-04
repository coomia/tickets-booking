package com.traineeship.ticketsbooking.bean;

import java.util.List;

public class Seance {
	private String seanceTime;
	private List<String> freePlaces;

	public String getSeanceTime() {
		return seanceTime;
	}

	public void setSeanceTime(String seanceTime) {
		this.seanceTime = seanceTime;
	}

	public List<String> getFreePlaces() {
		return freePlaces;
	}

	public void setFreePlaces(List<String> freePlaces) {
		this.freePlaces = freePlaces;
	}

	@Override
	public String toString() {
		return "\n\t\t\t\tВремя: " + seanceTime + "\n\t\t\t\t\tМеста: "
				+ freePlaces;
	}
}
