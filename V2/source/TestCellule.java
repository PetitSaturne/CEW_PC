package source;

import java.util.ArrayList;
import java.util.List;

import source.metier.*;
import source.metier.unites.*;

public class TestCellule
{
	private static List<Cellule> ensCellules = new ArrayList<Cellule>();
	private static List<Liaison> lstLiaisons = new ArrayList<Liaison>();

	public static void main(String[] args) 
	{

		System.out.println("/* ---------------------------------- */");
		System.out.println("/*    Création de cellules normales   */");
		System.out.println("/* ---------------------------------- */");
		
		TestCellule.ensCellules.add(Cellule.creerCellule(1,10  ) );
		TestCellule.ensCellules.add(Cellule.creerCellule(0,0   ) );
		TestCellule.ensCellules.add(Cellule.creerCellule(2,0   ) );
		TestCellule.ensCellules.add(Cellule.creerCellule(0,40  ) );
		TestCellule.ensCellules.add(Cellule.creerCellule(2,30  ) );
		TestCellule.ensCellules.add(Cellule.creerCellule(5,140 ) );

		TestCellule.nettoyer();
		TestCellule.afficherListeCellule();
		
	 	System.out.println();
		System.out.println("/* ----------------------------------- *");
		System.out.println("/*    Création de cellules spéciales   *");
		System.out.println("/* ----------------------------------- *");
		TestCellule.viderListe();
		

		//TestCellule.ensCellules.add(CelluleAttaque.creerCellule(1,120 , null) );
		//TestCellule.ensCellules.add(CelluleAttaque.creerCellule(1,120 , null) );
		//TestCellule.ensCellules.add(CelluleDefense.creerCellule(1,120 , null) );
		//TestCellule.ensCellules.add(CelluleSoin   .creerCellule(1,120 , null) );
		TestCellule.ensCellules.add(CelluleBonus  .creerCellule(0,120 ) );
		TestCellule.ensCellules.add(CelluleBonus  .creerCellule(2,30  ) );
		TestCellule.ensCellules.add(CelluleBonus  .creerCellule(5,160 ) );
		TestCellule.ensCellules.add(CelluleBonus  .creerCellule(6,0   ) );

		TestCellule.nettoyer();
		TestCellule.afficherListeCellule();



	}

	public static Cellule getCellule (char nomCellule)
	{
		Cellule c = null;

		for (Cellule cellule : TestCellule.ensCellules) 
		
			if (cellule.getNom() == nomCellule)
				c = cellule;
	
		if (c == null)
		{
			System.out.println("\nÉlément introuvable, dernier élement de la liste attribué");
			c = TestCellule.ensCellules.getLast();
		} 

		return c;
	}

	public static void nettoyer()
	{
		ArrayList<Cellule> lstTemp = new ArrayList<Cellule>(ensCellules);

		for (Cellule cellule : ensCellules) 
			if (cellule == null) 
				lstTemp.remove(cellule);

		TestCellule.ensCellules = lstTemp;
		
	}

	public static void viderListe()
	{
		TestCellule.ensCellules.clear();
		Cellule.reinitialiserCompteur();
	}



	public static void ajouterLiaison(char cSource, char cDest)
	{

		Cellule source, dest;

		source = null;
		dest   = null;

		for (Cellule cellule : ensCellules) 
		{
			if (cellule.getNom() == cSource) source = cellule;
			if (cellule.getNom() == cDest  ) dest   = cellule;
		}

		boolean existe = false;

		if (source != null && dest != null)
		{

			for (Liaison liaison : TestCellule.lstLiaisons)
			{
				if (dest   == liaison.getDest() && source == liaison.getSource() )  existe = true;
				if (source == liaison.getDest() && dest   == liaison.getSource() ) 
				{
					liaison.echanger();
					existe = true;
				}
					
				/*if (source.estEnLienAvec(liaison.getDest  ().getNom() ) || 
				    dest.estEnLienAvec  (liaison.getSource().getNom() ) ) existe = true;*/
			}
	
			if (!existe) 
			{
				Liaison l = Liaison.creerLiaison(source, dest);
				if (l != null)
				lstLiaisons.add(l);
			}
		}

	}

