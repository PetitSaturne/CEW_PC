package source;

import source.metier.*;
import source.metier.unites.*;

public class TestEquipe
{
	public static void main(String[] args) 
	{
		Equipe joueur, advRouge;
		joueur   = new Equipe("Neutre");
		advRouge = new Equipe("Rouge");

		//joueur.ajouterCellule(null  , 2, 20, joueur);
		//joueur.ajouterCellule("Soin", 2, 30, "Joueur");
		//joueur.ajouterCellule("Soin", 3, 40, "Joueur");
		//joueur.ajouterCellule(null  , 2, 20, "Joueur");

		//advRouge.ajouterCellule(null     , 2, 20, "Rouge");
		//advRouge.ajouterCellule("Attaque", 2, 30, "Rouge");
		//advRouge.ajouterCellule("Attaque", 3, 50, "Rouge");
		//advRouge.ajouterCellule("Bonus"  , 3, 20, "Rouge");


		System.out.println("---------------------------"  );
		System.out.println("  Affichage équipe Joueur"    );
		System.out.println("---------------------------\n");

		//joueur.afficherListeCellule();

		/*System.out.println("--------------------------"  );
		System.out.println("  Affichage équipe Rouge"     );
		System.out.println("--------------------------\n");

		advRouge.afficherListeCellule();*/

		
		System.out.println("------------------------------------");
		System.err.println("  Test avec des cellules invalides"  );
		System.out.println("------------------------------------");

		System.out.println("\n Test de la mise à niveau à partir des PV");

		//joueur  .viderListe();
		//advRouge.viderListe();

	

		/*joueur.ajouterCellule(null, 0, 30);
		joueur.ajouterCellule(null, 0, 40);
		joueur.ajouterCellule(null, 0, 70);
		joueur.ajouterCellule(null, 0, 10);
		joueur.ajouterCellule(null, 0, 120);
		joueur.ajouterCellule(null, 0, 160);*/


		System.out.println(joueur.afficherListeCellule() );

		System.out.println("\n Test d'ajout des PV en fonction du niveau");
		joueur.viderListe();

		//joueur.ajouterCellule(null, 1, 0, advRouge);
		//joueur.ajouterCellule(null, 7, 0, advRouge);

		/*joueur.ajouterCellule(null, 1, 20);

		
		joueur.ajouterCellule(null, 2, 5 );
		joueur.ajouterCellule(null, 2, 80);

		joueur.ajouterCellule(null, 3, 5  );
		joueur.ajouterCellule(null, 3, 120);

		joueur.ajouterCellule(null, 4, 10 );
		joueur.ajouterCellule(null, 4, 160);

		joueur.ajouterCellule(null, 5, 10 );
		joueur.ajouterCellule(null, 5, 170);

		joueur.ajouterCellule(null, 6, 10);
		joueur.ajouterCellule(null, 6, 350);*/

		System.out.println(joueur.afficherListeCellule() );

	}

}