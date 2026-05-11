package source.metier;

// Classe facultative si la fonction de liaison peut être faite dans la classe Cellule

public class Liaison
{
	private Cellule celluleSource;
	private Cellule celluleDest;

	private int distance;

	public static Liaison creerLiaison(Cellule celluleSource, Cellule celluleDest)
	{
		if (celluleSource == null || celluleDest == null) return null;
		if (celluleSource == celluleDest)                 return null;

		return new Liaison(celluleSource, celluleDest);

	}

	private Liaison(Cellule celluleSource, Cellule celluleDest)
	{
		this.celluleSource = celluleSource;
		this.celluleDest   = celluleDest;
		this.distance      = 0;

		this.celluleDest  .creerLiaison(celluleSource);
		this.celluleSource.creerLiaison(celluleDest  );
	}

	public Cellule getSource() { return this.celluleSource;}
	public Cellule getDest  () { return this.celluleDest  ;}

	public void supprimerLiaison(Cellule celluleSource, Cellule celluleDest)
	{
		celluleDest  .supprimerLiaison(celluleSource);
		celluleSource.supprimerLiaison(celluleDest);
	}


	public void echanger()
	{
		Cellule cTemp;

		cTemp              = this.celluleDest;
		this.celluleDest   = this.celluleSource;
		this.celluleSource = cTemp;
	}
	
	public String toString()
	{
		return "Liaison de " + this.celluleSource.getNom() + " vers " + this.celluleDest.getNom();
	}

}