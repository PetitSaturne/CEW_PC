package source.metier.unites;



public class CelluleSoin extends Cellule
{

	public static CelluleSoin creerCellule(int niveauActuel, int pv)
	{

		if (!Cellule.parametresValide(niveauActuel, pv)) return null;
		return new CelluleSoin( niveauActuel, pv);

	}

	private CelluleSoin(int niveauActuel, int pv)
	{
		super( niveauActuel, pv);
		this.capacite = "Soin";
		
	}

	

}