	public static void supprimerLiaison(char cNom1, char cNom2)
	{

		Cellule c1, c2;

		c1 = null;
		c2 = null;

		for (Cellule cellule : ensCellules) 
		{
			if (cellule.getNom() == cNom1) c1= cellule;
			if (cellule.getNom() == cNom2) c2= cellule;
		}
		
		List<Liaison> lstTemp = new ArrayList<Liaison>(TestCellule.lstLiaisons);

		c1.supprimerLiaison(c2);
		c2.supprimerLiaison(c1);

		for (Liaison l : lstLiaisons) 
		
			if (l.getSource() == c1 && l.getDest  () == c2  )
				lstTemp.remove(l);
		
		TestCellule.lstLiaisons = lstTemp;

	}

	public static void afficherLiaison(char nomCellule)
	{
		Cellule cTemp = null;

		System.out.println();
		for (int cpt = 0; cpt < TestCellule.ensCellules.size() ; cpt++) 
			if (nomCellule ==   TestCellule.ensCellules.get(cpt).getNom() )
			
				cTemp = TestCellule.ensCellules.get(cpt);

		System.out.println("Liaisons de " + cTemp.getNom() + " --> " + cTemp.getNbLiaisonsActive() + "/" + cTemp.getNbTentacules() );

		for (Cellule cellule : cTemp.getLstLiaisons() )
			System.out.println(cellule.getNom() );
			
		
	}

	public static void afficherListeLiaisons()
	{
		for (Liaison liaison : TestCellule.lstLiaisons) 
			System.out.println(liaison);
	}

	public static void afficherListeCellule()
	{
		for (Cellule cellule : TestCellule.ensCellules) 
			System.out.println(cellule);
	}

	/*------------------------*/
	/*
	
		Fonctionnalité testées : 

		- Test de création des Cellules valides        OK
		- Test de création des Cellules invalides      OK
		- Test des liaisons simples                    OK
		- Test des liaisons multiples                  OK
		- Test des liaisons bi-directionnelles         OK
		- Test d'auto Liaison                          OK
		- Test des liaisons si trop de liaisons        OK
		- Getters                                      OK
	  
	 
	
	
	*/

