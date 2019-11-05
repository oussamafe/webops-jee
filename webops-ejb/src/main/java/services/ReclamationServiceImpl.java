package services;

import java.util.List;
import java.util.Properties; 
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query; 
import entities.Employe;
import entities.Etat;
import entities.Reclamation; 
import interfaces.ReclamtionImplimentation;
import com.sun.mail.smtp.SMTPTransport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage; 
import java.util.Date; 

@Stateless
@LocalBean
public class ReclamationServiceImpl implements ReclamtionImplimentation {
	@PersistenceContext(unitName = "webops-ejb")
	EntityManager em;

	@Override
	public int ClaimUserToAdmin(Reclamation R) {
		Query q,q1;
		R.setDate(new Date());
		R.setEtat(Etat.En_Attent);
		if(R.getClaimtype().toString().equals("Service_Technique"))
		{
			em.persist(R);
			em.flush();  
		}
		else if(R.getClaimtype().toString().equals("Company")){
			int trait = 0;
			try{
				q = em.createNativeQuery("SELECT id FROM user WHERE "
						+ "claimtraited=(SELECT min(us.claimtraited) FROM user us where us.role='Human_Resources' and us.type='Employee' and company_id='"+R.getCompany().getId()+"')"
						+ "and id=(SELECT `WhoDoneIt_id` ct FROM `reclamation` WHERE etat='En_Attent' GROUP BY `WhoDoneIt_id` ORDER by COUNT(etat) LIMIT 1)");
				trait= (int) q.setMaxResults(1).getSingleResult(); 
				System.out.println("non exception");
			}
			catch (NoResultException nre){ 
				System.out.println("exception");
				q1 = em.createNativeQuery("SELECT id FROM user where role='Human_Resources' and type='Employee' and company_id="+R.getCompany().getId());
				trait= (int) q1.setMaxResults(1).getSingleResult(); 
			}


			Employe emp=new Employe(); 
			emp.setId(trait);
			R.setWhoDoneIt(emp); 

			em.persist(R);
			em.flush(); 
		}
		if(R.getId()!=0)
		{
			q = em.createNativeQuery("SELECT u.email FROM user u WHERE u.id="+R.getUser().getId());
			String maile= (String) q.setMaxResults(1).getSingleResult(); 
			sendMail(maile,"Reclamation Bien récu","Votre reclamation a propos'' "+R.getMessage()+"'' sera traiter le plustot possible merci pour votre confiance");
		}
		return R.getId();

	}

	@Override
	public List<Reclamation> ReadClaim() {
		List<Reclamation> list =  em.createQuery("from Reclamation",Reclamation.class).getResultList(); 
		return list;

	}
	@Override
	public List<Reclamation> ShowClaimByEtat(String etat) {
		List<Reclamation> list = em.createQuery("from Reclamation where etat="+etat,Reclamation.class).getResultList();
		return list;
	}

	@Override
	public void UpdateClaim(Reclamation R) { 
		Reclamation claims=em.find(Reclamation.class,R.getId()); 
		Query q;
		if(R.getReponse()!=null && !R.getReponse().equals(""))
		{ 
			claims.setDateDeTraitement(new Date());
			//			q = em.createNativeQuery("SELECT id FROM user WHERE "
			//					+ "claimtraited=(SELECT min(us.claimtraited) FROM user us where us.role='Human_Resources' and us.type='Employee') "
			//					+ "and id=(SELECT `WhoDoneIt_id` ct FROM `reclamation` WHERE etat='En_Attent' GROUP BY `WhoDoneIt_id` ORDER by COUNT(etat) LIMIT 1)");
			//		
			//			int trait= (int) q.setMaxResults(1).getSingleResult();
			q = em.createQuery("update User e set e.claimtraited=claimtraited+1 where e.id=:trait");
			q.setParameter("trait", claims.getWhoDoneIt().getId());  
			q.executeUpdate(); 
		}

		else if(R.getEtat()!=claims.getEtat())
		{
			q = em.createNativeQuery("SELECT u.email FROM user u WHERE u.id="+claims.getUser().getId());
			String maile= (String) q.setMaxResults(1).getSingleResult();
			sendMail(maile,R.getEtat().toString(),"Votre reclamation est "+R.getEtat().toString());
		}

		claims.setEtat( R.getEtat());  
		claims.setReponse(  R.getReponse());
		claims.setMessage(R.getMessage());
		em.merge(claims);

	}

	@Override
	public void DeleteClaim(int id) {
		Reclamation claim = em.find(Reclamation.class, id);
		em.remove(claim);

	}

	@Override
	public List<Reclamation> SuivieClaim(int Userid) {
		List<Reclamation> list = em.createQuery("from Reclamation R where R.user="+Userid,Reclamation.class).getResultList();
		return list;
	}

	@Override
	public void BestClaimTraiter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void AffectClaimToTrait() {
		// TODO Auto-generated method stub

	}
	@Override
	public List<Reclamation> ShowClaimBySubject(String sujet) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Reclamation> ShowClaimByCompany(int idCompany) {
		// TODO Auto-generated method stub
		return null;
	}


	private static final String SMTP_SERVER = "smtp.gmail.com";
	private static final String USERNAME = "web.ops.esprit@gmail.com";
	private static final String PASSWORD = "rocketmaN19";

	public void sendMail(String EMAIL_TO,String EMAIL_SUBJECT,String EMAIL_TEXT) 
	{ 
		// for example, smtp.mailgun.org


		String EMAIL_FROM = "WebOps";
		String EMAIL_TO_CC = "";

		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "587"); // default port 25
		prop.put("mail.smtp.starttls.enable", "true"); //TLS
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");


		Session session = Session.getInstance(prop, null);
		Message msg = new MimeMessage(session);

		try {

			// from
			msg.setFrom(new InternetAddress(EMAIL_FROM));

			// to 
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(EMAIL_TO, false));

			// cc
			msg.setRecipients(Message.RecipientType.CC,
					InternetAddress.parse(EMAIL_TO_CC, false));

			// subject
			msg.setSubject(EMAIL_SUBJECT);

			// content 
			msg.setText(EMAIL_TEXT);

			msg.setSentDate(new Date());

			// Get SMTPTransport
			SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

			// connect
			t.connect(SMTP_SERVER, USERNAME, PASSWORD);

			// send
			t.sendMessage(msg, msg.getAllRecipients());

			System.out.println("Response: " + t.getLastServerResponse());

			t.close();

		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}




	}
}
