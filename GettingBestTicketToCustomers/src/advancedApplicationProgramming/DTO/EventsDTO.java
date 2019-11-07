/**
 *
 * @author Arnav Kaforma
 * @version 23-03-2019
 */

package advancedApplicationProgramming.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class contains all the fields to be used for getting Events and all its properties 
 *
 **/
public class EventsDTO {

	private int id;
	private LocationDTO location;
	private int tickets;
	private List<Integer> price = new ArrayList<>();
	private Integer cheapestPriceInPriceList;
	private int distance;
	private static List<EventsDTO> eventsList = new ArrayList<>();

	private EventsDTO(int id, LocationDTO location, List<Integer> price, int cheapestTicket, int distance) {
		super();
		this.id = id;
		this.location = location;
		this.price = price;
		this.cheapestPriceInPriceList = cheapestTicket;
		this.distance = distance;
	}

	public EventsDTO() {
	}

	/**
	 * ????????
	 */

	public EventsDTO(int id, LocationDTO location, List<Integer> price) {
		this.id = id;
		this.location = location;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static List<EventsDTO> getEventsList() {
		return eventsList;
	}

	public static void setEventsList(EventsDTO eventsList) {
		EventsDTO.eventsList.add(eventsList);
	}

	public static void setFullEventsList(List<EventsDTO> eventsList) {
		EventsDTO.eventsList = eventsList;
	}

	public LocationDTO getLocation() {
		return location;
	}

	public void setLocation(LocationDTO location) {
		this.location = location;
	}

	public int getTickets() {
		return tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

	public List<Integer> getPrice() {
		return price;
	}

	public String printPrice() {
		return getPrice().stream().map(i -> i.toString()).collect(Collectors.joining(","));
	}

	public void setPrice(Integer i) {
		this.price.add(i);
	}
	public void setPrice(List<Integer> i) {
		this.price = i;
	}


	public static void setEventsList(List<EventsDTO> eventsList) {
		EventsDTO.eventsList = eventsList;
	}

	public static EventsDTO addEvent(int Id, LocationDTO location, List <Integer> priceList, Integer price, int distance) {
		EventsDTO evenDto = new EventsDTO(Id, location,priceList, price, distance);
		return evenDto;
	}

	public static EventsDTO addEvent(int Id, LocationDTO location, List<Integer> price) {
		EventsDTO evenDto = new EventsDTO(Id, location, price);
		return evenDto;
	}

	@Override
	public String toString() {
		return "EventsDTO [id=" + id + ", location=" + location + " price=" + price + ", cheapestPriceInPriceList="
				+ cheapestPriceInPriceList + ", distance=" + distance + "]";
	}

	/**
	 * @return the cheapestPriceInPriceList
	 */
	public Integer getCheapestPriceInPriceList() {
		return cheapestPriceInPriceList;
	}

	/**
	 * @param cheapestPriceInPriceList
	 *            the cheapestPriceInPriceList to set
	 */
	public void setCheapestPriceInPriceList(Integer cheapestPriceInPriceList) {
		this.cheapestPriceInPriceList = cheapestPriceInPriceList;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	public static void removeBookedEvent(EventsDTO event) {
		int index = 0;
		for (EventsDTO e : getEventsList()) {
			index++;
			if (e.getId() == event.getId())
				break;
		}
		getEventsList().remove(index);
	}

}