	public static void afficherEtape(int etape)
	{
		switch (etape) 
		{
			case 1 -> 
			{
				TestCellule.afficherLiaison('A');
				TestCellule.afficherLiaison('B');

				System.out.println("\nNombre de liaisons total : " + 
				   TestCellule.lstLiaisons.size()                    +
				( (TestCellule.lstLiaisons.size() == 1) ? "\tOK" : "\tErreur" ) + "\n") ;

				System.out.println("Nombre de liaisons sur A : "          + 
				   TestCellule.getCellule('A').getNbLiaisonsActive() + 
				( (TestCellule.getCellule('A').getNbLiaisonsActive() == 1 ) ? "\tOK" : "\tErreur" ) ); 

				System.out.println("Nombre de liaisons sur B : "          + 
				   TestCellule.getCellule('B').getNbLiaisonsActive() +
				( (TestCellule.getCellule('B').getNbLiaisonsActive() == 1 ) ? "\tOK" : "\tErreur"  ) );
				
				System.out.println("\nListe des liaisons : \n");
				TestCellule.afficherListeLiaisons();
			}

			case 2 ->
			{
				TestCellule.afficherLiaison('A');
				TestCellule.afficherLiaison('B');
				TestCellule.afficherLiaison('C');

				System.out.println("\nNombre de liaisons total : " + 
				   TestCellule.lstLiaisons.size()                    + 
				( (TestCellule.lstLiaisons.size() == 2) ? "\tOK" : "\tErreur " ) + "\n" ) ;

				System.out.println("Nombre de liaisons sur A : "          + 
				   TestCellule.getCellule('A').getNbLiaisonsActive() + 
				( (TestCellule.getCellule('A').getNbLiaisonsActive() == 1 ) ? "\tOK" : "\tErreur" ) ); 

				System.out.println("Nombre de liaisons sur B : "          + 
				   TestCellule.getCellule('B').getNbLiaisonsActive() +
				( (TestCellule.getCellule('B').getNbLiaisonsActive() == 2 ) ? "\tOK" : "\tErreur --> " ) );

				System.out.println("Nombre de liaisons sur C : "           +
				   TestCellule.getCellule('C').getNbLiaisonsActive() +
				( (TestCellule.getCellule('C').getNbLiaisonsActive() == 1 ) ? "\tOK" : "\tErreur --> " ) );

				System.out.println("\nListe des liaisons : \n");
				TestCellule.afficherListeLiaisons();

			}

			case 3 ->
			{
				System.out.println("Suppression Lien entre B et C ");
				System.out.println("\nNombre de liaisons restantes : " + 
				   TestCellule.lstLiaisons.size()                      + 
				( (TestCellule.lstLiaisons.size() == 1) ? "\tOK" : "Erreur -->" ) );

				TestCellule.afficherLiaison('A');
				TestCellule.afficherLiaison('B');
				TestCellule.afficherLiaison('C');

				System.out.println();

			}

			case 4 ->
			{
				System.out.println("Ajout d'un Lien déjà Existant");

				System.out.println("\nNombre de liaisons : " + 
				   TestCellule.lstLiaisons.size()            + 
				( (TestCellule.lstLiaisons.size() == 1) ? "\tOK" : "Erreur -->" ) );

				System.out.println("Liste des liaisons :\n");
				TestCellule.afficherListeLiaisons();
			}

			case 5 ->
			{
				System.out.println("\n*************************************");
				System.out.println("  Tentative d'ajout en sens inverse");
				System.out.println("*************************************");

				
				System.out.println("\nListe des liaisons :\n");
				TestCellule.afficherListeLiaisons();
				
				System.out.println("\n\nNombre de liaisons       : "   + 
				   TestCellule.lstLiaisons.size()            + 
				( (TestCellule.lstLiaisons.size() == 1) ? "\tOK" : "Erreur -->" ) );

				System.out.println("\nNombre de liaisons sur A : "          + 
				   TestCellule.getCellule('A').getNbLiaisonsActive() + 
				( (TestCellule.getCellule('A').getNbLiaisonsActive() == 1 ) ? "\tOK" : "\tErreur" ) ); 

				System.out.println("Nombre de liaisons sur B : "          + 
				   TestCellule.getCellule('B').getNbLiaisonsActive() + 
				( (TestCellule.getCellule('B').getNbLiaisonsActive() == 1 ) ? "\tOK" : "\tErreur" ) ); 

			}

			case 6 ->
			{
				System.out.println("Ajout d'une liaison sur C et C");
				System.out.println("\nNombre de liaisons       : "   + 
				   TestCellule.lstLiaisons.size()                      + 
				( (TestCellule.lstLiaisons.size() == 1) ? "\tOK" : "\tErreur" ) );

				System.out.println("Nombre de liaisons sur C : "          + 
				   TestCellule.getCellule('C').getNbLiaisonsActive() + 
				( (TestCellule.getCellule('C').getNbLiaisonsActive() == 0 ) ? "\tOK" : "\tErreur" ) ); 
			}

			case 7 ->
			{
				System.out.println("Ajout de liaisons jusqu'à atteindre la capacité maximale de la cellule : ");
				System.out.println("Liste des liaisons courantes avant ajout: " + lstLiaisons.size() + "\n");
				TestCellule.afficherListeLiaisons();

				TestCellule.ajouterLiaison('A', 'C');

				/*System.out.println("Ajout de C : " + ensCellules.get(2).estEnLienAvec('A') + 
				( (ensCellules.get(2).estEnLienAvec('A') ) ? "\t\tOK" : "\t\tErreur"));

				System.out.println("Liste des liaisons courantes : " + lstLiaisons.size() + "\n");

				TestCellule.afficherListeLiaisons();
				
				System.out.println("\nNombre de liaisons sur A : "            + 
				   TestCellule.getCellule('A').getNbLiaisonsActive() + 
				( (TestCellule.getCellule('A').getNbLiaisonsActive() == 2 ) ? "\tOK" : "\tErreur" ) ); 
				
				
				TestCellule.ajouterLiaison('A', 'D');

				System.out.println("Ajout de D : " + 
				  TestCellule.getCellule('C').estEnLienAvec('A') + 
				((TestCellule.getCellule('C').estEnLienAvec('A') ) ? "\t\tOK" : "\t\tErreur") + "\n");

				TestCellule.afficherListeLiaisons();
			
			}

			case 8 ->
			{
				Cellule cTest;

				cTest = TestCellule.getCellule('A');
				System.out.println("Obtention de A : " + ( (cTest != null) ? "OK" : "Erreur" ) );

				cTest = TestCellule.getCellule('D');
				System.out.println("Obtention de D : " + ( (cTest != null) ? "OK" : "Erreur" ) );

				cTest = TestCellule.getCellule('Z');
				System.out.println("Obtention de Z : " + ( (cTest != null) ? "OK" : "Erreur" ) );
				
				*/


			}
		}
	}
}