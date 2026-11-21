package source.ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;


import source.Controleur;

public class PanelJouer extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private MainFrame  frameMenu;



	private JButton btnCampagne;
	private JButton btnEdition ;
	private JButton btnRetour;
	
	public PanelJouer(Controleur ctrl, MainFrame frameMenu)
	{

		JPanel panelJeu;
		JPanel panelPrc;
		JPanel panelretour;

		this.ctrl      = ctrl     ;
		this.frameMenu = frameMenu;

		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */

		this.setLayout(new GridBagLayout(   ) );

		panelPrc    = new JPanel(new BorderLayout()    );
		panelJeu    = new JPanel(new GridLayout  (1,2) );
		panelretour = new JPanel();

		this.btnCampagne = new JButton("Mode Campagne" );
		this.btnEdition  = new JButton("Mode Édition"  );
		this.btnRetour   = new JButton("Retour au menu");

		this.btnCampagne.setName("campagne");
		this.btnEdition .setName("edition" );
		this.btnRetour  .setName("retour"  );
		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */

		panelJeu.add(this.btnCampagne);
		panelJeu.add(this.btnEdition );
		
		panelretour.add(this.btnRetour);

		panelPrc.add(panelJeu   , BorderLayout.CENTER);
		panelPrc.add(panelretour, BorderLayout.SOUTH );

		this.add(panelPrc, new GridBagConstraints() );

		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */

		this.btnRetour.addActionListener(this);


	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnRetour) 
			this.frameMenu.changerPanel(this, ( (JButton) e.getSource() ).getName() );
		
	}

	

}