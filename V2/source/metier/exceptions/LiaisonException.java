package source.metier.exceptions;

public class LiaisonException extends Exception
{
	public LiaisonException(String nomErreur, String erreur)
	{
		super(String.format("%-25s",nomErreur) + " : " + erreur);
		
	}
}