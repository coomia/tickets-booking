package com.traineeship.ticketsbooking.command;

import com.traineeship.ticketsbooking.service.BookingService;

public class ReadBookingCommand implements Command {

	BookingService bookingService = new BookingService();

	@Override
	public void execute() {
		bookingService.readBooking();
	}
}
