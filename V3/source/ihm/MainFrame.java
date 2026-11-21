package source.ihm;

import source.Controleur;

import java.awt.Toolkit;

import javax.swing.*;


public class MainFrame extends JFrame
{
	private Controleur ctrl;
	private JPanel     panelActuel;


	public MainFrame(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle        ("Organisms Battle"   );
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setSize(400,400);

		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, 
		             Toolkit.getDefaultToolkit().getScreenSize().height);	

		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */

		this.panelActuel = new PanelMenu(ctrl,this);

		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */

		this.add(this.panelActuel);

		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */

		this.setVisible              (true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*public void affichageCUI()
	{
		System.out.println(this.ctrl.getListeEquipes() );
	}*/

	public boolean changerPanel(JPanel panelAppelant, String action)
	{
		if (panelAppelant == null) return false;

		this.remove(this.panelActuel);

		if (panelAppelant instanceof PanelJouer)
		{
			if (action.equals("retour") ) this.panelActuel = new PanelMenu(ctrl, this) ;
		}

		if (panelAppelant instanceof PanelMenu)
		{
			if (action.equals("jouer") ) this.panelActuel = new PanelJouer(ctrl, this) ;
		}

		this.add(this.panelActuel);
		this.majIHM();

		return true;

	}

	public void majIHM()
	{
		this.repaint();
		this.revalidate();
	}
	
}