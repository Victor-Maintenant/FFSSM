/**
 * @(#) Club.java
 */
package FFSSM;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Club {

    public Moniteur president;
    public String nom;
    public String adresse;
    public String telephone;
    public List<Plongee> activites = new LinkedList<>();
    
    

    public Club(Moniteur président, String nom, String telephone) {
        this.president = président;
        this.nom = nom;
        this.telephone = telephone;
    }

    /**
     * Calcule l'ensemble des plongées non conformes organisées par ce club.
     * Une plongée est conforme si tous les plongeurs de la palanquée ont une licence
     * valide à la date de la plongée
     * @return l'ensemble des plongées non conformes
     * @throws Exception 
     */
    public Set<Plongee> plongeesNonConformes() throws Exception {
    	if(this.activites.isEmpty()) {
    		throw new Exception("Aucune plongée ajoutée");
    	}
    	else {
    		Set<Plongee> pNonValide = new HashSet<>();
            for( Plongee p : this.activites) {
            	if(!p.estConforme()) {
            		pNonValide.add(p);
            	}
            }
            return pNonValide;
    	}
    }

    /**
     * Enregistre une nouvelle plongée organisée par ce club
     * @param p la nouvelle plongée
     */
    public void organisePlongee(Plongee p) {
    	this.activites.add(p);
    }
    
    
    public List<Plongee> getActivites() {
		return activites;
	}

	public Moniteur getPresident() {
        return president;
    }

    public void setPresident(Moniteur président) {
        this.president = président;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Club{" + "président=" + president + ", nom=" + nom + ", adresse=" + adresse + ", telephone=" + telephone + '}';
    }

}
