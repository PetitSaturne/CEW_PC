package source;

import source.metier.*;
import source.ihm.*;

public class Controleur
{

	private Vue ihm;

	public Controleur()
	{

		this.ihm = new Vue(this);

	}

	public static void main(String[] args) 
	{
		new Controleur();
	}
}