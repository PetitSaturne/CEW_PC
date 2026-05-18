package source.ihm;

import source.Controleur;

public class Vue
{
	Controleur ctrl;

	public Vue(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.ihmCUI();
	}

	public void ihmCUI()
	{
		System.out.println(this.ctrl.getListeEquipes() );
	}
	
}