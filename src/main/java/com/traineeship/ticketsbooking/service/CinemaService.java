package com.traineeship.ticketsbooking.service;

import com.traineeship.ticketsbooking.dao.CinemaDao;

public class CinemaService {
	CinemaDao cinemaDao = new CinemaDao();

	public void readShedule() {
		cinemaDao.read();
	}
}
