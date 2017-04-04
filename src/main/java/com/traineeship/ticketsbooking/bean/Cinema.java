package com.traineeship.ticketsbooking.bean;

import java.util.List;

public class Cinema {
	private List<Dateclass> dates;
	private static Cinema cinema;

	private Cinema() {
	}

	public static Cinema getInstance() {
		if (cinema == null) {
			cinema = new Cinema();
		}
		return cinema;
	}

	public List<Dateclass> getDates() {
		return dates;
	}

	public void setDates(List<Dateclass> dates) {
		this.dates = dates;
	}

	@Override
	public String toString() {
		return "Даты:" + dates;
	}
}
