package fr.cnam.packageDeadlines;

import java.time.LocalDateTime;
import java.io.*;
import java.util.* ;

import javax.swing.JOptionPane;


public class Profil {
	
	/**
	 * Classe Profil :
	 * 		> listeObjectifs [List <Objectif>] : la liste des objectifs de l'utilisateur.
	 * 
	 * Celle classe sert uniquement pour rassembler tous les objectifs et les enregistrer dans un fichier "profil.tik"
	 */
	
	private List <Objectif> listeObjectifs ;
	
	
	// Constructeur :
	public Profil(){
		listeObjectifs = new ArrayList<Objectif>() ;
	}
	
	// Getter :
	public List <Objectif> getListeObjectifs() {
		return(listeObjectifs) ;
	}
	
	// NB : Setter inutile
	
	
	// Méthode permettant d'ajouter un objectif à la liste.
	public boolean ajouterObjectif(Objectif o) {
		return(listeObjectifs.add(o)) ;
	}
	
	
	// Méthode permettant d'enlever un objectif à la liste.
	public boolean retirerObjectif(Objectif o) {
		return(listeObjectifs.remove(o)) ;
	}
	
	


	
	
	/*
	 *  Chargement (Deserialisation) du profil dans un fichier "Profil.tik".
	 *  
	 *  En cas de problème, un message d'erreur apparait dans une boite de dialogue.
	 */
	
	public void deserialiserProfil() {
		ObjectInputStream objIs = null ;
		try {
			String path = new File("Profil.tik").getAbsolutePath() ;
			FileInputStream fichier = new FileInputStream(path) ;
			objIs = new ObjectInputStream(fichier) ;
			listeObjectifs = (ArrayList<Objectif>) objIs.readObject() ;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur : Impossible d'importer le profil.\nErreur : " + e.toString());
		} finally {
			
			try {
				if (objIs != null) {
					objIs.close();
				}
			} catch (IOException e ) {
				System.out.println(e.getMessage()) ;
			}
		}
	}
	
	
	/*
	 *  Enregistrement (Serialisation) du profil dans un fichier "Profil.tik".
	 *  
	 *  En cas de problème, un message d'erreur apparait dans une boite de dialogue.
	 */
	public void serialiserProfil() {
		
		ObjectOutputStream objOs = null ;
		try {
			
			String path = new File("Profil.tik").getAbsolutePath() ;
			FileOutputStream fichier = new FileOutputStream(path) ;
			objOs = new ObjectOutputStream(fichier) ;
			objOs.writeObject(listeObjectifs) ;
			
						
		} catch (IOException e) {
			System.out.println(e.getMessage()) ;
		} finally {
			
			try {
				if (objOs != null) {
					objOs.flush();
					objOs.close();
				}
			} catch (IOException e ) {
				JOptionPane.showMessageDialog(null, "Erreur : Impossible d'exporter le profil.\nErreur : " + e.toString());
			}
		}
		
	}
	// Avant le passage du Garbage Collector, on enregistre le profil.
	public void finalize() {
		serialiserProfil() ;
	}

	// toString
	public String toString() {
		String retour = "Profil :\n" ;
		
		retour = retour.concat("\n") ;
		
		for (Objectif o : listeObjectifs) {
			retour = retour.concat(o.toString()) ;
		}
		
		return(retour) ;
	}


}