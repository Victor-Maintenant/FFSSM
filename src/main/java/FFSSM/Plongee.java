/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Plongee {

	public Site lieu;
	public Moniteur chefDePalanquee;
	public LocalDate date;
	public int profondeur;
	public int duree;
	public List<Licence> plongeurs = new LinkedList<>();

	public Plongee(Site lieu, Moniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
		this.lieu = lieu;
		this.chefDePalanquee = chefDePalanquee;
		this.date = date;
		this.profondeur = profondeur;
		this.duree = duree;
	}
	

	public void ajouteParticipant(Licence l) {
		if (!this.plongeurs.contains(l)) this.plongeurs.add(l);
	}

	public LocalDate getDate() {
		return date;
	}

	/**
	 * Détermine si la plongée est conforme. 
	 * Une plongée est conforme si tous les plongeurs de la palanquée ont une
	 * licence valide à la date de la plongée
	 * @return vrai si la plongée est conforme
	 * @throws Exception 
	 */
	public boolean estConforme() throws Exception {
		if (this.plongeurs.isEmpty()) throw new Exception("Aucune plongée enregistrée");
		for(Licence l : this.plongeurs) {
			if (!l.estValide(this.date)) {
				return false;
			}
		}
		return true;
	}


	public List<Licence> getPlongeurs() {
		return plongeurs;
	}

}
