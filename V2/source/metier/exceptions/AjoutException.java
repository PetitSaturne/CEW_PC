package source.metier.exceptions;

public class AjoutException extends Exception
{
	public AjoutException(String nomErreur, String erreur)
	{
		super(String.format("%-25s",nomErreur) + " : " + erreur);
		
	}
}