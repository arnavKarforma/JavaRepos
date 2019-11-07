package advancedApplicationProgramming.BusinessLogicImpl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import advancedApplicationProgramming.Constraints;
import advancedApplicationProgramming.BusinessLogic.Service;
import advancedApplicationProgramming.DTO.EventsDTO;
import advancedApplicationProgramming.DTO.LocationDTO;

/**
 * This is the service class where all the business logic is executed and the
 * dummy data is generated in case input file is empty or not present and
 * persisted in file
 *
 **/
public class ServiceImpl implements Service {
	public ServiceImpl() {
		this.checkForInput();
		testScope = false;
	}

	public ServiceImpl(String test) {
		this.testScope = true;
	}

	public ArrayList<EventsDTO> listGoingThroughAllChecks = new ArrayList<>();
	private Random seedDataGenerator = new Random();
	private List<String> locationString = new ArrayList<>();
	private Boolean testScope = false;

	/**
	 * This method generates a random number based on range
	 *
	 * @param nothing
	 * @return nothing
	 */
	@Override
	public int generateRandomNumbers(int range) {
		return seedDataGenerator.nextInt(range) + 1;
	}

	/**
	 * This method generates a random number based on minimum and upper limit
	 *
	 * @param nothing
	 * @return nothing
	 */
	@Override
	public int generateRandomNumbers(int maxRange, int minRange) {
		return seedDataGenerator.nextInt(maxRange + 1 - minRange) + minRange;
	}

	/**
	 * This method on constructor call checks if the Input file is present and
	 * has data if not it calls the generate data to generate seed data
	 * 
	 * @param nothing
	 * @return nothing
	 */
	@Override
	public void checkForInput() {
		File f = new File(new File("").getAbsolutePath() + Constraints.FILE_PATH);
		System.out.println("File exists" + f.exists());
		System.out.println("File exists" + !f.isDirectory());
		if (f.exists() && !f.isDirectory()) {
			if (f.length() != 0) {
				this.readFromFile(Constraints.FILE_PATH);
				System.out.println("got inside to read");
				return;
			}
		}
		this.generateData();

	}

