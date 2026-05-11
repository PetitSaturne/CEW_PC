package source.metier;

import java.util.List;
import java.util.ArrayList;

public class Cellule
{
	public static final int NB_TENTACULE_MAXI = 4;
	public static final int NIVEAU_MAXI       = 6;
	public static final int PV_MAXI           = 300;


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

	private static String[] tabCapacite = {"Soin", "Attaque", "Bonus", "Defense"};

	private static int nbCellule = 0;
	private char       nom;


	private int    pv;
	private int    niveauActuel;
	private int    niveauPrc   ;
	private int    nbTentacules;
	private String capacite;

	private Equipe equipe;

	private boolean source;
	private boolean dest;

	private List<Cellule> liaisons;

	public static Cellule creerCellule(String capacite,int niveauActuel, int pv, Equipe equipe)
	{

		/*Le nombre de PV d'une cellule doit être scrupuleusement respecté.
		
			Selon le niveau de la cellule, le nombre de PV est situé dans une plage de valeur bien précise.
			Il est donc important de bien contrôler les deux paramètres pv et niveauActuel

			Si le niveauActuel vaut 0, il est calibré en fonction du nombre de PV
			Si le nombre de PV vaut 0, ils sont calibrés en fonction du niveauActuel
			Si les deux valeurs valent 0, la cellule n'est pas créée.
		*/



		if (niveauActuel > Cellule.NIVEAU_MAXI)  return null;
		if (pv     < 0 && niveauActuel < 0    )  return null;
		if (!Cellule.capaciteValide(capacite) )  return null;
		

		return new Cellule(capacite, niveauActuel, pv, equipe);

	}
	
	private Cellule(String capacite,int niveauActuel, int pv, Equipe equipe)
	{
		int calibrageNiveauPv = -1;

		this.nom = (char) ('A' + Cellule.nbCellule++);

		this.capacite     = capacite;
		this.niveauActuel = niveauActuel;
		this.niveauPrc    = this.niveauActuel;
		this.pv           = pv;
		this.equipe       = equipe;

		this.dest   = false;
		this.source = false;
		
		this.liaisons = new ArrayList<Cellule>();



		if (this.niveauActuel > 0)
		{

			/*
			Vérification du nombre de PV rentrée en paramètre et correction si nécessaire :

				//1 Le niveau est 1 et le nombre de pv est supérieur à 15.
				//2 Le niveau est 2 et le nombre de pv est plus petit que 5 et plus grand que 40  
				//3 Le niveau est 3 et le nombre de pv est plus petit
			*/


			calibrageNiveauPv = 

			(this.niveauActuel == 1 &&  this.pv >  Cellule.UP_NIVEAU_2)                                    ? 0 : //1
			(this.niveauActuel == 2 && (this.pv <= Cellule.DW_NIVEAU_1 || this.pv > Cellule.UP_NIVEAU_3) ) ? 1 : //2
			(this.niveauActuel == 3 && (this.pv <= Cellule.DW_NIVEAU_2 || this.pv > Cellule.UP_NIVEAU_4) ) ? 2 :	
			(this.niveauActuel == 4 && (this.pv <= Cellule.DW_NIVEAU_3 || this.pv > Cellule.UP_NIVEAU_5) ) ? 3 : 
			(this.niveauActuel == 5 && (this.pv <= Cellule.DW_NIVEAU_4 || this.pv > Cellule.UP_NIVEAU_6) ) ? 4 :
			(this.niveauActuel == 6 && (this.pv <= Cellule.DW_NIVEAU_5)|| this.pv > Cellule.PV_MAXI )      ? 5 : -1;

			System.out.println("Cellule.Cellule() --> " + this.niveauActuel + " *** " + calibrageNiveauPv);

			//if (calibrageNiveauPv == -1) this.niveauActuel = 1;

			this.pv = switch (calibrageNiveauPv) 
			{
				case 0 -> (int) (Math.random() * (Cellule.UP_NIVEAU_2 -1)                                             + 1 );
				case 1 -> (int) (Math.random() * (Cellule.UP_NIVEAU_3 - Cellule.UP_NIVEAU_2 -1) + Cellule.DW_NIVEAU_1 + 1 ); 
				case 2 -> (int) (Math.random() * (Cellule.UP_NIVEAU_4 - Cellule.DW_NIVEAU_2 -1) + Cellule.DW_NIVEAU_2 + 1 );
				case 3 -> (int) (Math.random() * (Cellule.UP_NIVEAU_5 - Cellule.DW_NIVEAU_3 -1) + Cellule.DW_NIVEAU_3 + 1 );
				case 4 -> (int) (Math.random() * (Cellule.UP_NIVEAU_6 - Cellule.DW_NIVEAU_4 -1) + Cellule.DW_NIVEAU_4 + 1 );
				case 5 -> (int) (Math.random() * (Cellule.PV_MAXI     - Cellule.DW_NIVEAU_5 -1) + Cellule.DW_NIVEAU_5 + 1 );
				
				default -> 10;
			
			};

		}

		else
		{
			if (this.pv < Cellule.UP_NIVEAU_2)                                   this.niveauActuel = 1;
			if (this.pv >= Cellule.UP_NIVEAU_2 && this.pv < Cellule.UP_NIVEAU_3) this.niveauActuel = 2;
			if (this.pv >= Cellule.UP_NIVEAU_3 && this.pv < Cellule.UP_NIVEAU_4) this.niveauActuel = 3;
			if (this.pv >= Cellule.UP_NIVEAU_4 && this.pv < Cellule.UP_NIVEAU_5) this.niveauActuel = 4;
			if (this.pv >= Cellule.UP_NIVEAU_5 && this.pv < Cellule.UP_NIVEAU_6) this.niveauActuel = 5;
			if (this.pv >= Cellule.UP_NIVEAU_6                                 ) this.niveauActuel = 6;
		}

		// ??

		/*if (this.pv < 0)
		{
			switch (this.niveauActuel) 
			{
				case 1 -> this.pv = 10;
				case 2 -> this.pv = Cellule.DW_NIVEAU_2;
				case 3 -> this.pv = Cellule.DW_NIVEAU_3;
				case 4 -> this.pv = 90;
				case 5 -> this.pv = 125;
			}
		}*/

		
		//Paramètrage du nombre de tentacules en fonction du niveau de la cellule

		if (this.niveauActuel <  2                         ) this.nbTentacules = 1;
		if (this.niveauActuel >= 2 && this.niveauActuel < 5) this.nbTentacules = 2;
		if (this.niveauActuel >= 5                         ) this.nbTentacules = 3;


		if (this.equipe   == null) this.equipe   = new Equipe();
		if (this.capacite == null) this.capacite = "Aucune";
		if (this.capacite.equals(tabCapacite[2] ) ) this.nbTentacules++;

	}



