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

import com.traineeship.ticketsbooking.bean.Cinema;
import com.traineeship.ticketsbooking.bean.Dateclass;
import com.traineeship.ticketsbooking.bean.Film;
import com.traineeship.ticketsbooking.bean.Seance;

public class CinemaDao {
	private static final String FILE_OUTPUT = "Schedule.xml";
	private static final String FILE_INPUT = "Schedule.xml";

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

		Cinema cinema = Cinema.getInstance();

		NodeList dataList = root.getElementsByTagName("Дата");

		Dateclass date = null;
		List<Dateclass> dateList = new ArrayList<>();

		for (int i = 0; i < dataList.getLength(); i++) {
			date = new Dateclass();
			Element dateElement = (Element) dataList.item(i);
			date.setDate(dateElement.getAttribute("дата"));

			NodeList films = dateElement.getElementsByTagName("Фильм");
			Film film = null;

			for (int j = 0; j < films.getLength(); j++) {
				film = new Film();
				Element filmElement = (Element) films.item(j);
				film.setName(filmElement.getAttribute("название"));

				NodeList seances = filmElement.getElementsByTagName("Сеанс");
				Seance seance = null;
				for (int k = 0; k < seances.getLength(); k++) {
					seance = new Seance();
					Element seanceElement = (Element) seances.item(k);
					seance.setSeanceTime(seanceElement.getAttribute("время"));

					String placesStr = getSingleChild(seanceElement, "Места")
							.getTextContent().trim();
					List<String> places = new ArrayList<>(
							Arrays.asList(placesStr.split(",")));
					seance.setFreePlaces(places);
					film.getSeances().add(seance);
				}
				date.getFilms().add(film);
			}
			dateList.add(date);
		}
		cinema.setDates(dateList);
	}

	private static Element getSingleChild(Element element, String childName) {
		NodeList nlist = element.getElementsByTagName(childName);
		Element child = (Element) nlist.item(0);
		return child;
	}

	public void write(Cinema сinema) {

		File outputFile = new File(FILE_OUTPUT);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(
				outputFile))) {
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n");
			writer.write("<Кино>" + "\n");

			List<Dateclass> dates = сinema.getDates();
			for (Dateclass date : dates) {
				writer.write("\t<Дата дата=\"" + date.getDate() + "\">" + "\n");
				List<Film> films = date.getFilms();
				for (Film film : films) {
					writer.write("\t\t<Фильм название=\"" + film.getName()
							+ "\">" + "\n");
					List<Seance> seances = film.getSeances();
					for (Seance seance : seances) {
						writer.write("\t\t\t<Сеанс время=\""
								+ seance.getSeanceTime() + "\">" + "\n");
						List<String> places = seance.getFreePlaces();
						String placesLine = "";
						for (String place : places) {
							placesLine = placesLine + place + ",";
						}
						writer.write("\t\t\t\t<Места>"
								+ placesLine.substring(0,
										placesLine.length() - 1) + "</Места>"
								+ "\n");
						writer.write("\t\t\t</Сеанс>" + "\n");
					}
					writer.write("\t\t</Фильм>" + "\n");
				}
				writer.write("\t</Дата>" + "\n");
			}
			writer.write("</Кино>" + "\n");
		} catch (IOException e) {
			throw new RuntimeException(
					"Error occurred while saving the DamageData", e);
		}
	}
}