	/**
	 * This method writes the data to the Input file if the file is empty or not
	 * present
	 *
	 * @param nothing
	 * @return nothing
	 */
	@Override
	public void writeInFile(String filePath) {
		try (FileWriter fw = new FileWriter(new File("").getAbsolutePath() + filePath)) {
			EventsDTO.getEventsList().forEach(p -> {
				try {
					String line = p.getId() + "||" + p.getLocation() + "||" + p.printPrice() + System.lineSeparator();
					fw.write(line);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * This method writes the data to the Input file if the file is empty or not
	 * present
	 *
	 * @param nothing
	 * @return nothing
	 */
	@Override
	public void readFromFile(String filePath) {
		try (FileReader fr = new FileReader(new File("").getAbsolutePath() + filePath)) {
			int index;
			String line = "";
			String eventsParam[];
			List<EventsDTO> eventsList = new ArrayList<>();
			while ((index = fr.read()) != -1) {
				boolean priceISEmpty = false;
				char character = (char) index;
				line += character;
				if (character == '\n') {
					eventsParam = line.split("\\|\\|");
					String location[] = eventsParam[1].trim().split(",");
					LocationDTO locDTO = new LocationDTO();
					locDTO.setX_axis(Integer.parseInt(location[0]));
 					locDTO.setY_axis(Integer.parseInt(location[1]));
					String prices[] = eventsParam[2].trim().split(",");
					List<Integer> priceList = new ArrayList<>();
					for (int i = 0; i < prices.length; i++) {
						int price = 0;
						try {
							price = Integer.parseInt(prices[i]);
						} catch (Exception e) {
							priceISEmpty = true;
							break;
						}
						priceList.add(price);
					}
					if (priceISEmpty == false) {
						EventsDTO events = EventsDTO.addEvent(Integer.parseInt(eventsParam[0].trim()), locDTO,
								priceList);
						eventsList.add(events);
					}
				line = "";
				}
			}
			/*
			 * int i = 0; for(EventsDTO e :eventsList){
			 * if(e.getPrice().isEmpty()){ break; } i++; } eventsList.remove(i);
			 */
			EventsDTO.setEventsList(eventsList);
			System.out.println(
					"--------------------All the events pulled from existing file------------------------------");
			EventsDTO.getEventsList().forEach((event) -> System.out.println(event));
			System.out.println(
					"--------------------All the events pulled from existing file------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method generates seed data in case the input file is not present
	 *
	 * @param nothing
	 * @return nothing
	 */
	@Override
	public void generateData() {
		EventsDTO eventsDTO;
		LocationDTO locationDTO;
		int idGenerator = 1;
		while (EventsDTO.getEventsList().size() < Constraints.TOTAL_EVENTS) {
			locationDTO = new LocationDTO();
			locationDTO.setX_axis(generateRandomNumbers(Constraints.MAX_POSITIVE_AXIS, Constraints.MAX_NEGATIVE_AXIS));
			locationDTO.setY_axis(generateRandomNumbers(Constraints.MAX_POSITIVE_AXIS, Constraints.MAX_NEGATIVE_AXIS));
			locationString.add(locationDTO.toString());
			eventsDTO = new EventsDTO();
			eventsDTO.setLocation(locationDTO);
			eventsDTO.setTickets(
					generateRandomNumbers(Constraints.MAX_TICKETS_IN_ONE_EVENT, Constraints.MIN_TICKETS_IN_ONE_EVENT));
			for (int j = 0; j < eventsDTO.getTickets(); j++) {
				eventsDTO.setPrice(Integer
						.valueOf(generateRandomNumbers(Constraints.MAX_TICKET_PRICE, Constraints.MIN_TICKET_PRICE)));
			}
			eventsDTO.setId(idGenerator++);
			EventsDTO.setEventsList(eventsDTO);
		}
		System.out.println("--------------------All the events generated------------------------------");
		EventsDTO.getEventsList().forEach((event) -> System.out.println(event));
		System.out.println("--------------------All the events generated------------------------------");
		if (testScope == true) {
			this.writeInFile(Constraints.TEST_FILE_PATH);
		} else
			this.writeInFile(Constraints.FILE_PATH);

	}

	/**
	 * This method takes the user location and applies all the business logic to
	 * get the best event
	 *
	 * @param LocationDTO(user
	 *            location)
	 * @return EventDTO(final event)
	 */
	@Override
	public EventsDTO findClosesEvents(LocationDTO searchLocation) {
		System.out.println("Entered findClosestEvent" + searchLocation);
		EventsDTO finalResult = new EventsDTO();
		populatedistance(searchLocation);
		sortwholelistAsPerDistance();
		keepEventsWithMinDistance();
		sortAsPerPrice();
		keepEventsWithMinPrice();
		sortAsPerId(listGoingThroughAllChecks, "checked list");
		removeBookedPrice(listGoingThroughAllChecks.get(0).getCheapestPriceInPriceList());
		finalResult = listGoingThroughAllChecks.get(0);
		int eventIdSelected = listGoingThroughAllChecks.get(0).getId();
		System.out.println("----------------After removing the price list---------------------");
		System.out.println(EventsDTO.getEventsList().stream().filter(i -> i.getId() == eventIdSelected).findFirst());
		System.out.println("----------------After removing the price list---------------------");
		sortAsPerId(EventsDTO.getEventsList(), "full");
		if (testScope == true) {
			this.writeInFile(Constraints.TEST_FILE_PATH);
		} else if (testScope == false) {
			System.out.println("-------------------------wrote updated event list in the file-----------------------");
			this.writeInFile(Constraints.FILE_PATH);
		}
		return finalResult;
	}

	/**
	 * To remove the booked ticket price from the price list of that event
	 *
	 * @param nothing
	 * @return nothing
	 */
	private void removeBookedPrice(int cheapestPrice) {
		int indexOfId = -1;
		for (EventsDTO e : EventsDTO.getEventsList()) {
			indexOfId++;
			if (e.getId() == listGoingThroughAllChecks.get(0).getId()) {
				break;
			}
		}

		int indexOfPrice = EventsDTO.getEventsList().get(indexOfId).getPrice().indexOf(cheapestPrice);
		System.out.println("index" + indexOfPrice);
		List<Integer> removedBookedPrice = new ArrayList<Integer>(EventsDTO.getEventsList().get(indexOfId).getPrice());
		removedBookedPrice.remove(indexOfPrice);
		EventsDTO.getEventsList().get(indexOfId).setPrice(removedBookedPrice);
	}

	/**
	 * This method takes the user location and based on the saved events
	 * location calculates the distance in between them
	 *
	 * @param x,
	 *            x1,y,y1(users's coordinates in x axis,event's coordinates in x
	 *            axis,users's coordinates in y axis,event's coordinates in y
	 *            axis )
	 * @return nothing
	 */
	@Override
	public Integer calculateDistance(int x, int x1, int y, int y1) {
		return Math.abs(x1 - x) + Math.abs(y1 - y);
	}

	/**
	 * This method is private as its part of implementation and not part of
	 * interface, its main purpose is top populate the distance from the user of
	 * each event
	 *
	 * @param LocationDTO(user
	 *            location)
	 * @return nothing
	 */
	private void populatedistance(LocationDTO searchLocation) {
		EventsDTO.getEventsList()
				.forEach((event) -> event.setDistance(calculateDistance(event.getLocation().getX_axis(),
						searchLocation.getX_axis(), event.getLocation().getY_axis(), searchLocation.getY_axis())));
		System.out.println("Event direction has been set");

	}

	/**
	 * This method is private as its part of implementation and not part of
	 * interface, its purpose is to sort all the events as per distance
	 *
	 *
	 * @param nothing
	 * @return nothing
	 */
	private void sortwholelistAsPerDistance() {
		Collections.sort(EventsDTO.getEventsList(), new Comparator<EventsDTO>() {

			@Override
			public int compare(EventsDTO o1, EventsDTO o2) {
				int dis1 = o1.getDistance();
				int dis2 = o2.getDistance();
				if (dis1 == dis2)
					return 0;
				else if (dis1 > dis2)
					return 1;
				else
					return -1;
			}
		});
		System.out.println("------------------Sorted as per distance-----------------------------");
		EventsDTO.getEventsList().forEach((event) -> System.out.println(event));
		System.out.println("------------------Sorted as per distance-----------------------------");

	}

	/**
	 * This method is private as its part of implementation and not part of
	 * interface, its purpose is to remove all events that are not the closest
	 * one to user
	 * 
	 * 
	 * @param nothing
	 * @return nothing
	 */
	private void keepEventsWithMinDistance() {
		int checkedEvents = 1;
		EventsDTO temp = EventsDTO.addEvent(EventsDTO.getEventsList().get(0).getId(),
				EventsDTO.getEventsList().get(0).getLocation(), EventsDTO.getEventsList().get(0).getPrice(),
				Collections.min(EventsDTO.getEventsList().get(0).getPrice()),
				EventsDTO.getEventsList().get(0).getDistance());
		listGoingThroughAllChecks.add(temp);
		while (checkedEvents < EventsDTO.getEventsList().size()) {
			if (listGoingThroughAllChecks.get(0).getDistance() == EventsDTO.getEventsList().get(checkedEvents)
					.getDistance()) {
				temp = EventsDTO.addEvent(EventsDTO.getEventsList().get(checkedEvents).getId(),
						EventsDTO.getEventsList().get(checkedEvents).getLocation(),
						EventsDTO.getEventsList().get(checkedEvents).getPrice(),
						Collections.min(EventsDTO.getEventsList().get(checkedEvents).getPrice()),
						EventsDTO.getEventsList().get(checkedEvents).getDistance());
				listGoingThroughAllChecks.add(temp);
				checkedEvents++;
			} else {
				break;
			}
		}

	}

	/**
	 * This method is private as its part of implementation and not part of
	 * interface, its purpose is to sort all the events as per cheapest price
	 * who are equally close to the customer
	 *
	 *
	 * @param nothing
	 * @return nothing
	 */
	private void sortAsPerPrice() {
		listGoingThroughAllChecks.sort(new Comparator<EventsDTO>() {
			@Override
			public int compare(EventsDTO o1, EventsDTO o2) {
				int id1 = o1.getCheapestPriceInPriceList();
				int id2 = o2.getCheapestPriceInPriceList();
				if (id1 == id2)
					return 0;
				else if (id1 > id2)
					return 1;
				else
					return -1;
			}
		});

		System.out.println("------------------Sorted as per price if distance is same-----------------------------");
		listGoingThroughAllChecks.forEach((event) -> System.out.println(event));
		System.out.println("------------------Sorted as per price if distance is same-----------------------------");

	}

	/**
	 * This method is private as its part of implementation and not part of
	 * interface, its purpose is to remove all events that are closest one to
	 * user but not the cheapest
	 * 
	 * 
	 * @param nothing
	 * @return nothing
	 */
	private void keepEventsWithMinPrice() {
		int secondCheckedEvents = 1;
		int removeFrom = 1;
		int previousSizeofResult = listGoingThroughAllChecks.size();
		while (secondCheckedEvents < previousSizeofResult) {
			if (listGoingThroughAllChecks.get(0).getCheapestPriceInPriceList() == listGoingThroughAllChecks
					.get(removeFrom).getCheapestPriceInPriceList()) {
				secondCheckedEvents++;
				removeFrom++;
			} else {
				System.out.println("-----------------------------Removed------------------------------");
				listGoingThroughAllChecks.remove(removeFrom);
				secondCheckedEvents++;
			}
		}

	}

	/**
	 * This method is private as its part of implementation and not part of
	 * interface, its purpose is to sort all the events as per id who are
	 * equally close to the customer and are equally cheap
	 *
	 *
	 * @param nothing
	 * @return nothing
	 */
	private void sortAsPerId(List<EventsDTO> list, String listTobeSorted) {
		list.sort(new Comparator<EventsDTO>() {
			@Override
			public int compare(EventsDTO o1, EventsDTO o2) {
				int id1 = o1.getId();
				int id2 = o2.getId();
				if (id1 == id2)
					return 0;
				else if (id1 > id2)
					return 1;
				else
					return -1;
			}
		});
		if (listTobeSorted.equals("checked list")) {
			System.out.println(
					"------------------Sorted as per id if distance and price is same-----------------------------");
			listGoingThroughAllChecks.forEach((event) -> System.out.println(event));
			System.out.println(
					"------------------Sorted as per id if distance and price is same-----------------------------");
		}
	}

}
