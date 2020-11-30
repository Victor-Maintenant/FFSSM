/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Moniteur extends Plongeur {

    public int numeroDiplome;
    private final HashMap<Club, Embauche> emplois = new HashMap<>();

    public Moniteur(String numeroINSEE, String nom, String prenom, String adresse, 
    		String telephone, LocalDate naissance, int numeroDiplome, int niveau, GroupeSanguin groupe, int numDiplome ) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance, niveau, groupe);
        this.numeroDiplome = numeroDiplome;
    }

    public Map<Club, Embauche> getEmplois() {
		return emplois;
	}

	/**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est terminée,
     * ce moniteur n'a pas d'employeur.
     * @return l'employeur actuel de ce moniteur sous la forme d'un Optional
     */
    public Optional<Club> employeurActuel() throws Exception {
    	if (this.emplois.isEmpty()) throw new Exception("Aucun emploi");
    	Club c = new Club(null, null, null);
    	Embauche e = new Embauche(naissance,  null, null);
    	for (Club club : this.emplois.keySet()) {
    		if (this.emplois.get(club).getDebut().isAfter(e.getDebut())) {
    			e = this.emplois.get(club);
    			c = club;
    		}
    	}
    	if (this.emplois.get(c).estTerminee()) throw new Exception("Dernier emploi déjà terminé");
    	return Optional.ofNullable(this.emplois.get(c).getEmployeur());
    }
    
    /**
     * Enregistrer une nouvelle embauche pour cet employeur
     * @param employeur le club employeur
     * @param debutNouvelle la date de début de l'embauche
     */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) {   
    	Embauche e = new Embauche(debutNouvelle, this, employeur);
    	this.emplois.put(employeur, e);
    }

    
    /**
     * retourne la liste des embauches du moniteur
     * @return la liste des embauches
     * @throws Exception 
     */
    public List<Embauche> emplois() throws Exception {
    	if (this.emplois.isEmpty()) {
    		throw new Exception("Aucun emplois enregistré");
    	}
    	else {
    		List<Embauche> e = new LinkedList<>();
        	this.emplois.forEach((club, embauche) ->{
        		e.add(embauche);
        	});
        	return e;
    	}
    }

}
