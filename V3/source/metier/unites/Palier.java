package source.metier.unites;

public enum Palier
{
	UP_NIVEAU_2 (15 ),
	UP_NIVEAU_3 (40 ),
	UP_NIVEAU_4 (80 ),
	UP_NIVEAU_5 (120),
	UP_NIVEAU_6 (160),

	DW_NIVEAU_1 (5  ),
	DW_NIVEAU_2 (30 ),
	DW_NIVEAU_3 (60 ),
	DW_NIVEAU_4 (100),
	DW_NIVEAU_5 (140);
	
	private int valeur;

	private Palier(int valeur)
	{
		this.valeur = valeur;
	}

	public int getValeur() { return this.valeur;}
	
}