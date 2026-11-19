package source;

import source.metier.unites.*;

public class TestCellule
{
	public static void main(String[] args) 
	{
		Cellule cTemp;

		System.out.println("/* ---------------------------------- */");
		System.out.println("/*    Création de cellules normales   */");
		System.out.println("/* ---------------------------------- */");

		cTemp = Cellule.creerCellule(0, 0);
		System.out.println( "Test 1 : " + ( (cTemp == null) ? "OK, Les deux paramètres sont nuls, aucune Cellule créée" : "Erreur" ) );

		cTemp = Cellule.creerCellule(1, 0);
		System.out.println
		( "Test 2 : " + ( (cTemp.getPv() != 0) ? "OK, les PV ont été calibré   : rentré 0  , sortie " + cTemp.getPv() : "Erreur" ) );

		cTemp = Cellule.creerCellule(0, 1);
		System.out.println
		("Test 3 : " + ( (cTemp != null) ? "OK, le niveau a été calibré  : rentré 0  , sortie " + cTemp.getNiveau() : "Erreur" ) );

		cTemp = Cellule.creerCellule(1, 1);
		System.out.println
		("Test 4 : " + ( (cTemp != null) ? "OK, le niveau n'a pas changé : rentré 1  , sortie " + cTemp.getNiveau() : "Erreur" ) );

		cTemp = Cellule.creerCellule(1, 120);
		System.out.println
		("Test 5 : " + ( (cTemp.getPv() != 120) ? "OK, les PV ont été calibré   : rentré 120, sortie " + cTemp.getPv() : "Erreur " ) );

		cTemp = Cellule.creerCellule(0, 120);
		System.out.println
		("Test 6 : " + ( (cTemp.getNiveau() != 0) ? "OK, le niveau a été calibré  : rentré 0  , sortie " + cTemp.getNiveau() : "Erreur " ) );

	 	System.out.println();
		System.out.println("/* ----------------------------------- *");
		System.out.println("/*    Création de cellules spéciales   *");
		System.out.println("/* ----------------------------------- *");


		cTemp = CelluleAttaque.creerCellule(0, 120);
		System.out.println("Test 7  : " + ( (cTemp instanceof CelluleAttaque) ? "OK, Cellule " + cTemp.getCapacite() + " ajoutée"  : "Erreur " ) );

		cTemp = CelluleDefense.creerCellule(0, 120);
		System.out.println("Test 8  : " + ( (cTemp instanceof CelluleDefense) ? "OK, Cellule " + cTemp.getCapacite() + " ajoutée"  : "Erreur " ) );

		cTemp = CelluleSoin.creerCellule(0, 120);
		System.out.println("Test 9  : " + ( (cTemp instanceof CelluleSoin) ? "OK, Cellule "    + cTemp.getCapacite() + " ajoutée"  : "Erreur " ) );

		cTemp = CelluleBonus.creerCellule(0, 120);
		System.out.println("Test 10 : " + ( (cTemp instanceof CelluleBonus) ? "OK, Cellule "   + cTemp.getCapacite() + " ajoutée"  : "Erreur " ) );


	}



	

	/*------------------------*/
	/*
	
		Fonctionnalité testées : 

		- Test de création des Cellules valides        OK
		- Test de création des Cellules invalides      OK
	*/

	
	
}