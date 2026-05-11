package source.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import source.metier.unites.*;

public class Equipe
{
	private List<Cellule>                   lstCellule;
	private HashMap<Cellule,List<Liaison> > hmLiaisonsCellules;

	private String nom;

	public Equipe(String nom)
	{
		this.nom = nom;

		this.lstCellule  = new ArrayList <Cellule>();
		this.hmLiaisonsCellules  = new HashMap <Cellule,List<Liaison>> ();

	}

	public Equipe()
	{
		this("Neutre");
	}

	public Cellule getCellule (char nomCellule)
	{
		Cellule c = null;

		for (Cellule cellule : this.lstCellule) 
		
			if (cellule.getNom() == nomCellule)
				c = cellule;

		return c;
	}

	public List<Cellule> getLstCellules() { return this.lstCellule       ;}
	public int           getNbCellules () { return this.lstCellule.size();}  
	public String        getNom        () { return this.nom        ;}


	public void ajouterCellule(Cellule c)
	{
	
		if (c != null)
		{
			this.lstCellule.add(c);
			c.setEquipe(this);
		}
	}

	public void viderListe()
	{ 
		this.lstCellule.removeAll(lstCellule);
		Cellule.reinitialiserCompteur();
	}

	public void retirerCellule(Cellule c)
	{

		List<Cellule> lstTemp = new ArrayList<Cellule>(this.lstCellule);
		
		for (Cellule cellule : this.lstCellule) 
		
			if (cellule == c)
				lstTemp.remove(c);

		this.lstCellule = lstTemp;
		
	}


	public void associerLiaisonCellules()
	{
		List<Liaison> lstLiaisons = new ArrayList<Liaison>();

		for (Cellule cellule : lstCellule) 
		{
			for (Cellule cLiaison : cellule.getLstLiaisons() )
				lstLiaisons.add(Liaison.creerLiaison(cellule, cLiaison ) );

			hmLiaisonsCellules.put(cellule, lstLiaisons);
		}	
	}

	public String afficherListeCellule()
	{

		String sRet = "";

		for (Cellule cellule : this.lstCellule) 
			sRet += cellule + "\n";

		return sRet;
	}

	public String toString()
	{

		boolean possedeLiaisons = false;

		String sRet = "/*--------------------------*/\n"                + 
		              "/*" + String.format("%16s",  this.nom )  +
					         String.format("%10s", " ") + "*/\n" + 
					  "/*--------------------------*/\n";

		sRet +=   this.lstCellule.size()                        + " cellule"  + 
		       ( (this.lstCellule.size() > 1) ? "s"  : "")      + " présente" + 
			   ( (this.lstCellule.size() > 1) ? "nt" : "")      + " dans l'équipe.\n";

		for (Cellule c : lstCellule) 
			sRet += "\nCellule " + c.getNom() + " de niveau " + c.getNiveau() + " de type " + c.getCapacite();
			
		for (Cellule c : lstCellule) 
			if (!c.getDetailLiaisons().equals("") ) possedeLiaisons = true;
	
		if (possedeLiaisons)
			sRet += "\n\n/*------------Liaisons------------*/\n"   ;

		for (Cellule c : lstCellule) 
			if (!c.getDetailLiaisons().equals("") )

				sRet += c.getDetailLiaisons();

		return sRet;
	}
}