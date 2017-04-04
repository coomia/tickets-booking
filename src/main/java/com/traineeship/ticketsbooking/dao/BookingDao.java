package com.traineeship.ticketsbooking.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.traineeship.ticketsbooking.bean.Booking;
import com.traineeship.ticketsbooking.bean.BookingList;

public class BookingDao {
	private static final String FILE_OUTPUT = "Booking.xml";
	private static final String FILE_INPUT = "Booking.xml";

	public void read() {
		DOMParser parser = new DOMParser();
		try {
			parser.parse(FILE_INPUT);
		} catch (SAXException | IOException e) {
			throw new RuntimeException("Error occurred while reding the file",
					e);
		}
		Document document = parser.getDocument();

		Element root = document.getDocumentElement();

		BookingList bookingList = BookingList.getInstance();

		NodeList booking = root.getElementsByTagName("Бронь");
		Booking book = null;
		List<Booking> booksList = new ArrayList<>();
		for (int i = 0; i < booking.getLength(); i++) {
			Element bookingElement = (Element) booking.item(i);
			book = new Booking();
			book.setId(bookingElement.getAttribute("id"));
			book.setName(getSingleChild(bookingElement, "Имя").getTextContent()
					.trim());
			book.setDate(getSingleChild(bookingElement, "Дата")
					.getTextContent().trim());
			book.setFilm(getSingleChild(bookingElement, "Фильм")
					.getTextContent().trim());
			book.setSeance(getSingleChild(bookingElement, "Сеанс")
					.getTextContent().trim());
			String placesStr = getSingleChild(bookingElement, "Места")
					.getTextContent().trim();

			book.setPlaces(Arrays.asList(placesStr.split(",")));
			booksList.add(book);
		}
		bookingList.setBookingList(booksList);
	}

	private static Element getSingleChild(Element element, String childName) {
		NodeList nlist = element.getElementsByTagName(childName);
		Element child = (Element) nlist.item(0);
		return child;
	}

	public void write(BookingList bookingList) {

		File outputFile = new File(FILE_OUTPUT);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(
				outputFile))) {
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n");
			writer.write("<Брони>" + "\n");

			List<Booking> bookings = bookingList.getBookingList();
			for (Booking booking : bookings) {
				writer.write("\t<Бронь id=\"" + booking.getId() + "\">" + "\n");
				writer.write("\t\t<Имя>" + booking.getName() + "</Имя>" + "\n");
				writer.write("\t\t<Дата>" + booking.getDate() + "</Дата>"
						+ "\n");
				writer.write("\t\t<Фильм>" + booking.getFilm() + "</Фильм>"
						+ "\n");
				writer.write("\t\t<Сеанс>" + booking.getSeance() + "</Сеанс>"
						+ "\n");
				List<String> places = booking.getPlaces();
				String placesLine = "";
				for (String place : places) {
					placesLine = placesLine + place + ",";
				}
				writer.write("\t\t<Места>"
						+ placesLine.substring(0, placesLine.length() - 1)
						+ "</Места>" + "\n");
				writer.write("\t</Бронь>" + "\n");
			}
			writer.write("</Брони>" + "\n");
		} catch (IOException e) {
			throw new RuntimeException(
					"Error occurred while saving the DamageData", e);
		}
	}
}
