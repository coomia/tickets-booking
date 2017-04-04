package com.traineeship.ticketsbooking.exe;

import java.util.Scanner;

import com.traineeship.ticketsbooking.bean.BookingList;
import com.traineeship.ticketsbooking.bean.Cinema;
import com.traineeship.ticketsbooking.command.Command;
import com.traineeship.ticketsbooking.command.CommandProvider;
import com.traineeship.ticketsbooking.command.ReadBookingCommand;
import com.traineeship.ticketsbooking.command.ReadSheduleCommand;

public class Executer {
	public static void main(String[] args) {

		BookingList.getInstance();
		Cinema.getInstance();
		ReadSheduleCommand readShedule = new ReadSheduleCommand();
		ReadBookingCommand readBooking = new ReadBookingCommand();
		readShedule.execute();
		readBooking.execute();

		try (Scanner scanner = new Scanner(System.in)) {
			CommandProvider provider = new CommandProvider();
			while (true) {

				System.out.println("Выберите действие: ");
				System.out.print("1. Просмотреть расписание." + "\n"
						+ "2. Забронировать места." + "\n"
						+ "3. Отменить бронь." + "\n"
						+ "4. Получить информацию о брони." + "\n");

				String menuItem = scanner.nextLine();

				Command command = provider.getCommand(menuItem);
				if (command != null) {
					command.execute();
				} else {
					System.out.println("Неверный ввод. Попробуйте еще раз");
				}
			}
		}
	}
}
