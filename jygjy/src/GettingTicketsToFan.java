import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GettingTicketsToFan {
    private static EventsDTO globaleEventsDTO = new EventsDTO(); 
    private static int numOfEvents ;
    public static class LocationDTO {

        private int x_axis;
        private int y_axis;

        public int getX_axis() {
            return x_axis;
        }

        public void setX_axis(int x_axis) {
            this.x_axis = x_axis;
        }

        public int getY_axis() {
            return y_axis;
        }

        public void setY_axis(int y_axis) {
            this.y_axis = y_axis;
        }

        @Override
        public String toString() {
            return x_axis + "," + y_axis;
        }

    }

    public static class EventsDTO {

        private int id;
        private int distance;
        
        private int cheapestPrice;

        public int getCheapestPrice() {
            return cheapestPrice;
        }

        public void setCheapestPrice(int cheapestPrice) {
            this.cheapestPrice = cheapestPrice;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public  List<EventsDTO> getEventsList() {
            return eventsList;
        }

        public  void setEventsList(EventsDTO eventsList) {
            this.eventsList.add(eventsList);
        }

        private LocationDTO location;
        private int tickets;
        private List<Integer> price = new ArrayList<>();
        private  List<EventsDTO> eventsList = new ArrayList<>();

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

        public void setPrice(Integer i) {
            this.price.add(i);
        }

        public  void setEventsList(List<EventsDTO> eventsList) {
            this.eventsList = eventsList;
        }

        @Override
        public String toString() {
            return "EventsDTO [id=" + id + ", location=" + location + ", tickets=" + tickets + ", price=" + price + "]";
        }

    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner scan = new Scanner(System.in);
        
        int sizeOfWorld = Integer.parseInt(scan.nextLine());
        int numberOfEvents = Integer.parseInt(scan.nextLine());
        numOfEvents = numberOfEvents;
        int i =0;
        while(i < numberOfEvents){
            String eventLine = scan.nextLine();
            LocationDTO loc = new LocationDTO();
            EventsDTO eventDto = new EventsDTO();
            String[] eventsDetails = eventLine.split(" ");
            loc.setX_axis(Integer.parseInt(eventsDetails[1]));
            loc.setY_axis(Integer.parseInt(eventsDetails[2]));
            if (!(loc.getX_axis() < sizeOfWorld && loc.getY_axis() < sizeOfWorld))
                continue;
            eventDto.setLocation(loc);
            eventDto.setId(Integer.parseInt(eventsDetails[0])); 
            for( int j = 2; j < eventsDetails.length - 1; j++){
                eventDto.setPrice(Integer.parseInt(eventsDetails[j]));
            }
            globaleEventsDTO.setEventsList(eventDto);

            i++;
        }
        
        int numberOfBuyers = Integer.parseInt(scan.nextLine());
        int k= 0;
        while(k < numberOfBuyers ){
            
            LocationDTO loc = new LocationDTO();
            String buyerLine = scan.nextLine();
            String[] axisMeasures = buyerLine.split(" ");
            loc.setX_axis(Integer.parseInt(axisMeasures[0]));
            loc.setY_axis(Integer.parseInt(axisMeasures[1]));
            if(! (loc.getX_axis() < sizeOfWorld && loc.getY_axis() < sizeOfWorld))
            continue;
            List<EventsDTO> events = findClosesEvents(loc);
            for(EventsDTO event: events){
                int dis1 = calculateManhattanDistance(event.getLocation().getX_axis(), loc.getX_axis(),
                        event.getLocation().getY_axis(), loc.getY_axis());
                event.setDistance(dis1);
                event.setCheapestPrice(event.getPrice().get(0));
            }
            int check =0;
            int smallestprice = 0;
            List<EventsDTO> temp = new ArrayList<EventsDTO>();
            for(EventsDTO event: events){
                if(events.get(0).getDistance() == event.getDistance() ){
                    temp.add(event);
                    
                }   

            }
            Collections.sort(temp,  new Comparator<EventsDTO>() {

            @Override
            public int compare(EventsDTO o1, EventsDTO o2) {
                int pric1 =o1.getCheapestPrice();
                int pric2 = o2.getCheapestPrice();

                 if (pric1 == pric2)
                    return 0;
                else if (pric1 > pric2)
                    return 1;
                else
                    return -1;
            }
        });
            List<EventsDTO> temp2 = new ArrayList<EventsDTO>();
            for (EventsDTO event : events) {
                if (events.get(0).getCheapestPrice() == event.getCheapestPrice()) {
                    temp2.add(event);

                }
            }
            Collections.sort(temp2, new Comparator<EventsDTO>() {

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

          System.out.println(temp2.get(0).getId()+" "+ temp2.get(0).getPrice());
            k++;
        }

        // The solution to the first sample above would be to output the following to console:
        // (Obviously, your solution will need to figure out the output and not just hard code it)
        
    }    
    
    // The following method get the manhatten distance betwen two points (x1,y1) and (x2,y2)
    public static int calculateManhattanDistance(int x1, int y1, int x2, int y2)    {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    public static List<EventsDTO> findClosesEvents(LocationDTO searchLocation) {
        ArrayList<EventsDTO> result = new ArrayList<>();
        Collections.sort(globaleEventsDTO.getEventsList(), new Comparator<EventsDTO>() {

            @Override
            public int compare(EventsDTO o1, EventsDTO o2) {
                int dis1 = calculateManhattanDistance(o1.getLocation().getX_axis(), searchLocation.getX_axis(),
                        o1.getLocation().getY_axis(), searchLocation.getY_axis());
                int dis2 = calculateManhattanDistance(o2.getLocation().getX_axis(), searchLocation.getX_axis(),
                        o2.getLocation().getY_axis(), searchLocation.getY_axis());
                if (dis1 == dis2)
                    return 0;
                else if (dis1 > dis2)
                    return 1;
                else
                    return -1;
            }
        });
        int numOfEventsAdded = 0;
        while (numOfEventsAdded < numOfEvents ) {
            EventsDTO temp = new EventsDTO();
            temp.setId(globaleEventsDTO.getEventsList().get(numOfEventsAdded).getId());
            temp.setLocation(globaleEventsDTO.getEventsList().get(numOfEventsAdded).getLocation());
            temp.setPrice(Collections.min(globaleEventsDTO.getEventsList().get(numOfEventsAdded).getPrice()));
            result.add(temp);
            numOfEventsAdded++;
        }
        return result;
    }
}

