package com.traineeship.ticketsbooking.command;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
	private Map<String, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put("1", new DisplayScheduleCommand());
		commands.put("2", new BookingCommand());
		commands.put("3", new CancelBookingCommand());
		commands.put("4", new DisplayBookingCommand());
	}

	public Command getCommand(String menuName) {
		return commands.get(menuName);
	}
}