	public int           getPv               ()          { return this.pv                              ;}
	public char          getNom              ()          { return this.nom                             ;}
	public int           getNiveau           ()          { return this.niveauActuel                    ;}
	public int           getNbLiaisonsActive ()          { return this.liaisons.size()                 ;}
	public int           getNbTentacules     ()          { return this.nbTentacules                    ;}
	public Cellule       getLiaison          (int index) { return this.liaisons.get(index)             ;}
	public List<Cellule> getLstLiaisons      ()          { return new ArrayList<Cellule>(this.liaisons);}
	public String        getCapacite         ()          { return this.capacite                        ;}
	public String        getEquipe           ()          { return this.equipe.getEquipe();}

	public boolean estSource () { return this.source;}
	public boolean estDest   () { return this.dest  ;}

	public static boolean capaciteValide(String capacite)
	{
		boolean estValide = false;

		if (capacite == null) return true;
		

		for (int cpt = 0; cpt < tabCapacite.length; cpt++) 
			if ( capacite.equals(tabCapacite[cpt]) )

				estValide = true;

		return estValide;
		
	}

	public boolean creerLiaison(Cellule c)
	{
		if (c == null )                                    return false;
		if (this.nbTentacules > Cellule.NB_TENTACULE_MAXI) return false;

		this.liaisons.add(c);
		 
		return true;

	}

	public void supprimerLiaison(Cellule c)
	{

		List<Cellule> lstTemp = new ArrayList<Cellule> (this.liaisons);

		for (Cellule cellule : liaisons) 	
		{
			if (cellule == c)
				lstTemp.remove(cellule);
			
		}
		
		this.liaisons = lstTemp;

	}

	public static void reinitialiserCompteur() { Cellule.nbCellule = 0;}

	public boolean estEnLienAvec(char cLien)
	{

		boolean estLie = false;

		for (Cellule cellule : liaisons) 
			if (cellule.getNom() == cLien )

				estLie = true;
		
		return estLie;
	}



	public String toString()
	{
		String sRet = "";

		sRet += "\nCellule "            + this.nom                                             +" :\n\n" + 
		        "Points de vie      : " + this.pv + " (" + this.niveauActuel +")"              + "\n"    + 
		        "Nombre de liaisons : " + this.getNbLiaisonsActive() +"/"  + this.nbTentacules + "\n"    +  
				"Capacité           : " + this.capacite                                        + "\n"    + 
				"Équipe             : " + this.equipe.getEquipe();

		return sRet;
	}
}