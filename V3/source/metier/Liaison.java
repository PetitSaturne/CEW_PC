package source.metier;

import source.metier.unites.Cellule;

public class Liaison
{
	private Cellule celluleSource;
	private Cellule celluleDest;

	private int distance;

	public Liaison(Cellule celluleSource, Cellule celluleDest)
	{
		this.celluleSource = celluleSource;
		this.celluleDest   = celluleDest;
		this.distance      = 0;

	}

	/* ---------------------------------- */
	/*             Accesseurs             */
	/* ---------------------------------- */

	public Cellule getSource() { return this.celluleSource;}
	public Cellule getDest  () { return this.celluleDest  ;}

	/* ---------------------------------- */
	/*           Autres Méthodes          */
	/* ---------------------------------- */

	public void echanger()
	{
		Cellule cTemp;

		cTemp              = this.celluleDest;
		this.celluleDest   = this.celluleSource;
		this.celluleSource = cTemp;

	}
	
	public String toString()
	{
		return this.celluleSource.getNom()      + " de " + this.celluleSource.getEquipe().getNom() + 
			   " est la source d'un lien vers " + 
			   this.celluleDest  .getNom()      + " de " + this.celluleDest  .getEquipe().getNom() + "\n";

	}

}