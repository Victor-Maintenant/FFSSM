package FFSSM;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Plongeur extends Personne {
	
	private int niveau;
	private GroupeSanguin groupe;
	private List<Licence> licenses = new LinkedList<>();

	public Plongeur(String numeroINSEE, String nom, String prenom, String adresse, String telephone,
			LocalDate naissance, int niveau, GroupeSanguin gs) {
		super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
		this.niveau = niveau;
		this.groupe = gs;
	}
	
	public void ajouteLicence(String numero, LocalDate delivrance) {
		Licence l = new Licence(numero, delivrance);
		this.licenses.add(l);
	}
	
	
}
