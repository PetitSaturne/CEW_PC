package source.metier.unites;


public class CelluleAttaque extends Cellule
{

	public static CelluleAttaque creerCellule(int niveauActuel, int pv )
	{

		if (!Cellule.parametresValide(niveauActuel, pv) ) return null;
		return new CelluleAttaque( niveauActuel, pv );

	}

	private CelluleAttaque(int niveauActuel, int pv )
	{
		super( niveauActuel, pv );
		this.capacite = "Attaque";
	}
	
}