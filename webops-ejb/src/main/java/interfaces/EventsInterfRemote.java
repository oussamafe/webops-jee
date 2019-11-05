package interfaces;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import entities.Events;

@Remote
public interface EventsInterfRemote {

	public Events addEvent(Events event);
	public void editEvent(int idEvent , Events event);
	public void deleteEvent(int idEvent);
	public void addParticipant(int idEvent , int idCandidate);
	public void removeParticipant(int idEvent , int idCandidate);
	public List<Events> showAllEvents();
	public Set<Events> showEventsByCompany(int idCompany);
	public void indexEvent(Events event);
	
}
