package com.traineeship.ticketsbooking.command;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.traineeship.ticketsbooking.service.BookingService;

public class CancelBookingCommand implements Command {
	private static final Logger LOGGER = LogManager
			.getLogger(CancelBookingCommand.class.getName());

	BookingService bookingService = new BookingService();

	@Override
	public void execute() {
		try (Scanner scanner = new Scanner(System.in)) {

			LOGGER.info("Введите номер брони: ");
			String bookingNumber = scanner.nextLine();

			if (!bookingNumber.isEmpty()) {
				if (bookingService.cancelBooking(bookingNumber)) {
					System.out.println("Бронь отменена.");
				} else {
					System.out.println("Проверьте правильность номера брони.");
				}
			} else {
				System.out.println("Неверный ввод. Попробуйте еще раз");
			}
		}
	}
}
