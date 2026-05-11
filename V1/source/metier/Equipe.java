package source.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Equipe
{
	private List<Cellule>                   lstCellulesEquipes;
	private HashMap<Cellule,List<Liaison> > hmLiaisonsCellules;

	private String nomEquipe;

	public Equipe(String nomEquipe)
	{
		this.nomEquipe = nomEquipe;

		this.lstCellulesEquipes  = new ArrayList <Cellule>();
		this.hmLiaisonsCellules  = new HashMap   <Cellule,List<Liaison>> ();

	}

	public Equipe()
	{
		this("Joueur");
	}

	public Cellule getCellule (char nomCellule)
	{
		Cellule c = null;

		for (Cellule cellule : this.lstCellulesEquipes) 
		
			if (cellule.getNom() == nomCellule)
				c = cellule;

		return c;
	}

	public List<Cellule> getLstCellules() { return this.lstCellulesEquipes       ;}
	public int           getNbCellules () { return this.lstCellulesEquipes.size();}  
	public String        getEquipe     () { return this.nomEquipe;}


	public void ajouterCellule(String capacite, int niveau, int pv)
	{
		Cellule cTemp = Cellule.creerCellule(capacite, niveau, pv, this);

		if (cTemp != null)
			this.lstCellulesEquipes.add(cTemp);
	}

	public void viderListe()
	{ 
		this.lstCellulesEquipes.removeAll(lstCellulesEquipes);
		Cellule.reinitialiserCompteur();
	}

	public void retirerCellule(Cellule c)
	{

		List<Cellule> lstTemp = new ArrayList<Cellule>(this.lstCellulesEquipes);
		
		for (Cellule cellule : this.lstCellulesEquipes) 
		
			if (cellule == c)
				lstTemp.remove(c);

		this.lstCellulesEquipes = lstTemp;
		
	}


	public void associerLiaisonCellules()
	{
		List<Liaison> lstLiaisons = new ArrayList<Liaison>();

		for (Cellule cellule : lstCellulesEquipes) 
		{
			for (Cellule cLiaison : cellule.getLstLiaisons() )
				lstLiaisons.add(Liaison.creerLiaison(cellule, cLiaison ) );

			hmLiaisonsCellules.put(cellule, lstLiaisons);
		}
	}

	public String afficherListeCellule()
	{

		String sRet = "";

		for (Cellule cellule : this.lstCellulesEquipes) 
			sRet += cellule + "\n";

		return sRet;
	}

	public String toString()
	{
		return this.nomEquipe + "(" + this.lstCellulesEquipes.size() + ")";
	}
}