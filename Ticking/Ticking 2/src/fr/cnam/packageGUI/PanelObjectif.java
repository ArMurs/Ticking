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
	 * NB : Cette UI a été réalisée à l'aide d'un plugin Eclipse : WindowsBuilder Core.
	 */
	
	
	/*
	 * Création du PanelObjectif.
	 * Propre à chaque objectif, il contient :
	 * 		> Le nom de l'objectif ;
	 * 		> L'échéance de l'objectif ;
	 * 		> La série actuelle ainsi que la meilleure série (dans les cas des Objectifs répétés) ;
	 * 		> Le compte à rebours avant échéance ;
	 * 
	 * 		> Un bouton Supprimer ;
	 * 		> Un bouton Modifier ;
	 * 		> Un bouton Mise à Jour Objectif (dans les cas des Objectifs répétés).
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
		 * Création, mise en forme et ajout d'un label "Nom"
		 */
		JLabel LabelNom = new JLabel(O.getNom());
		LabelNom.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		GridBagConstraints gbc_LabelNom = new GridBagConstraints();
		gbc_LabelNom.insets = new Insets(0, 0, 5, 5);
		gbc_LabelNom.gridx = 0;
		gbc_LabelNom.gridy = 0;
		add(LabelNom, gbc_LabelNom);
		
		
		/*
		 * Création, mise en forme et ajout d'un label "Échéance"
		 */
		JLabel LabelEcheance = new JLabel(O.getDateFormat() + "     " + O.getHeureFormat());
		LabelEcheance.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		GridBagConstraints gbc_LabelEcheance = new GridBagConstraints();
		gbc_LabelEcheance.insets = new Insets(0, 0, 5, 5);
		gbc_LabelEcheance.gridx = 0;
		gbc_LabelEcheance.gridy = 1;
		add(LabelEcheance, gbc_LabelEcheance);
		
		
	
		/*
		 * Création, mise en forme et ajout d'un label "Séries"
		 */
		JLabel labelSeries = new JLabel();
		labelSeries.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		GridBagConstraints gbc_labelSeries = new GridBagConstraints();
		gbc_labelSeries.insets = new Insets(0, 0, 5, 5);
		gbc_labelSeries.gridx = 0;
		gbc_labelSeries.gridy = 2;
		add(labelSeries, gbc_labelSeries);
		
		
		/*
		 * NB : le Label Séries est forcément présent pour tout type d'Objectifs. En revanche, pour les
		 * objectifs non répétés, il ne contient aucun texte, alors que pour les objectis répétés, les séries sont affichées
		 */
		if (!(O.getRepetition().equals(CategoriesRepetition.AUCUNE))) {
			labelSeries.setText("Série Actuelle : "+ O.getSerieActuelle() + "      Meilleure Série : "+ O.getMeilleureSerie());
		}

		
		/*
		 * Création, mise en forme et ajout d'un label "Compte à rebours"
		 */
		JLabel LabelCompteARebours = new JLabel(O.tempsRestant());
		LabelCompteARebours.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		GridBagConstraints gbc_LabelCompteARebours = new GridBagConstraints();
		gbc_LabelCompteARebours.insets = new Insets(0, 0, 0, 5);
		gbc_LabelCompteARebours.gridx = 0;
		gbc_LabelCompteARebours.gridy = 3;
		add(LabelCompteARebours, gbc_LabelCompteARebours);
		
		
		
		
		/*
		 * Création, mise en forme et ajout du bouton Edition
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
		 * Création, mise en forme et ajout du bouton Supprimer
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
		 * Seulement dans le cas où l'Objectif est répété : Création et mise en forme et ajout du bouton Mise à Jour
		 */
		if (!(O.getRepetition().equals(CategoriesRepetition.AUCUNE))) {
			
			btnMisAJourObjectif = new JButton("Objectif Atteint");
			btnMisAJourObjectif.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			GridBagConstraints gbc_btnMisAJourObjectif = new GridBagConstraints();
			gbc_btnMisAJourObjectif.gridx = 1;
			gbc_btnMisAJourObjectif.gridy = 3;
			/*
			 * NB : Si ce n'est pas encore le bon moment pour l'utilisateur de mettre à jour le profil, 
			 * ce bouton sera présent mais pas utilisable.
			 */
			btnMisAJourObjectif.setEnabled(O.estBonnePeriode());
			add(btnMisAJourObjectif, gbc_btnMisAJourObjectif);
			
			
			
			
			/*
			 * Évenement : Lorsque le bouton Mise à jour est cliqué :
			 */
			btnMisAJourObjectif.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					/*
					 * On teste si c'est le bon moment pour mettre l'Objectif à jour (Pour une raison qui m'échappe,
					 * un bouton présent mais désactivé reste cliquable.)
					 */
					if (O.estBonnePeriode()) {
						
						//On valide alors cette mise à jour (+1 pour la série, test série maximum et report d'échéance)
						O.objectifAtteint();
						
						// Désactivationn du bouton Mise à Jour
						btnMisAJourObjectif.setEnabled(false);
						
						// Modification du label contenant les séries
						labelSeries.setText("Série Actuelle : "+ O.getSerieActuelle() + "      Meilleure Série : "+ O.getMeilleureSerie());
						
						// Modification du label contenant la date
						LabelEcheance.setText(O.getDateFormat() + "     " + O.getHeureFormat());
					}
					
					
				}
				
			});
		}
		
		
		
		
		/*
		 * Évenement : Lorsque le bouton Supprimer est cliqué :
		 */
		BoutonSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// On affiche un pop-up Oui/Non pour que l'utilisateur confirme son choix 
				int confirmation = JOptionPane.showConfirmDialog(BoutonSupprimer.getParent(), "Êtes sous sûr(e) de vouloir supprimer cet objectif ?","Confirmation Suppression", JOptionPane.YES_NO_OPTION) ;
				
				// Si l'utilisateur confirme son choix, on supprime alors l'objectif du profil utilisateur,
				if (confirmation == JOptionPane.YES_OPTION) {
					FenetrePrincipale.P.retirerObjectif(O) ;
					
					// On retire le PanelObjectif de la FenetrePrincipale.
					effacerObjectif() ;
					
				}
			}
		});
		
		
		
		/*
		 * Évenement : Lorsque le bouton Modifier est cliqué :
		 */
		boutonEdition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * On ouvre la FenetreEdition avec en paramètres :
				 * 		> l'Objectif
				 *		> flagCreationObjectif = false (il s'agit d'une modification d'un objectif existant et 
				 * non de la création d'un nouvel objectif)
				 */
				FenetreEdition fenetreModification = new FenetreEdition(O, false) ;
				fenetreModification.setVisible(true);
			}

		});
		
		
		
		/*
		 * Évenement : Ce qui suit se produit toute les secondes, c'est ce qui permet d'animer les comptes à rebours.
		 */
		int delay = 1000; //millisecondes
		
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				// Changement du contenu du LabelCompteARebours
				LabelCompteARebours.setText(O.tempsRestant());
				
				// Si l'objectif se répète :
				if (!(O.getRepetition().equals(CategoriesRepetition.AUCUNE))) {
					
					// On rend disponible ou non le bouton de mis à jour :
					btnMisAJourObjectif.setEnabled(O.estBonnePeriode());
					
					
					// Si l'échéance en plus est passée :
					if (O.estPasse()) {
						
						// L'objectif est considéré comme manqué (sérieActuelle ppv 0, report de l'échéance)
						O.objectifManque() ;
						
						// Mise à jour du label Série
						labelSeries.setText("Série Actuelle : "+ O.getSerieActuelle() + "      Meilleure Série : "+ O.getMeilleureSerie());
						
						// Mise à jour du label Échéance
						LabelEcheance.setText(O.getDateFormat() + "     " + O.getHeureFormat());
						}
					
					
					}
				
		      	}
		      	
		  	};
		new Timer(delay, taskPerformer).start();

		
	}

	/*
	 *  Cette méthode permet d'effacer le PanelObjectif de ceux présents dans la FenetrePrincipale et
	 *  de raffraichir le panelObjectifs.
	 */
	
	public void effacerObjectif() {
		
		this.setVisible(false);
		FenetrePrincipale.panelObjectifs.repaint() ;

	}

}
