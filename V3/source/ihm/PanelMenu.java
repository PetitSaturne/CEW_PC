package source.ihm;

import source.Controleur;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelMenu extends JPanel implements ActionListener
{
	public static final int NB_BOUTON_MENU = 3;

	private Controleur ctrl;
	private FrameMenu frameMenu;

	private Image imgFond;

	private JButton[] tabBtnAction;
	


	public PanelMenu(Controleur ctrl, FrameMenu frameMenu)
	{	
		JPanel panelCentre;

		this.ctrl      = ctrl;
		this.frameMenu = frameMenu;

		this.imgFond = this.getToolkit().getImage ( "./source/ihm/images/fond/fond1.png" );

		this.setLayout(new GridBagLayout() );

		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */

		panelCentre       = new JPanel (new GridLayout(4,1,0,60 ) );

		this.tabBtnAction = new JButton[PanelMenu.NB_BOUTON_MENU];

		this.tabBtnAction[0] = new JButton("JOUER");
		this.tabBtnAction[1] = new JButton("PARAMÈTRES");
		this.tabBtnAction[2] = new JButton("QUITTER");

		
		//Modification des états des composants
		
		//System.out.println(this.btnJouer );
		
		this.tabBtnAction[0].setPreferredSize(new Dimension(200,40) );



		panelCentre.setOpaque(false);

		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */

		panelCentre.add(this.tabBtnAction[0]);
		panelCentre.add(new JLabel() );
		panelCentre.add(this.tabBtnAction[1]);
		panelCentre.add(this.tabBtnAction[2]);
		
		this.add(panelCentre, new GridBagConstraints() );

		


		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */

		/* ------------- Action ------------- */

		this.tabBtnAction[0].addActionListener(this);
		this.tabBtnAction[1].addActionListener(this);
		this.tabBtnAction[2].addActionListener(this);

		/* ------ Gestion de la souris ------ */
		
		GereSouris gereSouris = new GereSouris();

		for (JButton btn : tabBtnAction) 	
			btn.addMouseListener(gereSouris);
		


	}

	/** Anecdote : La méthode paintComponent ici utilisé sert à dessiner (entre autre) le fond du panel
	 * 
	 * Or lors de la déclaration de cette méthode, j'ai écrit à la place 'painComponent (Graphics g)'
	 * à cause d'un simple 'T' oublié, la méthode paintComponent n'était jamais appelé
	 * 
	 * Cela ne serait pas arrivé si j'avais ajouté l'annotation @Override au dessus, car Java aurait compris que 
	 * je voulais faire une surcharge, mais n'aurait pas trouvé ce nom de méthode à surcharger  
	 * 
	 * Voici une utilité du @Override
	*/

	@Override
	public void paintComponent(Graphics g)
	{

		super.paintComponent(g);

		g.drawImage(this.imgFond, 0, 0, this.getWidth(), this.getWidth(), this);

		g.setColor(Color.RED);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.tabBtnAction[2]) System.exit(0);

	}
	


	private class GereSouris extends MouseAdapter	
	{

		private JButton btnTemp = new JButton();
		
		@Override 
		public void mouseEntered(MouseEvent ev)
		{
			String nomBtn = ( (JButton) ev.getSource() ).getText().toLowerCase().trim();

			if (nomBtn.equals("jouer"       ) ) PanelMenu.this.tabBtnAction[0].setBackground(new Color(100,188,0) );
			if (nomBtn.equals("paramètres"  ) ) PanelMenu.this.tabBtnAction[1].setBackground(new Color(100,188,0) );
			if (nomBtn.equals("quitter"     ) ) PanelMenu.this.tabBtnAction[2].setBackground(new Color(188,75,0) );
		}

		@Override
		public void mouseExited(MouseEvent ev)
		{
			for (JButton btn : PanelMenu.this.tabBtnAction) 
			{
				btn.setBackground(this.btnTemp.getBackground() );
			}
		}

		@Override
		public void mouseClicked(MouseEvent ev)
		{
			if (ev.getButton() == MouseEvent.BUTTON1) System.out.println("Clique Gauche" );
			if (ev.getButton() == MouseEvent.BUTTON2) System.out.println("Clique Central");
			if (ev.getButton() == MouseEvent.BUTTON3) System.out.println("Clique Droit"  );
		}

	}
}

/* ---------------------------------- */
/*          DESSIN DYNAMIQUE          */
/* ---------------------------------- */

/* 

	private Graphics2D g2;

	private Timer timer;

	this.ratio     = 0.0;

		/*timer = new Timer(1, new ActionListener() 
		{

			public void actionPerformed(ActionEvent e)
			{
				//Action de dessin à répéter tout les x temps
				repaint();
			}

		} );

		timer.start();


	
	------------ Obtenir la taille d'un composant ---------------

	this.<cmp>.getPreferredSize() );

	-- Modifier la forme d'un bouton (test) non finalisé ---

	/*this.btnJouer     .setBorder(BorderFactory.createLineBorder(new Color(0,186,0), 6, false) );
	
	//this.btnParametres.setBorder(BorderFactory.createCompoundBorder() );
	//this.btnQuitter   .setBorder(BorderFactory.createCompoundBorder() );

*/