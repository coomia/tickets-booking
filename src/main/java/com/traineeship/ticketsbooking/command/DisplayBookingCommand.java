package com.traineeship.ticketsbooking.command;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.traineeship.ticketsbooking.bean.Booking;
import com.traineeship.ticketsbooking.service.BookingService;

public class DisplayBookingCommand implements Command {
	private static final Logger LOGGER = LogManager
			.getLogger(DisplayBookingCommand.class.getName());
	BookingService bookingService = new BookingService();

	@Override
	public void execute() {

		try (Scanner scanner = new Scanner(System.in)) {

			LOGGER.info("Введите номер брони: ");
			String bookingNumber = scanner.nextLine();

			if (!bookingNumber.isEmpty()) {
				Booking booking = bookingService.getBookingById(bookingNumber);
				LOGGER.info(booking);
			} else {
				LOGGER.info("Неверный ввод. Попробуйте еще раз");
			}
		}
	}
}
