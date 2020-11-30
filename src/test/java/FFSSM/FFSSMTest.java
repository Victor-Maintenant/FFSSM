package FFSSM;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class FFSSMTest {
	Club c1, c2, c3;
	Plongeur p1, p2;
	Moniteur m1, m2, m3;
	LocalDate date = LocalDate.now();
	
	@BeforeEach
	public void setUp() {
		p1 = new Plongeur("01", "Michel", "Michelle", "Marseille", "01", date.minusYears(40), 1, GroupeSanguin.APLUS);
		p2 = new Plongeur("01", "Denise", "Denis", "Marseille", "02", date.minusYears(30), 2, GroupeSanguin.APLUS);
		m1 = new Moniteur("1", "Untel", "Un", "Marseile", "01", date.minusYears(25), 1, 9, GroupeSanguin.AMOINS, 1);
		c1 = new Club(m1, "club1", "01");
		c2 = new Club(m2, "club2", "02");
	}
	
	@Test
	public void testAjoutLicence() {
		assertTrue(p1.getLicenses().isEmpty());
		p1.ajouteLicence("L1", date);
		p1.ajouteLicence("L2", date);
		assertEquals(2, p1.getLicenses().size());
	}
	
	@Test
	public void testGetterSetterLicence() {
		p1.ajouteLicence("L1", date);
		p1.getLicenses().get(0).setClub(c1);
		p1.getLicenses().get(0).setNiveau(p1.getNiveau());
		p1.getLicenses().get(0).setPossesseur(p1);
		assertEquals(c1,p1.getLicenses().get(0).getClub());
		assertEquals(p1.getNiveau(),p1.getLicenses().get(0).getNiveau());
		assertEquals(p1,p1.getLicenses().get(0).getPossesseur());
		assertEquals("L1",p1.getLicenses().get(0).getNumero());
		assertEquals(date,p1.getLicenses().get(0).getDelivrance());
	}
		
	@Test
	public void testLicenceEstValide() {
		Licence l = new Licence(p1, "L1", date, p1.getNiveau(), c1);
		assertTrue(l.estValide(date.plusMonths(2)));
		assertFalse(l.estValide(date.plusYears(2)));
	}

	@Test
	public void testInitSite() {
		Site s = new Site("Marseille", "details");
		s.setDetails("PlusDeDetails");
		s.setNom("Mediterranee");
		assertEquals("PlusDeDetails", s.getDetails());
		assertEquals("Mediterranee",s.getNom());
		assertEquals("Site{nom=Mediterranee, details=PlusDeDetails}",s.toString());
	}

	@Test
	public void testAjouterParticipantPlongee() {
		Site s = new Site("Marseille", "details");
		Plongee p = new Plongee(s, m1, date, 25, 60);
		assertEquals(0,p.getPlongeurs().size());
		Licence l = new Licence(p1, "L1", date, p1.getNiveau(), c1);
		p.ajouteParticipant(l);
		assertEquals(1,p.getPlongeurs().size());
	}

	@Test
	public void testEstConformePlongee() {
		Site s = new Site("Marseille", "details");
		Plongee p = new Plongee(s, m1, date, 25, 60);
		Licence l1 = new Licence(p1, "L1", date.minusMonths(2), p1.getNiveau(), c1);
		Licence l2 = new Licence(p2, "L2", date.minusYears(2), p2.getNiveau(), c1);
		p.ajouteParticipant(l1);
		assertTrue(p.estConforme());
		p.ajouteParticipant(l2);
		assertFalse(p.estConforme());
	}

	@Test
	public void testOrganisePlongeeClub() {
		Site s = new Site("Marseille", "details");
		Plongee p = new Plongee(s, m1, date, 25, 60);
		Licence l1 = new Licence(p1, "L1", date.minusMonths(2), p1.getNiveau(), c1);
		p.ajouteParticipant(l1);
		assertEquals(0,c1.getActivites().size());
		c1.organisePlongee(p);
		assertEquals(1,c1.getActivites().size());	
	}
	
	@Test 
	public void testEmbauche() {
		Embauche e  = new Embauche(date, m1, c1);
		assertFalse(e.estTerminee());
		e.terminer(date.plusMonths(2));
		assertEquals(date.plusMonths(2), e.getFin());
	}

	@Test
	public void testPlongeeNonConforme() throws Exception {
		Site s = new Site("Marseille", "details");
		Plongee pl1 = new Plongee(s, m1, date, 25, 60);
		Plongee pl2 = new Plongee(s, m1, date, 25, 60);
		Licence l1 = new Licence(p1, "L1", date.minusMonths(2), p1.getNiveau(), c1);
		Licence l2 = new Licence(p2, "L2", date.minusYears(2), p2.getNiveau(), c1);
		pl1.ajouteParticipant(l1);
		pl2.ajouteParticipant(l2);
		c1.organisePlongee(pl1);
		c1.organisePlongee(pl2);
		assertEquals(1,c1.plongeesNonConformes().size());
		assertEquals(pl2,c1.plongeesNonConformes().toArray()[0]);
	}

	@Test
	public void testPlongeeNonConformePasDePlongee() {
		assertTrue(c1.activites.isEmpty());
		assertThrows(Exception.class, () -> {
	        c1.plongeesNonConformes();
	    });
	}

	@Test
	public void testEmploisMoniteur() throws Exception {
		assertTrue(m1.getEmplois().isEmpty());
		assertThrows(Exception.class, () -> {
		       m1.emplois();
		    });
		m1.nouvelleEmbauche(c1, date.minusYears(1));
		m1.nouvelleEmbauche(c2, date);
		assertEquals(2,m1.emplois().size());
	}

	@Test
	public void testEmployerActuel() throws Exception {
		assertThrows(Exception.class, () -> {
			m1.employeurActuel();
	    });
		m1.nouvelleEmbauche(c1, date.minusYears(1));
		m1.getEmplois().get(c1).terminer(date.minusWeeks(1));
		assertThrows(Exception.class, () -> {
			m1.employeurActuel();
	    });
		m1.nouvelleEmbauche(c2, date);
		assertEquals(c2,m1.employeurActuel().get());
	}
	
}
