package com.traineeship.ticketsbooking.service;

import java.util.List;

import com.traineeship.ticketsbooking.bean.Booking;
import com.traineeship.ticketsbooking.bean.BookingList;
import com.traineeship.ticketsbooking.bean.Cinema;
import com.traineeship.ticketsbooking.bean.Dateclass;
import com.traineeship.ticketsbooking.bean.Film;
import com.traineeship.ticketsbooking.bean.Seance;
import com.traineeship.ticketsbooking.dao.BookingDao;
import com.traineeship.ticketsbooking.dao.CinemaDao;

public class BookingService {
	BookingDao bookingDao = new BookingDao();
	CinemaDao cinemaDao = new CinemaDao();
	BookingList bookingList = BookingList.getInstance();
	Cinema cinema = Cinema.getInstance();

	public void readBooking() {
		bookingDao.read();
	}

	public boolean book(Booking booking) {
		if (booking == null) {
			throw new RuntimeException("The booking does not exist");
		}
		if (bookPlaces(booking)) {
			bookingDao.write(bookingList);
			cinemaDao.write(cinema);
			return true;
		}
		return false;
	}

	public Booking getBookingById(String id) {
		readBooking();
		List<Booking> bookings = bookingList.getBookingList();
		for (Booking bookingInList : bookings) {
			if (id.equals(bookingInList.getId())) {
				return bookingInList;
			}
		}
		return null;
	}

	public boolean cancelBooking(String number) {
		if (cancel(number)) {
			bookingDao.write(bookingList);
			cinemaDao.write(cinema);
			return true;
		}
		return false;
	}

	private boolean cancel(String id) {
		List<Booking> bookings = bookingList.getBookingList();
		Booking booking = getBookingById(id);
		if (booking == null) {
			return false;
		}
		bookings.remove(booking);
		bookingList.setBookingList(bookings);

		String bookingDate = booking.getDate();
		String bookingFilm = booking.getFilm();
		String bookingSeance = booking.getSeance();
		List<String> bookingPlaces = booking.getPlaces();

		List<Dateclass> cinemaDates = cinema.getDates();
		for (Dateclass date : cinemaDates) {
			if (bookingDate.equals(date.getDate())) {
				List<Film> films = date.getFilms();
				for (Film film : films) {
					if (bookingFilm.equals(film.getName())) {
						List<Seance> seances = film.getSeances();
						for (Seance seance : seances) {
							if (bookingSeance.equals(seance.getSeanceTime())) {
								List<String> placesList = seance
										.getFreePlaces();
								for (String place : bookingPlaces) {
									placesList.add(place);
								}
								seance.setFreePlaces(placesList);
							}
						}
					}
				}
			}
		}
		return true;
	}

	private boolean bookPlaces(Booking booking) {
		List<Booking> bookings = bookingList.getBookingList();
		Integer id = Integer
				.parseInt(bookings.get(bookings.size() - 1).getId()) + 1;
		booking.setId(String.valueOf(id));
		bookings.add(booking);

		String bookingDate = booking.getDate();
		String bookingFilm = booking.getFilm();
		String bookingSeance = booking.getSeance();
		List<String> bookingPlaces = booking.getPlaces();

		List<Dateclass> cinemaDates = cinema.getDates();
		for (Dateclass date : cinemaDates) {
			if (bookingDate.equals(date.getDate())) {
				List<Film> films = date.getFilms();
				for (Film film : films) {
					if (bookingFilm.equals(film.getName())) {
						List<Seance> seances = film.getSeances();
						for (Seance seance : seances) {
							if (bookingSeance.equals(seance.getSeanceTime())) {
								List<String> placesList = seance
										.getFreePlaces();
								for (String place : bookingPlaces) {
									if (placesList.indexOf(place) == -1) {
										return false;
									}
									placesList.remove(place);
								}
								seance.setFreePlaces(placesList);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
}
