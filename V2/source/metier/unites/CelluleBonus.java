package source.metier.unites;

public class CelluleBonus extends Cellule
{

	public static CelluleBonus creerCellule(int niveauActuel, int pv)
	{

		if (!Cellule.parametresValide(niveauActuel, pv)) return null;
		return new CelluleBonus( niveauActuel, pv);

	}

	private CelluleBonus(int niveauActuel, int pv)
	{
		super( niveauActuel, pv);

		this.capacite = "Bonus";
		this.nbTentacules++;
	}
	
}