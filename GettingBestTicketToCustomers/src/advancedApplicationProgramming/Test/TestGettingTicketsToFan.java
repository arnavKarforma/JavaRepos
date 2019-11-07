/**
 * ?????????????????????????????????????????????/
 *
 * @author Arnav Kaforma
 * @version DD-MM-YY
 */

package advancedApplicationProgramming.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import advancedApplicationProgramming.Constraints;
import advancedApplicationProgramming.BusinessLogic.Service;
import advancedApplicationProgramming.BusinessLogicImpl.ServiceImpl;
import advancedApplicationProgramming.DTO.EventsDTO;

/**
 * @author ARNAV
 *
 */
public class TestGettingTicketsToFan {

	Service service = new ServiceImpl("Test");

	/* (non-Javadoc)
	 * @see advancedApplicationProgramming.Test.bgk#testUnitTest()
	 */
	
	@Test
	public void testUnitTest() {
		assertEquals(1, 1);
	}

	/* (non-Javadoc)
	 * @see advancedApplicationProgramming.Test.bgk#testA()
	 */

	
	@Test
	public void testA() {
		service.generateData();
		int tot = Constraints.TOTAL_EVENTS;
		int maxTicketCounts = Constraints.MAX_TICKETS_IN_ONE_EVENT;
		int minTicketCounts = Constraints.MIN_TICKETS_IN_ONE_EVENT;
		assertEquals(tot, EventsDTO.getEventsList().size());
		for (EventsDTO e : EventsDTO.getEventsList()) {
			assertTrue(e.getPrice().size() <= maxTicketCounts);
			assertTrue(e.getPrice().size() >= minTicketCounts);
		}
		service.writeInFile(Constraints.TEST_FILE_PATH);
	}

	/* (non-Javadoc)
	 * @see advancedApplicationProgramming.Test.bgk#testB()
	 */
	
	@Test
	public void testB() {
		service.writeInFile(Constraints.TEST_FILE_PATH);
		File f = new File(new File("").getAbsolutePath() + Constraints.TEST_FILE_PATH);
		assertTrue(f.exists() && !f.isDirectory());

	}

	/* (non-Javadoc)
	 * @see advancedApplicationProgramming.Test.bgk#testC()
	 */
	
	@Test
	public void testC() {
		EventsDTO.setFullEventsList(null);
		int tot = Constraints.TOTAL_EVENTS;
		service.readFromFile(Constraints.TEST_FILE_PATH);
		assertEquals(tot, EventsDTO.getEventsList().size());

	}

	/* (non-Javadoc)
	 * @see advancedApplicationProgramming.Test.bgk#testD()
	 */
	
	@Test
	public void testD() {
		int idGenerator = EventsDTO.getEventsList().size() + 1;
		EventsDTO firstEvent = EventsDTO.addEvent(-1, EventsDTO.getEventsList().get(0).getLocation(),
				EventsDTO.getEventsList().get(0).getPrice());
		EventsDTO secondlastEvent = EventsDTO.addEvent(-1,
				EventsDTO.getEventsList().get(Constraints.TOTAL_EVENTS - 1).getLocation(), Arrays.asList(1, 2, 3));
		EventsDTO lastEvent = EventsDTO.addEvent(-1,
				EventsDTO.getEventsList().get(Constraints.TOTAL_EVENTS - 1).getLocation(), Arrays.asList(1, 2, 3));
		firstEvent.setId(idGenerator++);
		secondlastEvent.setId(idGenerator++);
		lastEvent.setId(idGenerator++);
		EventsDTO.setEventsList(firstEvent);
		EventsDTO.setEventsList(secondlastEvent);
		EventsDTO.setEventsList(lastEvent);
		EventsDTO event = service.findClosesEvents(secondlastEvent.getLocation());
		int id = event.getId();
		int price = event.getCheapestPriceInPriceList();
		int eventIdSelected = secondlastEvent.getId();
		assertEquals(secondlastEvent.getId(), id);
		assertEquals(price,1);
		List<Integer> removedBookedTicketPrice = EventsDTO.getEventsList().stream().filter(i -> i.getId() == eventIdSelected).findFirst().map(EventsDTO::getPrice).orElse(null);
		assertTrue(!removedBookedTicketPrice.contains(1));
	}


	@AfterClass
	public static void afterAllTest() {
		EventsDTO.setFullEventsList(null);
		assertEquals(null, EventsDTO.getEventsList());

	}

}
