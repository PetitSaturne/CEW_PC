package source.ihm;

import source.Controleur;

import java.awt.Toolkit;

import javax.swing.*;


public class FrameMenu extends JFrame
{
	Controleur     ctrl;
	PanelMenu panelMenu;

	public FrameMenu(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Organisms Battle");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 
		             Toolkit.getDefaultToolkit().getScreenSize().height);	

		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */

		this.panelMenu = new PanelMenu(ctrl);

		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */

		this.add(this.panelMenu);

		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}

	public void affichageCUI()
	{
		System.out.println(this.ctrl.getListeEquipes() );
	}
	
}