package com.traineeship.ticketsbooking.command;

import com.traineeship.ticketsbooking.service.CinemaService;

public class ReadSheduleCommand implements Command {
	CinemaService cinemaService = new CinemaService();

	@Override
	public void execute() {
		cinemaService.readShedule();
	}
}
