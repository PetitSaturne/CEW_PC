package source.ihm;

import source.Controleur;
import source.ihm.utils.BouttonOvale;

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
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PanelMenu extends JPanel implements ActionListener
{


	private Controleur ctrl;

	private Image imgFond;
	
	private JButton btnJouer;
	private JButton	btnParametres;
	private JButton btnQuitter;

	public PanelMenu(Controleur ctrl)
	{	
		JPanel panelCentre;

		this.ctrl    = ctrl;
		this.imgFond = this.getToolkit().getImage ( "./ihm/images/fond/fond1.png" );

		this.setLayout(new GridBagLayout() );

		/* ---------------------------------- */
		/*       Création des composants      */
		/* ---------------------------------- */

		panelCentre        = new JPanel (new GridLayout(4,1,0,60 ) );

		this.btnJouer      = new JButton("JOUER");
		this.btnParametres = new JButton("PARAMÈTRES");
		this.btnQuitter    = new JButton("QUITTER");

		
		//Modification des états des composants
		
		System.out.println(this.btnJouer );
		
		this.btnJouer.setPreferredSize(new Dimension(200,40) );

		/*this.btnJouer     .setBorder(BorderFactory.createLineBorder(new Color(0,186,0), 6, false) );*/
	
		//this.btnParametres.setBorder(BorderFactory.createCompoundBorder() );
		//this.btnQuitter   .setBorder(BorderFactory.createCompoundBorder() );

		panelCentre.setOpaque(false);

		/* ---------------------------------- */
		/*    Positionnement des composants   */
		/* ---------------------------------- */

		panelCentre.add(this.btnJouer);
		panelCentre.add(new JLabel() );
		panelCentre.add(this.btnParametres);
		panelCentre.add(this.btnQuitter);
		
		this.add(panelCentre, new GridBagConstraints() );

		


		/* ---------------------------------- */
		/*      Activation des composants     */
		/* ---------------------------------- */

		GereSouris gereSouris = new GereSouris();

		this.btnJouer.addActionListener(this);

		this.btnJouer.addMouseListener(gereSouris);


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

	}
	


	private class GereSouris extends MouseAdapter
	{
		@Override 
		public void mouseEntered(MouseEvent e)
		{
			PanelMenu.this.btnJouer.setBackground(new Color(0,188,0) );
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			PanelMenu.this.btnJouer.setBackground(new JButton().getBackground() );
		}

		@Override
		public void mouseClicked(MouseEvent e)
		{
			if (e.getButton() == MouseEvent.BUTTON1) System.out.println("Clique Gauche" );
			if (e.getButton() == MouseEvent.BUTTON2) System.out.println("Clique Central");
			if (e.getButton() == MouseEvent.BUTTON3) System.out.println("Clique Droit"  );
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

*/