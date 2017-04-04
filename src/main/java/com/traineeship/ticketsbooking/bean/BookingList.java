package com.traineeship.ticketsbooking.bean;

import java.util.List;

public class BookingList {
	private List<Booking> bookingList;
	private static BookingList booking;

	private BookingList() {
	}

	public static BookingList getInstance() {
		if (booking == null) {
			booking = new BookingList();
		}
		return booking;
	}

	public List<Booking> getBookingList() {
		return bookingList;
	}

	public void setBookingList(List<Booking> bookingList) {
		this.bookingList = bookingList;
	}

	@Override
	public String toString() {
		return "Брони:" + bookingList;
	}
}
