package source;

import source.metier.*;
import source.metier.unites.*;

public class TestNiveau 
{
	public static void main(String[] args) 
	{
		Niveau n1 = new Niveau();


		System.out.println("/* ---------------------------------- */");
		System.out.println("/*       Test d'ajouts d'Équipe       */");
		System.out.println("/* ---------------------------------- */");

		n1.ajouterEquipe(new Equipe("Neutre") ); // Ajout
		n1.ajouterEquipe(new Equipe("Neutre") ); // Neutre existe déjà, non ajoutée.
		n1.ajouterEquipe(new Equipe("Joueur") ); // Ajout
		n1.ajouterEquipe(new Equipe("Rouge" ) ); // Ajout
		n1.ajouterEquipe(new Equipe("Rose"  ) ); // Ajout
		n1.ajouterEquipe(new Equipe("Orange") ); // Ajout

		System.out.println();

		n1.ajouterCellule("Neutre", Cellule.creerCellule(1, 3) ); // A
		n1.ajouterCellule("Neutre", Cellule.creerCellule(1, 3) ); // B
		n1.ajouterCellule("Neutre", Cellule.creerCellule(1, 3) ); // C
		n1.ajouterCellule("Neutre", Cellule.creerCellule(1, 3) ); // D
		System.out.println();

		n1.ajouterCellule("Joueur", CelluleSoin   .creerCellule(0, 40) ); // E
		n1.ajouterCellule("Joueur", CelluleAttaque.creerCellule(0, 20) ); // F
		n1.ajouterCellule("Joueur", Cellule       .creerCellule(0, 9 ) ); // G
		n1.ajouterCellule("Joueur", CelluleSoin   .creerCellule(0, 80) ); // H
		System.out.println();

		n1.ajouterCellule("Rouge",Cellule.creerCellule(6, 170) ); // I
		System.out.println();

		/* --- Test Unitaire des exceptions -- */
		/* --- Dans la méthode relierCellule -- */

		System.out.println();
		
		System.out.println("/* ---------------------------------- */");
		System.out.println("/*           Test de Liaison          */");
		System.out.println("/* ---------------------------------- */");
		System.out.println();

		n1.relierCellule('Z', 'E'); // La Cellule source n'existe pas
		n1.relierCellule('E', 'Z'); // La cellule de destination n'existe pas
		n1.relierCellule('Z', 'P'); // Les deux cellules n'existent pas 

		n1.relierCellule('A', 'E'); // La cellule source est neutre
		n1.relierCellule('A', 'A'); // Les deux cellules sont identiques

		n1.relierCellule('E', 'A'); // Liaison réussie.

		System.out.println(n1.getDetailsLiaisons() );
		
		/* --- Test Unitaire des exceptions ------ */
		/* --- Dans la méthode supprimerLiaison -- */

		System.out.println("/* ---------------------------------- */");
		System.out.println("/*         Test de Suppression        */");
		System.out.println("/* ---------------------------------- */");

		System.out.println();

		n1.supprimerLiaison('E', 'Z'); // La Cellule n°2 n'existe pas	
		n1.supprimerLiaison('Z', 'P'); // Les deux Cellules n'existent pas
		n1.supprimerLiaison('E', 'A'); // Suppression de la liaison
		n1.supprimerLiaison('E', 'A'); // La liaison n'existe plus.

		System.out.println(n1.getDetailsLiaisons() );
		 
		System.out.println("/* ----------------------------------*/");
		System.out.println("/*     Test avec ajouts complexes    */");
		System.out.println("/* ----------------------------------*/");
		System.out.println();

		n1.relierCellule('E', 'F'); // Ici F est à la fois source

		n1.relierCellule('F', 'G'); // Et destination.
		n1.relierCellule('G', 'F'); // Inversion du sens de la liaison
		n1.relierCellule('F', 'G'); // Inversion du sens de la liaison
		n1.relierCellule('G', 'F'); // Inversion du sens de la liaison

		n1.relierCellule('E', 'A');
		n1.relierCellule('A', 'E'); // Inversion impossible car 'A' est neutre.
		System.out.println(n1.getDetailsLiaisons() );
		

		n1.viderLiaison();

		System.out.println("/* ---------------------------------- */");
		System.out.println("/*     Test d'ajouts triangulaire     */");
		System.out.println("/* ---------------------------------- */");

		n1.relierCellule('E', 'F');
		n1.relierCellule('F', 'G');
		n1.relierCellule('G', 'E');
		n1.relierCellule('G', 'A');

		System.out.println(n1.getDetailsLiaisons() );


		System.out.println("/* ---------------------------------- */");
		System.out.println("/*     Test d'ajouts inter-équipes    */");
		System.out.println("/* ---------------------------------- */");

		n1.viderLiaison();

		n1.relierCellule('E', 'A');		
		n1.relierCellule('E', 'I');		
		n1.relierCellule('I', 'D');		
		n1.relierCellule('I', 'A');		
		n1.relierCellule('I', 'B');
		n1.relierCellule('I', 'B');
		n1.relierCellule('E', 'I');
		
		System.out.println(n1.getDetailsLiaisons() );
		
	}
	
}