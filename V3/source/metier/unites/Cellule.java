package source.metier.unites;

import source.metier.Equipe;

import java.util.List;
import java.util.ArrayList;


/**
 * La classe {@code Cellule} est le type simple des autres Cellules.
 * Cependant cette classe n'est pas abstraite car il est également possible d'instancier des objets de type {@code Cellule}.
 * 
 * La plupart de ces paramètres sont également utilisés par ses classes filles.
 */

public class Cellule
{
	/** 
	 * Ces constantes sont utilisées pour définir des valeurs maximal à ne pas dépasser.
	 * 
	 * NB_TENTACULE_MAXI : Le nombre maximale qu'une {@code Cellule} peut avoir, indépendant de sa capacité.
	 * NIVEAU_MAXI       : Le niveau maximal  qu'une {@code Cellule} peut atteindre.
	 * PV_MAXI           : Le nombre maximal  qu'une {@code Cellule} peut posséder. 
	 * 
	 * Ces valeurs seront ammenées à être changées pour de futures mises à jour.
	 */
	public static final int NB_TENTACULE_MAXI = 4;
	public static final int NIVEAU_MAXI       = 6;
	public static final int PV_MAXI           = 300;

	/**
	 * Ces constantes définissent l'ensembles des valeurs clés correspondantes au changement d'un niveau.
	 * La {@code Cellule} doit voir son nombre de PV égaler les dites valeurs pour qu'un changement de niveau soit effectuer.
	 * 
	 * Les valeurs UP indiquent que le niveau de la {@code Cellule} est inférieur au numéro accompagnant la constante.
	 * Les valeurs DW indiquent que le niveau de la {@code Cellule} est supérieur au numéro accompagnant la constante.
	 * 
	 * Ex : Une {@code Cellule} instanciée avec 10 PV et de niveau 1 doit atteindre 15 Points de Vie pour passer au niveau 2.
	 *      Une {@code Cellule} instanciée avec 70 PV et de niveau 4 doit atteindre 60 Points de Vie pour repasser au niveau 3. 
	 */

	public static final int UP_NIVEAU_2 = 15;
	public static final int UP_NIVEAU_3 = 40;
	public static final int UP_NIVEAU_4 = 80;
	public static final int UP_NIVEAU_5 = 120;
	public static final int UP_NIVEAU_6 = 160;

	public static final int DW_NIVEAU_1 = 5;
	public static final int DW_NIVEAU_2 = 30;
	public static final int DW_NIVEAU_3 = 60;
	public static final int DW_NIVEAU_4 = 100;
	public static final int DW_NIVEAU_5 = 140;
	
	/* Compteur séquentiel auto-incrémenté */

	protected static int  nbCellule = 0;
	protected        char nom;

	protected Equipe equipe;
	protected String capacite;

	protected int pv;
	protected int niveauActuel;
	protected int niveauPrc   ;
	protected int nbTentacules;
	protected int nbLiasonsActives;


	/**
	 * Utilisation d'une factory afin de contrôler les valeurs rentrées en paramètres.
	 * Le nombre de PV d'une cellule doit être scrupuleusement respecté.
	 *	
	 * Selon le niveau de la cellule, le nombre de PV est situé dans une plage de valeur bien précise.
	 * Il est donc important de bien contrôler les deux paramètres pv et niveauActuel
	 *
	 * Si le niveauActuel vaut 0, il est calibré en fonction du nombre de PV
	 * Si le nombre de PV vaut 0, ils sont calibrés en fonction du niveauActuel
	 * Si les deux valeurs valent 0, la cellule n'est pas créée.
	 *
	 * Ceci est valable pour n'importe quel type de Cellule.
	 *		
	 * @param niveauActuel le niveau actuel de la cellule instanciée.
	 * @param pv           le nombre de PV  de la cellule instanciée.
	 * @return             Une {@code Cellule} si tout les paramètres sont correctes. Null dans l'autre cas.
	 * 
	 * @see #parametresValide(int, int)                    
	*/

	public static Cellule creerCellule(int niveauActuel, int pv)
	{

		if (!Cellule.parametresValide(niveauActuel, pv) ) return null;
		
		return new Cellule( niveauActuel, pv);

	}

	/** 
	 * Crée une instance de Cellule une fois les paramètres vérifiées
	 * 
	 * @param niveauActuel le niveau actuel de la {@code Cellule} instanciée.
	 * @param pv           le nombre de PV  de la {@code Cellule} instanciée.
	 * @see #parametresValide(int, int)

	*/
	
	protected Cellule(int niveauActuel, int pv)
	{

		this.nom = (char) ('A' + Cellule.nbCellule++);

		this.niveauActuel     = niveauActuel;
		this.niveauPrc        = this.niveauActuel;
		this.pv               = pv;
		this.nbLiasonsActives = 0;
		this.capacite         = "Normale";

		this.calibrerPV    ();
		this.calibrerNiveau();

		
		//Paramétrage du nombre de tentacules en fonction du niveau de la Cellule
		
		if (this.niveauActuel <  2                         ) this.nbTentacules = 1;
		if (this.niveauActuel >= 2 && this.niveauActuel < 5) this.nbTentacules = 2;
		if (this.niveauActuel >= 5                         ) this.nbTentacules = 3;

		this.equipe = new Equipe("Neutre"); // Attribué à l'équipe neutre dès la création.

	}

