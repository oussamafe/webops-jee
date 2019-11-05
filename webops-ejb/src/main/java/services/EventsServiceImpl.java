package services;

import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.Candidate;
import entities.Company;
import entities.Events;
import entities.Packs;
import interfaces.EventsInterfRemote;

@Stateless
@LocalBean
public class EventsServiceImpl implements EventsInterfRemote {

	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
	
	
	@Override
	public Events addEvent(Events event) {
		try {
			em.persist(event);
		}catch(PersistenceException e){}
		return null;
	}

	@Override
	public void editEvent(int idEvent , Events event) {
		Events events = em.find(Events.class, idEvent);
		if(events != null)
		{
			event.setId(event.getId());
			em.merge(event);
		}
	}

	@Override
	public void deleteEvent(int idEvent) {
		Events event = em.find(Events.class,idEvent);
		if (event != null) {
			em.remove(event);
		}

	}

	@Override
	public void addParticipant(int idEvent, int idCandidate) {
		
		Candidate candidate = em.find(Candidate.class, idCandidate);
		Events event = em.find(Events.class,idEvent);
		if(candidate != null && event != null)
		{
			event.getParticipants().add(candidate);
		}

	}

	@Override
	public void removeParticipant(int idEvent , int idCandidate) {
		Candidate candidate = em.find(Candidate.class, idCandidate);
		Events event = em.find(Events.class,idEvent);
		if(candidate != null && event != null)
		{
			event.getParticipants().remove(candidate);
		}

	}

	@Override
	public List<Events> showAllEvents() {
		
		List<Events> list = em.createQuery("from Events",Events.class).getResultList();
		if(list != null)
		{
			return list;
		}
		return null;
	}

	@Override
	public Set<Events> showEventsByCompany(int idCompany) {

		Company company = em.find(Company.class, idCompany);
		if(company != null)
		{
			return company.getEvents();
		}
		return null;
	}

	@Override
	public void indexEvent(Events event) {
		// TODO Auto-generated method stub
		
	}

}
