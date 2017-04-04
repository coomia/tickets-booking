package com.traineeship.ticketsbooking.command;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.traineeship.ticketsbooking.bean.Booking;
import com.traineeship.ticketsbooking.service.BookingService;

public class BookingCommand implements Command {
	private static final Logger LOGGER = LogManager
			.getLogger(BookingCommand.class.getName());
	BookingService bookingService = new BookingService();

	@Override
	public void execute() {
		try (Scanner scanner = new Scanner(System.in)) {

			LOGGER.info("Введите дату: ");
			String date = scanner.nextLine();
			LOGGER.info("Введите название фильма: ");
			String filmName = scanner.nextLine();
			LOGGER.info("Введите сеанс: ");
			String seance = scanner.nextLine();
			LOGGER.info("Введите места(через запятую): ");
			String places = scanner.nextLine();
			LOGGER.info("Введите свое имя: ");
			String name = scanner.nextLine();

			if (validateInput(date, filmName, seance, places, name)) {
				Booking booking = new Booking();
				booking.setDate(date.trim());
				booking.setFilm(filmName.trim());
				booking.setSeance(seance.trim());
				List<String> freePlaces = Arrays.asList(places.trim()
						.split(","));
				booking.setPlaces(freePlaces);
				booking.setName(name.trim());

				if (bookingService.book(booking)) {
					LOGGER.info("Места забронированы");
				} else {
					LOGGER.info("Места не доступны для бронирования");
				}
			} else {
				System.out.println("Неверный ввод. Попробуйте еще раз");
			}
		}
	}

	private boolean validateInput(String date, String film, String seance,
			String places, String name) {
		if (date.isEmpty() || film.isEmpty() || seance.isEmpty()
				|| places.isEmpty() || name.isEmpty()) {
			return false;
		}
		return true;
	}
}
