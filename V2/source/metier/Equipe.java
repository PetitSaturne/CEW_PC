package source.metier;

import java.util.List;
import java.util.ArrayList;

import source.metier.unites.*;

public class Equipe
{

	/* ---------------------------------- */
	/*              Attributs             */
	/* ---------------------------------- */

	private List<Cellule> lstCellule;
	private String        nom;

	public Equipe(String nom)
	{
		this.nom         = nom;
		this.lstCellule  = new ArrayList <Cellule>();
	}

	/* ---------------------------------- */
	/*             Accesseurs             */
	/* ---------------------------------- */

	public Cellule getCellule (char nomCellule)
	{
		for (Cellule cellule : this.lstCellule) 
			if (cellule.getNom() == nomCellule)
				
				return cellule;

		return null;
	}

	public List<Cellule> getLstCellules() { return this.lstCellule       ;}
	public int           getNbCellules () { return this.lstCellule.size();}  
	public String        getNom        () { return this.nom              ;}

	/* ---------------------------------- */
	/*           Autres Méthodes          */
	/* ---------------------------------- */

	/**
	 * 
	 * @param c
	 */

	public void ajouterCellule(Cellule c)
	{
		if (c != null)
		{
			this.lstCellule.add(c);
			c.setEquipe(this);
		}
	}

	/**
	 * 
	 */

	public void supprimerTout()
	{ 
		this.lstCellule.removeAll(lstCellule);
		Cellule.reinitialiserCompteur();
	}

	/**
	 * 
	 * @param c
	 */

	public void retirerCellule(Cellule c)
	{

		List<Cellule> lstTemp = new ArrayList<Cellule>(this.lstCellule);
		
		for (Cellule cellule : this.lstCellule) 
		
			if (cellule == c)
				lstTemp.remove(c);

		this.lstCellule = lstTemp;
		
	}

	/**
	 * 
	 * @return
	 */

	public String afficherListeCellule()
	{

		String sRet = "";

		for (Cellule cellule : this.lstCellule) 
			sRet += cellule + "\n";

		return sRet;
	}

	/**
	 * 
	 */

	public String toString()
	{

		String sRet = "/*--------------------------*/\n"                + 
		              "/*" + String.format("%16s",  this.nom )  +
					         String.format("%10s", " ") + "*/\n" + 
					  "/*--------------------------*/\n";

		sRet +=   this.lstCellule.size()                        + " cellule"  + 
		       ( (this.lstCellule.size() > 1) ? "s"  : "")      + " présente" + 
			   ( (this.lstCellule.size() > 1) ? "nt" : "")      + " dans l'équipe.\n";

		for (Cellule c : lstCellule) 
			sRet += "\nCellule " + c.getNom() + " de niveau " + c.getNiveau() + " de type " + c.getCapacite();
	
		return sRet;
	}
}