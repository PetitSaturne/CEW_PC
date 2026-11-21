package source;

import source.metier.*;
import source.ihm.*;

public class Controleur
{



	private MainFrame ihm;
	private Niveau metier;

	public Controleur()
	{

		this.metier = new Niveau   ();
		this.ihm    = new MainFrame(this);

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