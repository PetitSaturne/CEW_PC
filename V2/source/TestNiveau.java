package source;

import source.metier.*;
import source.metier.unites.*;

public class TestNiveau 
{
	public static void main(String[] args) 
	{
		Niveau n1 = new Niveau();

		
		n1.ajouterCellule("Neutre", Cellule.creerCellule(1, 3) );
		n1.ajouterCellule("Neutre", Cellule.creerCellule(1, 3) );
		n1.ajouterCellule("Neutre", Cellule.creerCellule(1, 3) );
		n1.ajouterCellule("Neutre", Cellule.creerCellule(1, 3) );

		n1.ajouterCellule("Joueur", CelluleSoin.creerCellule(0, 40) );


		n1.ajouterCellule("Joueur", CelluleAttaque.creerCellule(0, 20) );
		n1.ajouterCellule("Joueur", Cellule       .creerCellule(0, 9 ) );
		n1.ajouterCellule("Joueur", CelluleSoin   .creerCellule(0, 80) );

		n1.ajouterCellule("Rouge",Cellule.creerCellule(6, 170) );

		/* --- Test Unitaire des exceptions -- */
		/* --- Dans la méthode lierCellule -- */

		System.out.println("/* ---------------------------------- */");
		System.out.println("/*           Test de Liaison          */");
		System.out.println("/* ---------------------------------- */");

		n1.lierCellule('Z', 'E'); // La Cellule source n'existe pas
		n1.lierCellule('E', 'Z'); // La cellule de destination n'existe pas
		n1.lierCellule('Z', 'P'); // Les deux cellules n'existent pas 

		n1.lierCellule('A', 'E'); // La cellule source est neutre
		n1.lierCellule('A', 'A'); // Les deux cellules sont identiques

		n1.lierCellule('E', 'A'); // Liaison réussie.

		System.out.println(n1.getLiaisons() );
		
		/* --- Test Unitaire des exceptions ------ */
		/* --- Dans la méthode supprimerLiaison -- */

		System.out.println("/* ---------------------------------- */");
		System.out.println("/*         Test de Suppression        */");
		System.out.println("/* ---------------------------------- */");

		n1.supprimerLiaison('E', 'Z'); // La Cellule n°2 n'existe pas
		n1.supprimerLiaison('Z', 'P'); // Les deux Cellules n'existent pas
		n1.supprimerLiaison('E', 'A'); // Suppression de la liaison
		n1.supprimerLiaison('E', 'A'); // La liaison n'existe plus.

		System.out.println(n1.getLiaisons() );
		 
		System.out.println("/* ----------------------------------*/");
		System.out.println("/*     Test avec ajouts Complexe   */");
		System.out.println("/* ----------------------------------*/");

		n1.lierCellule('E', 'F'); // Ici F est à la fois source
		n1.lierCellule('F', 'G'); // Et destination.
		System.out.println(n1.getLiaisons() );

		n1.lierCellule('G', 'F'); // Inversion du sens de la liaison
		System.out.println(n1.getLiaisons() );

		n1.lierCellule('E', 'A');
		n1.lierCellule('A', 'E'); // Inversion impossible car 'A' est neutre.
		
	}
	
}

/*try 
{
	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	
} catch (Exception e) {e.printStackTrace();}*/


/** TO DO :
	 * 
	 * Gérer les cellules lorsque que plusieurs liaison leurs sont assignés.
	 * 
	 * Réutiliser la classe Liaison pour permettre ce genre de lien.
	 * 
	 */