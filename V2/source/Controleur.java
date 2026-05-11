package source;

import source.metier.*;
import source.ihm.*;

public class Controleur
{

	/* TO DO :  

		1) Gérer le jeu avec plusieurs équipes                     <-- Now
		2) Interface IHM correct pour pouvoir lancer le jeu	
		3) gérer l'affichage et le placement des cellules
		4) gérer l'interaction entre les cellules
		5) Gérer les évènements en temps réel



		0.1 Finir les commentaire sur les autres classes

		Tes

	*/

	private Vue ihm;
	private Niveau metier;

	public Controleur()
	{

		this.metier = new Niveau();
		this.ihm    = new Vue(this);

	}

	public String getListeEquipes()
	{
		return this.metier.toString();
	}

	public static void main(String[] args) 
	{
		new Controleur();
	}
}