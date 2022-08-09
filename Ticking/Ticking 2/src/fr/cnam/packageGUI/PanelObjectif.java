package fr.cnam.packageGUI;

import javax.swing.JPanel;

import fr.cnam.packageDeadlines.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.Timer;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelObjectif extends JPanel {
	
	private JButton btnMisAJourObjectif;

	/*
	 * NB : Cette UI a �t� r�alis�e � l'aide d'un plugin Eclipse : WindowsBuilder Core.
	 */
	
	
	/*
	 * Cr�ation du PanelObjectif.
	 * Propre � chaque objectif, il contient :
	 * 		> Le nom de l'objectif ;
	 * 		> L'�ch�ance de l'objectif ;
	 * 		> La s�rie actuelle ainsi que la meilleure s�rie (dans les cas des Objectifs r�p�t�s) ;
	 * 		> Le compte � rebours avant �ch�ance ;
	 * 
	 * 		> Un bouton Supprimer ;
	 * 		> Un bouton Modifier ;
	 * 		> Un bouton Mise � Jour Objectif (dans les cas des Objectifs r�p�t�s).
	 */
	public PanelObjectif(Objectif O) {
		
		// Mise en place d'un bord noir pour chaque objectif.
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setMaximumSize(new Dimension(600,130)) ;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{25, 25, 0, 40, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		
		/*
		 * Cr�ation, mise en forme et ajout d'un label "Nom"
		 */
		JLabel LabelNom = new JLabel(O.getNom());
		LabelNom.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		GridBagConstraints gbc_LabelNom = new GridBagConstraints();
		gbc_LabelNom.insets = new Insets(0, 0, 5, 5);
		gbc_LabelNom.gridx = 0;
		gbc_LabelNom.gridy = 0;
		add(LabelNom, gbc_LabelNom);
		
		
		/*
		 * Cr�ation, mise en forme et ajout d'un label "�ch�ance"
		 */
		JLabel LabelEcheance = new JLabel(O.getDateFormat() + "     " + O.getHeureFormat());
		LabelEcheance.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		GridBagConstraints gbc_LabelEcheance = new GridBagConstraints();
		gbc_LabelEcheance.insets = new Insets(0, 0, 5, 5);
		gbc_LabelEcheance.gridx = 0;
		gbc_LabelEcheance.gridy = 1;
		add(LabelEcheance, gbc_LabelEcheance);
		
		
	
		/*
		 * Cr�ation, mise en forme et ajout d'un label "S�ries"
		 */
		JLabel labelSeries = new JLabel();
		labelSeries.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		GridBagConstraints gbc_labelSeries = new GridBagConstraints();
		gbc_labelSeries.insets = new Insets(0, 0, 5, 5);
		gbc_labelSeries.gridx = 0;
		gbc_labelSeries.gridy = 2;
		add(labelSeries, gbc_labelSeries);
		
		
		/*
		 * NB : le Label S�ries est forc�ment pr�sent pour tout type d'Objectifs. En revanche, pour les
		 * objectifs non r�p�t�s, il ne contient aucun texte, alors que pour les objectis r�p�t�s, les s�ries sont affich�es
		 */
		if (!(O.getRepetition().equals(CategoriesRepetition.AUCUNE))) {
			labelSeries.setText("S�rie Actuelle : "+ O.getSerieActuelle() + "      Meilleure S�rie : "+ O.getMeilleureSerie());
		}

		
		/*
		 * Cr�ation, mise en forme et ajout d'un label "Compte � rebours"
		 */
		JLabel LabelCompteARebours = new JLabel(O.tempsRestant());
		LabelCompteARebours.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		GridBagConstraints gbc_LabelCompteARebours = new GridBagConstraints();
		gbc_LabelCompteARebours.insets = new Insets(0, 0, 0, 5);
		gbc_LabelCompteARebours.gridx = 0;
		gbc_LabelCompteARebours.gridy = 3;
		add(LabelCompteARebours, gbc_LabelCompteARebours);
		
		
		
		
		/*
		 * Cr�ation, mise en forme et ajout du bouton Edition
		 */
		JButton boutonEdition = new JButton("Modifier");
		boutonEdition.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_boutonEdition = new GridBagConstraints();
		gbc_boutonEdition.fill = GridBagConstraints.HORIZONTAL;
		gbc_boutonEdition.insets = new Insets(0, 0, 5, 0);
		gbc_boutonEdition.gridx = 1;
		gbc_boutonEdition.gridy = 1;
		add(boutonEdition, gbc_boutonEdition);
		
		/*
		 * Cr�ation, mise en forme et ajout du bouton Supprimer
		 */
		JButton BoutonSupprimer = new JButton("Supprimer");
		BoutonSupprimer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_BoutonSupprimer = new GridBagConstraints();
		gbc_BoutonSupprimer.fill = GridBagConstraints.HORIZONTAL;
		gbc_BoutonSupprimer.insets = new Insets(0, 0, 5, 0);
		gbc_BoutonSupprimer.gridx = 1;
		gbc_BoutonSupprimer.gridy = 0;
		add(BoutonSupprimer, gbc_BoutonSupprimer);
		
		

		/*
		 * Seulement dans le cas o� l'Objectif est r�p�t� : Cr�ation et mise en forme et ajout du bouton Mise � Jour
		 */
		if (!(O.getRepetition().equals(CategoriesRepetition.AUCUNE))) {
			
			btnMisAJourObjectif = new JButton("Objectif Atteint");
			btnMisAJourObjectif.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			GridBagConstraints gbc_btnMisAJourObjectif = new GridBagConstraints();
			gbc_btnMisAJourObjectif.gridx = 1;
			gbc_btnMisAJourObjectif.gridy = 3;
			/*
			 * NB : Si ce n'est pas encore le bon moment pour l'utilisateur de mettre � jour le profil, 
			 * ce bouton sera pr�sent mais pas utilisable.
			 */
			btnMisAJourObjectif.setEnabled(O.estBonnePeriode());
			add(btnMisAJourObjectif, gbc_btnMisAJourObjectif);
			
			
			
			
			/*
			 * �venement : Lorsque le bouton Mise � jour est cliqu� :
			 */
			btnMisAJourObjectif.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					/*
					 * On teste si c'est le bon moment pour mettre l'Objectif � jour (Pour une raison qui m'�chappe,
					 * un bouton pr�sent mais d�sactiv� reste cliquable.)
					 */
					if (O.estBonnePeriode()) {
						
						//On valide alors cette mise � jour (+1 pour la s�rie, test s�rie maximum et report d'�ch�ance)
						O.objectifAtteint();
						
						// D�sactivationn du bouton Mise � Jour
						btnMisAJourObjectif.setEnabled(false);
						
						// Modification du label contenant les s�ries
						labelSeries.setText("S�rie Actuelle : "+ O.getSerieActuelle() + "      Meilleure S�rie : "+ O.getMeilleureSerie());
						
						// Modification du label contenant la date
						LabelEcheance.setText(O.getDateFormat() + "     " + O.getHeureFormat());
					}
					
					
				}
				
			});
		}
		
		
		
		
		/*
		 * �venement : Lorsque le bouton Supprimer est cliqu� :
		 */
		BoutonSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// On affiche un pop-up Oui/Non pour que l'utilisateur confirme son choix 
				int confirmation = JOptionPane.showConfirmDialog(BoutonSupprimer.getParent(), "�tes sous s�r(e) de vouloir supprimer cet objectif ?","Confirmation Suppression", JOptionPane.YES_NO_OPTION) ;
				
				// Si l'utilisateur confirme son choix, on supprime alors l'objectif du profil utilisateur,
				if (confirmation == JOptionPane.YES_OPTION) {
					FenetrePrincipale.P.retirerObjectif(O) ;
					
					// On retire le PanelObjectif de la FenetrePrincipale.
					effacerObjectif() ;
					
				}
			}
		});
		
		
		
		/*
		 * �venement : Lorsque le bouton Modifier est cliqu� :
		 */
		boutonEdition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * On ouvre la FenetreEdition avec en param�tres :
				 * 		> l'Objectif
				 *		> flagCreationObjectif = false (il s'agit d'une modification d'un objectif existant et 
				 * non de la cr�ation d'un nouvel objectif)
				 */
				FenetreEdition fenetreModification = new FenetreEdition(O, false) ;
				fenetreModification.setVisible(true);
			}

		});
		
		
		
		/*
		 * �venement : Ce qui suit se produit toute les secondes, c'est ce qui permet d'animer les comptes � rebours.
		 */
		int delay = 1000; //millisecondes
		
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				// Changement du contenu du LabelCompteARebours
				LabelCompteARebours.setText(O.tempsRestant());
				
				// Si l'objectif se r�p�te :
				if (!(O.getRepetition().equals(CategoriesRepetition.AUCUNE))) {
					
					// On rend disponible ou non le bouton de mis � jour :
					btnMisAJourObjectif.setEnabled(O.estBonnePeriode());
					
					
					// Si l'�ch�ance en plus est pass�e :
					if (O.estPasse()) {
						
						// L'objectif est consid�r� comme manqu� (s�rieActuelle ppv 0, report de l'�ch�ance)
						O.objectifManque() ;
						
						// Mise � jour du label S�rie
						labelSeries.setText("S�rie Actuelle : "+ O.getSerieActuelle() + "      Meilleure S�rie : "+ O.getMeilleureSerie());
						
						// Mise � jour du label �ch�ance
						LabelEcheance.setText(O.getDateFormat() + "     " + O.getHeureFormat());
						}
					
					
					}
				
		      	}
		      	
		  	};
		new Timer(delay, taskPerformer).start();

		
	}

	/*
	 *  Cette m�thode permet d'effacer le PanelObjectif de ceux pr�sents dans la FenetrePrincipale et
	 *  de raffraichir le panelObjectifs.
	 */
	
	public void effacerObjectif() {
		
		this.setVisible(false);
		FenetrePrincipale.panelObjectifs.repaint() ;

	}

}