	/* ---------------------------------- */
	/*              Accesseur             */
	/* ---------------------------------- */

	public static int getNbCellules() { return Cellule.nbCellule;}

	public int    getPv               () { return this.pv             ;}
	public char   getNom              () { return this.nom            ;}
	public int    getNiveau           () { return this.niveauActuel   ;}
	public int    getNbTentacules     () { return this.nbTentacules   ;}
	public int    getNbLiaisonsActives() {return this.nbLiasonsActives;}
	public Equipe getEquipe           () { return this.equipe         ;}
	public String getCapacite         () { return this.capacite       ;}
	
	/* ---------------------------------- */
	/*            Modificateurs           */
	/* ---------------------------------- */

	public boolean setEquipe(Equipe e) 
	{
		if (e == null) return false;

		this.equipe = e;
		return true;

	}	

	public void supprimerLiaisonActive() {this.nbLiasonsActives--;}
	public void ajouterLiaisonActive  () {this.nbLiasonsActives++;}

	/* ---------------------------------- */
	/*           Autres Méthodes          */
	/* ---------------------------------- */

	public static void reinitialiserCompteur() { Cellule.nbCellule = 0;}

	protected static boolean parametresValide(int niveauActuel, int pv)
	{
		
		if (niveauActuel > Cellule.NIVEAU_MAXI)  return false;
		if (pv     <= 0 && niveauActuel <= 0  )  return false;
		
		return true;	

	}

	public boolean lienPossible() { return this.nbLiasonsActives < this.nbTentacules && this.nbLiasonsActives >= 0;}

	public String toString()
	{
		String sRet = "";

		sRet += "\nCellule "            + this.nom                                +" :\n\n" + 
		        "Points de vie      : " + this.pv + " (" + this.niveauActuel +")" + "\n"    + 
				"Équipe             : " + this.equipe.getNom()                    + "\n"    +
				"Capacité           : " + this.getCapacite  ();

		return sRet;
		
	}

	/* ---------------------------------- */
	/*          Méthodes Privées          */
	/* ---------------------------------- */

	private void calibrerPV()
	{

		//Vérification du nombre de PV rentrée en paramètre et correction si nécessaire

		int calibrageNiveauPv = 

		(this.niveauActuel == 1 &&  this.pv >  Cellule.UP_NIVEAU_2)                                    ? 0 : 
		(this.niveauActuel == 2 && (this.pv <= Cellule.DW_NIVEAU_1 || this.pv > Cellule.UP_NIVEAU_3) ) ? 1 : 
		(this.niveauActuel == 3 && (this.pv <= Cellule.DW_NIVEAU_2 || this.pv > Cellule.UP_NIVEAU_4) ) ? 2 :	
		(this.niveauActuel == 4 && (this.pv <= Cellule.DW_NIVEAU_3 || this.pv > Cellule.UP_NIVEAU_5) ) ? 3 : 
		(this.niveauActuel == 5 && (this.pv <= Cellule.DW_NIVEAU_4 || this.pv > Cellule.UP_NIVEAU_6) ) ? 4 :
		(this.niveauActuel == 6 && (this.pv <= Cellule.DW_NIVEAU_5)|| this.pv > Cellule.PV_MAXI )      ? 5 : -1;

		if (calibrageNiveauPv == -1) this.niveauActuel = 1;

		this.pv = switch (calibrageNiveauPv) 
		{
			case 0 -> (int) (Math.random() * (Cellule.UP_NIVEAU_2 -1)                                             + 1 );
			case 1 -> (int) (Math.random() * (Cellule.UP_NIVEAU_3 - Cellule.UP_NIVEAU_2 -1) + Cellule.DW_NIVEAU_1 + 1 ); 
			case 2 -> (int) (Math.random() * (Cellule.UP_NIVEAU_4 - Cellule.DW_NIVEAU_2 -1) + Cellule.DW_NIVEAU_2 + 1 );
			case 3 -> (int) (Math.random() * (Cellule.UP_NIVEAU_5 - Cellule.DW_NIVEAU_3 -1) + Cellule.DW_NIVEAU_3 + 1 );
			case 4 -> (int) (Math.random() * (Cellule.UP_NIVEAU_6 - Cellule.DW_NIVEAU_4 -1) + Cellule.DW_NIVEAU_4 + 1 );
			case 5 -> (int) (Math.random() * (Cellule.PV_MAXI     - Cellule.DW_NIVEAU_5 -1) + Cellule.DW_NIVEAU_5 + 1 );
			
			default -> pv;
		
		};

	}

	private void calibrerNiveau()
	{

		if (this.pv < Cellule.UP_NIVEAU_2)                                   this.niveauActuel = 1;
		if (this.pv >= Cellule.UP_NIVEAU_2 && this.pv < Cellule.UP_NIVEAU_3) this.niveauActuel = 2;
		if (this.pv >= Cellule.UP_NIVEAU_3 && this.pv < Cellule.UP_NIVEAU_4) this.niveauActuel = 3;
		if (this.pv >= Cellule.UP_NIVEAU_4 && this.pv < Cellule.UP_NIVEAU_5) this.niveauActuel = 4;
		if (this.pv >= Cellule.UP_NIVEAU_5 && this.pv < Cellule.UP_NIVEAU_6) this.niveauActuel = 5;
		if (this.pv >= Cellule.UP_NIVEAU_6                                 ) this.niveauActuel = 6;

	}

}