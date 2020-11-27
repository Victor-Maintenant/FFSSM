/**
 * @(#) LicencePlongeur.java
 */
package FFSSM;

import java.time.LocalDate;

public class Licence {

    public Personne possesseur;
    public String numero;
    public LocalDate delivrance;
    public int niveau;
    public Club club;

    public Licence(Personne possesseur, String numero, LocalDate delivrance, int niveau, Club club) {
        this.possesseur = possesseur;
        this.numero = numero;
        this.delivrance = delivrance;
        this.niveau = niveau;
        this.club = club;
    }

    public Licence(String numero, LocalDate delivrance) {
		this.numero = numero;
		this.delivrance = delivrance;
	}

	public Personne getPossesseur() {
        return possesseur;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDate getDelivrance() {
        return delivrance;
    }

    public int getNiveau() {
        return niveau;
    }

    public Club getClub() {
        return club;
    }

    public void setPossesseur(Personne possesseur) {
		this.possesseur = possesseur;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	/**
     * Est-ce que la licence est valide à la date indiquée ?
     * Une licence est valide pendant un an à compter de sa date de délivrance
     * @param d la date à tester
     * @return vrai si valide à la date d
     **/
    public boolean estValide(LocalDate d) {
         return this.delivrance.plusYears(1).isAfter(d);
    }

}
