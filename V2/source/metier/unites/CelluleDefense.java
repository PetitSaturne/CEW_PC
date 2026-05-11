package source.metier.unites;

public class CelluleDefense extends Cellule
{

	public static CelluleDefense creerCellule(int niveauActuel, int pv)
	{

		if (!Cellule.parametresValide(niveauActuel, pv) ) return null;
		return new CelluleDefense( niveauActuel, pv);

	}

	private CelluleDefense(int niveauActuel, int pv)
	{
		super( niveauActuel, pv);
		this.capacite = "Défense";
	}
	
}