package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import interfaces.InterviewManagementRemote;

@Stateless
@LocalBean
public class InterviewManagementImplimentation implements InterviewManagementRemote{
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;
}

