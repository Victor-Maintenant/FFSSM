/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Moniteur extends Plongeur {

    public int numeroDiplome;
    private final List<Embauche> emplois = new LinkedList<>();

    public Moniteur(String numeroINSEE, String nom, String prenom, String adresse, 
    		String telephone, LocalDate naissance, int numeroDiplome, int niveau, GroupeSanguin groupe, int numDiplome ) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance, niveau, groupe);
        this.numeroDiplome = numeroDiplome;
    }

	/**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est terminée,
     * ce moniteur n'a pas d'employeur.
     * @return l'employeur actuel de ce moniteur sous la forme d'un Optional
     */
    public Optional<Club> employeurActuel() throws Exception {
    	if (this.emplois.isEmpty()) throw new Exception("Aucun emploi");
    	if (this.emplois.get(this.emplois.size()-1).estTerminee()) throw new Exception("Dernier emploi déjà terminé");
    	return Optional.ofNullable(this.emplois.get(this.emplois.size()-1).getEmployeur());
    }
    
    /**
     * Enregistrer une nouvelle embauche pour cet employeur
     * @param employeur le club employeur
     * @param debutNouvelle la date de début de l'embauche
     */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) {   
    	Embauche e = new Embauche(debutNouvelle, this, employeur);
    	this.emplois.add(e);
    }

    
    /**
     * retourne la liste des embauches du moniteur
     * @return la liste des embauches
     * @throws Exception 
     */
    public List<Embauche> emplois() throws Exception {
    	if (this.emplois.isEmpty()) throw new Exception("Pas d'emploi enregistré");
    	return this.emplois;
    	}
}

