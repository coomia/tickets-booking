package com.traineeship.ticketsbooking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.traineeship.ticketsbooking.bean.Cinema;
import com.traineeship.ticketsbooking.service.CinemaService;

public class DisplayScheduleCommand implements Command {
	private static final Logger LOGGER = LogManager
			.getLogger(DisplayScheduleCommand.class.getName());

	CinemaService cinemaService = new CinemaService();

	@Override
	public void execute() {
		cinemaService.readShedule();
		LOGGER.info(Cinema.getInstance());
	}
}
