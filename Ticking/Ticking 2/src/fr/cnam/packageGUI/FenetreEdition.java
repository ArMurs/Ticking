package fr.cnam.packageGUI;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import fr.cnam.packageDeadlines.*;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class FenetreEdition extends JFrame {

	private JPanel FenetreEdition;
	
	
	private JTextField textFieldNom;
	private JFormattedTextField ftfJours ;
	private JFormattedTextField ftfMois ;
	private JFormattedTextField ftfAnnee ;
	private JFormattedTextField ftfHeures ;
	private JFormattedTextField ftfMinutes ;
	private JComboBox comboBoxRepetition;
	

	/*
	 * NB : Cette UI a été réalisée à l'aide d'un plugin Eclipse : WindowsBuilder Core.
	 */
	
	/*
	 * Création de la FenetreEdition
	 * 
	 * La FentereEdition se lance en prenant en paramètres : 
	 * 		> l'objectif O à modifier/créer ;
	 * 		> le boolée flagCreationObjectif qui indique si il s'agit d'une création (true) ou d'une modification (false)
	 * 
	 * En cas de création, il est nécessaire d'ajouter l'objectif O au profil utilisateur P. Ce n'est pas vrai dans 
	 * le cas d'une modification : l'objet se retrouverais alors en double.
	 */
	public FenetreEdition(Objectif O, boolean flagCreationObjectif) {
		
		/*
		 *  Création de la fenetre et remplissage du contenu avec un JPanel FenetreEdition (trèèèès mauvais choix de nom !)
		 *  Ce Panel recevra : 
		 *  	> Au centre : les paramètres à modifier de l'Objectif (Panel PanelEdition);
		 *  	> Au Sud : les boutons enregistrer et annuler. (Panel PanelBoutons)
		 */ 
		setTitle("\u00C9dition");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 233);
		FenetreEdition = new JPanel();
		FenetreEdition.setForeground(Color.GRAY);
		FenetreEdition.setBackground(SystemColor.windowBorder);
		FenetreEdition.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(FenetreEdition);
		FenetreEdition.setLayout(new BorderLayout(0, 0));


		/*
		 * Création du Panel PanelEdition
		 */
		JPanel PanelEdition = new JPanel();
		FenetreEdition.add(PanelEdition, BorderLayout.CENTER);
		GridBagLayout gbl_PanelEdition = new GridBagLayout();
		gbl_PanelEdition.columnWidths = new int[] {150, 0, 0};
		gbl_PanelEdition.rowHeights = new int[] {30, 30, 30};
		gbl_PanelEdition.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_PanelEdition.rowWeights = new double[]{0.0, 0.0, 0.0};
		PanelEdition.setLayout(gbl_PanelEdition);
		
		
		
		/*
		 * Création, mise en forme et ajout d'un label "Nom"
		 */
		JLabel LabelNom = new JLabel("Nom de l'objectif");
		LabelNom.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		LabelNom.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_LabelNom = new GridBagConstraints();
		gbc_LabelNom.anchor = GridBagConstraints.EAST;
		gbc_LabelNom.insets = new Insets(0, 0, 5, 5);
		gbc_LabelNom.fill = GridBagConstraints.VERTICAL;
		gbc_LabelNom.gridx = 0;
		gbc_LabelNom.gridy = 0;
		PanelEdition.add(LabelNom, gbc_LabelNom);
		
		
		
		/*
		 * Création, mise en forme et ajout d'un label "Date"
		 */
		JLabel LabelDate = new JLabel("\u00C9ch\u00E9ance de l'objectif");
		LabelDate.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_LabelDate = new GridBagConstraints();
		gbc_LabelDate.anchor = GridBagConstraints.EAST;
		gbc_LabelDate.insets = new Insets(0, 0, 5, 5);
		gbc_LabelDate.gridx = 0;
		gbc_LabelDate.gridy = 1;
		PanelEdition.add(LabelDate, gbc_LabelDate);
		
		
		
		/*
		 * Création, mise en forme et ajout d'un un champ de texte qui recueillera le nom de l'objectif.
		 * Celui-ci contiendra le nom initial de l'objectif.
		 */
		textFieldNom = new JTextField();
		textFieldNom.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldNom.setText(O.getNom());
		GridBagConstraints gbc_textFieldNom = new GridBagConstraints();
		gbc_textFieldNom.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNom.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNom.gridx = 1;
		gbc_textFieldNom.gridy = 0;
		PanelEdition.add(textFieldNom, gbc_textFieldNom);
		textFieldNom.setColumns(10);
		
		
		
		/*
		 * Création du Panel panelDate qui contient les différents champs de texte et labels pour modifier la date.
		 */
		JPanel panelDate = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		PanelEdition.add(panelDate, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 35, 0, 35, 0, 70, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panelDate.setLayout(gbl_panel);
		
		
		
		/*
		 * Création, mise en forme et ajout d'un label "Jour" (dans panelDate)
		 */
		JLabel LabelJour = new JLabel("Jour : ");
		LabelJour.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_LabelJour = new GridBagConstraints();
		gbc_LabelJour.insets = new Insets(0, 0, 5, 5);
		gbc_LabelJour.anchor = GridBagConstraints.NORTHEAST;
		gbc_LabelJour.gridx = 0;
		gbc_LabelJour.gridy = 0;
		panelDate.add(LabelJour, gbc_LabelJour);
		
		
		/*
		 * Création, mise en forme et ajout d'un un champ de texte qui recueillera le jour de l'échéance de l'objectif.
		 */
		ftfJours = new JFormattedTextField();
		ftfJours.setText(Integer.toString(O.getDateLimite().getDayOfMonth()));
		ftfJours.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		ftfJours.setColumns(25);
		GridBagConstraints gbc_ftfJours = new GridBagConstraints();
		gbc_ftfJours.insets = new Insets(0, 0, 5, 5);
		gbc_ftfJours.fill = GridBagConstraints.HORIZONTAL;
		gbc_ftfJours.gridx = 1;
		gbc_ftfJours.gridy = 0;
		panelDate.add(ftfJours, gbc_ftfJours);
		
		
		
		/*
		 * Création, mise en forme et ajout d'un label "Mois" (dans panelDate)
		 */
		JLabel LabelMois = new JLabel("Mois : ");
		LabelMois.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_LabelMois = new GridBagConstraints();
		gbc_LabelMois.anchor = GridBagConstraints.EAST;
		gbc_LabelMois.insets = new Insets(0, 0, 5, 5);
		gbc_LabelMois.gridx = 2;
		gbc_LabelMois.gridy = 0;
		panelDate.add(LabelMois, gbc_LabelMois);
		
		
		
		/*
		 * Création, mise en forme et ajout d'un un champ de texte qui recueillera le mois de l'échéance de l'objectif.
		 */
		ftfMois = new JFormattedTextField();
		ftfMois.setText(Integer.toString(O.getDateLimite().getMonthValue()));
		ftfMois.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		ftfMois.setColumns(25);
		GridBagConstraints gbc_ftfMois = new GridBagConstraints();
		gbc_ftfMois.insets = new Insets(0, 0, 5, 5);
		gbc_ftfMois.fill = GridBagConstraints.HORIZONTAL;
		gbc_ftfMois.gridx = 3;
		gbc_ftfMois.gridy = 0;
		panelDate.add(ftfMois, gbc_ftfMois);
		
		
		
		/*
		 * Création, mise en forme et ajout d'un label "Année" (dans panelDate)
		 */
		JLabel LabelAnnee = new JLabel("Ann\u00E9e : ");
		LabelAnnee.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_LabelAnnee = new GridBagConstraints();
		gbc_LabelAnnee.anchor = GridBagConstraints.EAST;
		gbc_LabelAnnee.insets = new Insets(0, 0, 5, 5);
		gbc_LabelAnnee.gridx = 4;
		gbc_LabelAnnee.gridy = 0;
		panelDate.add(LabelAnnee, gbc_LabelAnnee);
		
		
		
		/*
		 * Création, mise en forme et ajout d'un un champ de texte qui recueillera l'année de l'échéance de l'objectif.
		 */
		ftfAnnee = new JFormattedTextField();
		ftfAnnee.setText(Integer.toString(O.getDateLimite().getYear()));
		ftfAnnee.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		ftfAnnee.setColumns(60);
		GridBagConstraints gbc_ftfAnnee = new GridBagConstraints();
		gbc_ftfAnnee.insets = new Insets(0, 0, 5, 0);
		gbc_ftfAnnee.fill = GridBagConstraints.HORIZONTAL;
		gbc_ftfAnnee.gridx = 5;
		gbc_ftfAnnee.gridy = 0;
		panelDate.add(ftfAnnee, gbc_ftfAnnee);
		
		
		/* 
		 * Création, mise en forme et ajout d'un label "Heure" (dans panelDate)
		 */
		JLabel LabelHeures = new JLabel("Heure :");
		LabelHeures.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_LabelHeure = new GridBagConstraints();
		gbc_LabelHeure.anchor = GridBagConstraints.EAST;
		gbc_LabelHeure.insets = new Insets(0, 0, 0, 5);
		gbc_LabelHeure.gridx = 0;
		gbc_LabelHeure.gridy = 1;
		panelDate.add(LabelHeures, gbc_LabelHeure);
		
		
		
		
		/*
		 * Création, mise en forme et ajout d'un un champ de texte qui recueillera l'heure de l'échéance de l'objectif.
		 */
		ftfHeures = new JFormattedTextField();
		ftfHeures.setText(Integer.toString(O.getDateLimite().getHour()));
		ftfHeures.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		ftfHeures.setColumns(25);
		GridBagConstraints gbc_ftfHeures = new GridBagConstraints();
		gbc_ftfHeures.insets = new Insets(0, 0, 0, 5);
		gbc_ftfHeures.fill = GridBagConstraints.HORIZONTAL;
		gbc_ftfHeures.gridx = 1;
		gbc_ftfHeures.gridy = 1;
		panelDate.add(ftfHeures, gbc_ftfHeures);
		
		
		
		/*
		 * Création, mise en forme et ajout d'un label "Minutes" (dans panelDate)
		 */
		JLabel LabelMinutes = new JLabel("Minutes : ");
		LabelMinutes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_LabelMinutes = new GridBagConstraints();
		gbc_LabelMinutes.anchor = GridBagConstraints.EAST;
		gbc_LabelMinutes.insets = new Insets(0, 0, 0, 5);
		gbc_LabelMinutes.gridx = 2;
		gbc_LabelMinutes.gridy = 1;
		panelDate.add(LabelMinutes, gbc_LabelMinutes);
		
		
		
		/*
		 * Création, mise en forme et ajout d'un un champ de texte qui recueillera les minutes de l'échéance de l'objectif.
		 */
		ftfMinutes = new JFormattedTextField();
		ftfMinutes.setText(Integer.toString(O.getDateLimite().getMinute()));
		ftfMinutes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		ftfMinutes.setColumns(25);
		GridBagConstraints gbc_ftfMinutes = new GridBagConstraints();
		gbc_ftfMinutes.insets = new Insets(0, 0, 0, 5);
		gbc_ftfMinutes.fill = GridBagConstraints.HORIZONTAL;
		gbc_ftfMinutes.gridx = 3;
		gbc_ftfMinutes.gridy = 1;
		panelDate.add(ftfMinutes, gbc_ftfMinutes);

		
		/*
		 * Création, mise en forme et ajout d'un label "Repetition"
		 */
		JLabel LabelRepetition = new JLabel("Repetition");
		LabelRepetition.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_LabelRepetition = new GridBagConstraints();
		gbc_LabelRepetition.insets = new Insets(0, 0, 0, 5);
		gbc_LabelRepetition.anchor = GridBagConstraints.EAST;
		gbc_LabelRepetition.gridx = 0;
		gbc_LabelRepetition.gridy = 2;
		PanelEdition.add(LabelRepetition, gbc_LabelRepetition);
		
		
		/*
		 * Création d'un menu déroulant (ComboBox) qui permet de sélectionner l'éventuel type de répétition de 
		 * l'objectif.
		 */
		comboBoxRepetition = new JComboBox();
		comboBoxRepetition.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxRepetition.setModel(new DefaultComboBoxModel(CategoriesRepetition.values()));
		comboBoxRepetition.setSelectedIndex(O.getRepetition().ordinal());
		GridBagConstraints gbc_comboBoxRepetition = new GridBagConstraints();
		gbc_comboBoxRepetition.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRepetition.gridx = 1;
		gbc_comboBoxRepetition.gridy = 2;
		PanelEdition.add(comboBoxRepetition, gbc_comboBoxRepetition);
		
		
		
		
		/*
		 * Création du Panel PanelBoutons au sud de la fenetre et qui contiendra les boutons Enregistrer et Annuler
		 */
		JPanel PanelBoutons = new JPanel();
		FenetreEdition.add(PanelBoutons, BorderLayout.SOUTH);
		PanelBoutons.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		
		
		/*
		 * Création et placement du bouton Enregistrer.
		 */
		JButton BoutonEnregistrer = new JButton("Enregistrer");
		BoutonEnregistrer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		PanelBoutons.add(BoutonEnregistrer);

		
		/*
		 * Création et placement du bouton Annuler.
		 */
		JButton BoutonAnnuler = new JButton("Annuler");
		BoutonAnnuler.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		PanelBoutons.add(BoutonAnnuler);
		
		
		
		
		/*
		 * Évenement : Lorsque la FenetreEdition se ferme, la FenetrePrincipale se met a jour.
		 */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				FenetrePrincipale.refreshObjectifs() ;
			}
		});
		
		
		
		
		/*
		 * Évenement : Lorsque le bouton "Enregistrer" est appuyé, on modifie les caractéristiques de l'objectit O.
		 */
		
		BoutonEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modificationObjectif(O) ;
				/*
				 * NB : Si de plus, la FenetreEdition est en mode création (flagCreationObjectif = true),
				 * On ajoute l'objectif O au profil présent dans la FenetrePrincipale
				 */
				if (flagCreationObjectif) {
					FenetrePrincipale.P.ajouterObjectif(O) ;
				}
			}
		});
		
		
		
		
		/*
		 * Évenement : si l'utilisateur clique sur Annuler, on ferme simplement la FenetreEdition.
		 */
		BoutonAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose() ;
			}
		});
		
		
	}

	
	
	
	/*
	 * Modification des éléments de l'Objectif en fonction du contenu présent dans les différents champs.
	 * Cette Methode est appelée lorsque l'utilisateur appuie sur Enregistrer.
	 */
	public void modificationObjectif(Objectif O) {
		
		/*
		 * On récupère le nom, les différents éléments de la date ainsi que le type de répétition à partir des différents champs
		 * de saisie. On essaie alors de modifier les caractéristiques de l'Objectifs avec ces éléments.
		 */
		try {
			// Nom
			O.setNom(textFieldNom.getText());
			
			// Échéance
			O.setDateLimite(LocalDateTime.of(	Integer.parseInt(ftfAnnee.getText()),
												Integer.parseInt(ftfMois.getText()),
												Integer.parseInt(ftfJours.getText()),
												Integer.parseInt(ftfHeures.getText()),
												Integer.parseInt(ftfMinutes.getText()) ));
			 // Répétition
			O.setRepetition((CategoriesRepetition) (comboBoxRepetition.getSelectedItem()));
			
			/*
			 * Si tout s'est bien passé jusqu'ici, on peut fermer la fenetre.
			 */
			dispose() ;
			
			/*
			 * Dans le cas contraire, il y a forcément une erreur de frappe quelque part, à savoir :
			 * 		> Un caractère non numérique dans les champs de date ;
			 * 		> Une date incohérente (Exemple : 62 Juillet 2022 à 88:88)
			 * 
			 * On affiche donc une fenêtre pop-up avec le message d'erreur corresdpondant.
			 */
		} catch (NumberFormatException e) {
			
			JOptionPane.showMessageDialog(this, "Erreur : L'un des champs définissant la date n'est pas un nombre.");
			
		} catch (DateTimeException e) {
			
			JOptionPane.showMessageDialog(this, "Erreur : La date renseignée n'est pas une date valide.");
			
		} catch (Exception e) {

			JOptionPane.showMessageDialog(this, "Erreur : Impossible de modifier l'objectif.\n"+ e.toString());
			
		}
	}

}
