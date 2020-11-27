package FFSSM;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class FFSSMTest {
	Club c1, c2;
	Plongeur p1, p2;
	Moniteur m1, m2, m3;
	LocalDate date = LocalDate.now();
	
	@BeforeEach
	public void setUp() {
		p1 = new Plongeur("01", "Michel", "Michelle", "Marseille", "01", date, 1, GroupeSanguin.APLUS);
		p2 = new Plongeur("01", "Denise", "Denis", "Marseille", "02", date, 2, GroupeSanguin.APLUS);
		m1 = new Moniteur("1", "Untel", "Un", "Marseile", "01", date, 1, 9, GroupeSanguin.AMOINS, 1);
		m2 = new Moniteur("2", "Deuxtel", "Deux", "Toulon", "02", date, 2, 9, GroupeSanguin.BPLUS, 1);
		m3 = new Moniteur("3", "Troistel", "Trois", "Marseile", "03", date, 3, 9, GroupeSanguin.AMOINS, 1);
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
	
}
