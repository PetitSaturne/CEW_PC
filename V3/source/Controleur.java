package source;

import source.metier.*;
import source.ihm.*;

public class Controleur
{



	private FrameMenu ihm;
	private Niveau metier;

	public Controleur()
	{

		this.metier = new Niveau   ();
		this.ihm    = new FrameMenu(this);

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