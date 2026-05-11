package source.metier;

import source.metier.unites.*;

import java.util.List;
import java.util.ArrayList;

import source.metier.exceptions.*;

public class Niveau
{
	private Equipe[]      tabEquipes;
	private List<Liaison> lstLiaisons;

	public Niveau()
	{
		this.instancierEquipe();

		this.lstLiaisons = new ArrayList<Liaison>();
	}

	public void ajouterCellule(String nomEquipe, Cellule c)
	{
		if (c != null)

			for (int cpt = 0; cpt < tabEquipes.length; cpt++) 
				if (tabEquipes[cpt].getNom().equals(nomEquipe) )

					tabEquipes[cpt].ajouterCellule(c);
		
	}

	public void lierCellule(char de, char vers)
	{
		Cellule celSource, celDest;

		celSource = celDest = null;

		for (Equipe equipe : tabEquipes) 

			for (Cellule c : equipe.getLstCellules() ) 
			{
				if ( c.getNom() == de   ) celSource = c;
				if ( c.getNom() == vers ) celDest   = c;
			}

		try 
		{

			switch (this.gererException(celSource, celDest) ) 
			{
				case 1 -> 
				throw new LiaisonException("BothNullElementsException",
				"Les cellules source et de destination ne sont pas définies." );
				case 2 -> throw new LiaisonException("MissingFirstCellException", "La cellule source n'est pas définie.");
				case 3 -> 
				throw new LiaisonException("MissingLastCellException" ,"La cellule de destination n'est pas définie.");
				case 4 -> 
				throw new LiaisonException
				("IdenticCellException", "La cellule source est identique à la cellule de destination.");
					
			}

			if (celSource.getEquipe().getNom().equals("Neutre") ) 
				throw new LiaisonException("NeutralSourceException", "La cellule source n'appartient à aucune équipe.");

			if (celSource != celDest )
			{
				// S'il y a déjà un lien entre les deux cellule, le lien change de sens...

				Liaison lTemp = null;
				
				for (Liaison l : lstLiaisons) 
				{
					if (celSource == l.getDest() && celDest == l.getSource() )
					{
						lTemp = l;
						// ...Uniquement si la cellule destination n'est pas Neutre
						if ( celDest.getEquipe().getNom().equals("Neutre") ) 
							throw new LiaisonException("NeutralSourceException", "La cellule source n'appartient à aucune équipe.");
						
						l.echanger();
						System.out.println("Direction du lien modifié");
					}					
				}

				if (lTemp == null)
				{
					this.lstLiaisons.add(new Liaison(celSource, celDest) );
					System.out.println("Lien crée");
				}
			}	
			
		} 
		catch (Exception ex) 
		{	
			System.out.println(ex.getMessage() );
		}
	}
			

	public void supprimerLiaison(char nom1, char nom2)
	{

		Cellule cel1, cel2;

		cel1 = cel2 = null;

		for (Equipe equipe : tabEquipes) 
			for (Cellule c : equipe.getLstCellules() ) 
			{
				if (c.getNom() == nom1 || c.getNom() == nom2)
				{
					if (cel1 == null) cel1 = c;
					else              cel2 = c;
					
				}
			}

		try 
		{

			switch (this.gererException(cel1, cel2) ) 
			{
				case 1 -> 
				throw new LiaisonException("BothNullElementsException",
				"Les cellules n°1 et 2 ne sont pas définies." );
				case 3 -> 
				throw new LiaisonException("MissingLastCellException" ,"La deuxième cellule n'est pas définie.");
				case 4 -> 
				throw new LiaisonException
				("IdenticCellException", "La cellule source est la même que la cellule de destination.");
			}

			Liaison lTemp = null;
			ArrayList<Liaison> lstTemp = new ArrayList<Liaison>();

			for (Liaison l : lstLiaisons) 
			{

				if (cel1 == l.getSource() && cel2 == l.getDest() ||
			        cel1 == l.getDest  () && cel2 == l.getSource() )
				{
					lTemp = l;
					lstTemp.remove(l);
					System.out.println("Lien supprimé");

				}
			}

			this.lstLiaisons = lstTemp;

			if (lTemp == null) 
				throw new LiaisonException("NoLinkToCellException", "Il n'existe pas de lien entre les deux cellules.");

		} 

		catch (Exception ex) 
		{
			System.out.println(ex.getMessage() );
		}
	}

	public String toString()
	{
		String sRet = "";

		for (int cpt = 0; cpt < tabEquipes.length; cpt++) 
		{
			if (!tabEquipes[cpt].getLstCellules().isEmpty() )
				sRet += tabEquipes[cpt].toString() + "\n\n";
		}

		sRet += this.getLiaisons();

		return sRet;
	}
	
	private void instancierEquipe()
	{
		
		this.tabEquipes = new Equipe[5];

		this.tabEquipes[0] = new Equipe();                   // Equipe Neutre
		this.tabEquipes[1] = new Equipe("Joueur");
		this.tabEquipes[2] = new Equipe("Rouge" );
		this.tabEquipes[3] = new Equipe("Rose"  );
		this.tabEquipes[4] = new Equipe("Orange");

	}

	private int gererException(Cellule c1, Cellule c2)
	{

		if (c1 == null && c2 == null) return 1; // Les deux Cellules sont null
		if (c1 == null)               return 2; // La Cellule 1 est null
		if (c2 == null)               return 3; // La Cellule 2 est null
		if (c1 == c2)                 return 4; // La Cellule 1 et 2 sont identiques.
		
		return 0; // Il n'y a pas d'erreur
	}

	public String getLiaisons()
	{
		String sRet = "";

		if (!lstLiaisons.isEmpty() )
		{
			sRet += "\n/*----Lien présents entre toutes les cellules-----*/\n\n";
	
			for (Liaison l : lstLiaisons) 
				sRet += l;
		}

		return sRet;

	}

}