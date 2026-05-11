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
				case 3 -> 
				throw new LiaisonException("MissingLastCellException" ,"La deuxième cellule n'est pas définie.");
				case 4 -> 
				throw new LiaisonException
				("IdenticCellException", "La cellule source est la même que la cellule de destination.");
					
			}

			if (celSource.getEquipe().getNom().equals("Neutre") ) 
				throw new LiaisonException("NeutralSourceException", "La cellule source n'appartient à aucune équipe.");

			if (celSource != celDest )
			{
				// S'il y a déjà un lien entre les deux cellule, le lien change de sens...
				if (celSource.getLiaison(celDest.getNom() ) == null)
				{

					celSource.setSource   (true);
					celSource.creerLiaison(celDest);
					System.out.println("Lien crée");
				}	
				
				else
				{
					// ...Uniquement si la cellule destination n'est pas une cellule Neutre
					if (celDest.getEquipe().getNom().equals("Neutre") ) 
						throw new LiaisonException("NeutralSourceException", "La cellule source n'appartient à aucune équipe.");
					
					celSource.setSource(false);
					celDest  .setSource(true );
					System.out.println("Direction du lien modifié");
					
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
					else 
					{
						System.out.println(cel1.getNom() );
						cel2 = c;
					}
				}
			}

		try 
		{

			switch (this.gererException(cel1, cel2) ) 
			{
				case 1 -> 
				throw new LiaisonException("BothNullElementsException",
				"Les cellules n°1 et 2 ne sont pas définies." );
				case 2 -> 
				throw new LiaisonException("MissingFirstCellException","La première cellule n'est pas définie.");
				case 3 -> 
				throw new LiaisonException("MissingLastCellException" ,"La deuxième cellule n'est pas définie.");
				case 4 -> 
				throw new LiaisonException
				("IdenticCellException", "La cellule source est la même que la cellule de destination.");
			}

			if (cel1.getLiaison(cel2.getNom() ) == null) 
				throw new LiaisonException("NoLinkToCellException", "Il n'existe pas de lien entre les deux cellules.");

			cel1.supprimerLiaison(cel2);
			cel2.supprimerLiaison(cel1);

			System.out.println("Lien supprimé");

			
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
			if (tabEquipes[cpt].getLstCellules().size() > 0)
				sRet += tabEquipes[cpt].toString() + "\n\n";
		}

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
	

}