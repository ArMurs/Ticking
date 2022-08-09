package fr.cnam.packageGUI;

import com.formdev.flatlaf.FlatDarkLaf;

import fr.cnam.packageDeadlines.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.JButton;


import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.awt.BorderLayout;
import java.awt.EventQueue;

public class FenetrePrincipale extends JFrame {

	/*
	 * Ici, FenetreEdition et PanelObjectif peuvent avoir besoin du panelObjectifs et du Profil utilisateur P.
	 */
	private static JPanel contentPane;
	public static JPanel panelObjectifs ;
	public static Profil P;
	
	
	/*
	 * NB : Cette UI a été réalisée à l'aide d'un plugin Eclipse : WindowsBuilder Core.
	 * Le logiciel utilise également le LookandFeel FlatDarkLaf de FlatLaf.
	 * 
	 */
	public static void main(String[] args) {
		
		/*
		 * Ouverture du fichier "profil.tik" contenant le Profil de l'utilisateur.
		 * En cas d'erreur, un message s'affiche dans une fenêtre pop-up (voir Classe Profil)
		 */
		P = new Profil() ;
		P.deserialiserProfil();

			
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					// Lancement du Look and Feel FlatDarkLaf().
					UIManager.setLookAndFeel(new FlatDarkLaf());
					
					// Création et affichage de la FenetrePrincipale
					FenetrePrincipale frame = new FenetrePrincipale();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructeur de la fenêtre
	 */
	public FenetrePrincipale() {
		//Nom Du Logiciel
		setTitle("Ticking");
		
		// Opération lors de la fermeture de la fenêtre.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 600);
		
		/*
		 *  Création du contentPane qui correspond à l'ensemble du contenu de la FenetrePrincipale.
		 *  contentPane contiendra :
		 *  		> Un Panel Scrollable (scrollPane) au Centre ;
		 *  		> Un bouton Au Sud.
		 */
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		/*
		 * Dans la mesure où les objectifs seront emplilés les uns au dessus des autres, on insère dans contentPane
		 * un panel scrollable (uniquement de haut en bas)
		 */
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPanel, BorderLayout.CENTER);
		
		panelObjectifs = new JPanel();
		scrollPanel.setViewportView(panelObjectifs);
		panelObjectifs.setLayout(new BoxLayout(panelObjectifs, BoxLayout.Y_AXIS));
		
		
		// Affichage des Objectifs (voir ci-dessous)
		refreshObjectifs() ;
		
		
		
		// Mise en place du bouton "Nouvel Objectif" au Sud de le FenetrePrincipale.
		JButton BoutonNouveauObjectif = new JButton("Nouvel Objectif");
		contentPane.add(BoutonNouveauObjectif, BorderLayout.SOUTH);
		BoutonNouveauObjectif.setAlignmentX(Component.CENTER_ALIGNMENT);
		BoutonNouveauObjectif.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		
		
		// Lorsque le bouton est appuyé, on crée un nouvel Objectif
		BoutonNouveauObjectif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				// Appel du constructeur Objectif : O
				Objectif O = new Objectif() ;
			
				/*
				 * Création et affichage d'une fentre d'édition. Puisqu'il s'agit de la création d'un objectif,
				 * flagCreationObjectif est vrai :il sera nécéssaire d'ajouter l'objectif O en fin de modificaition.
				 */
				FenetreEdition edition = new FenetreEdition(O, true) ;
				edition.setVisible(true); ;
				}
		});

		
		
		/*
		 * Entre le moment où on quitte le logiciel et le moment où celui-ci se ferme, on enregistre le profil P.
		 */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				P.serialiserProfil();
			}
		});
		
	}
	
	
	/*
	 * Cette méthode permet de rafraichir l'affichage de tous les objectifs contenus dans le profil Utilisateur.
	 * Cette méthode peut être appelé par la FenetreEdition.
	 */
	public static void refreshObjectifs() {
		
		// On vide le panelObjectifs de tous les panelObjectif présents.
		panelObjectifs.removeAll() ;
		
		/*
		 * Pour chacun des objectifs présent dans le profil utilisateur, on construit et affiche le PanelObjectif
		 * Correspondant.
		 */
		for(Objectif O : P.getListeObjectifs()) {
			PanelObjectif panelObj = new PanelObjectif(O) ;
			panelObjectifs.add(panelObj) ;
		
		}
		
	}

	

}
