package fr.cnam.packageDeadlines;

import java.io.Serializable;
import java.time.* ;
import java.time.format.DateTimeFormatter;

public class Objectif implements Serializable {
	
	/**
	 * Classe Objectif :
	 * 		> nom [String] : Correspond � l'intitul� de l'objectif;
	 * 		> dateLimite [LocalDateTime] : Correspond � la date de limite de l'objectif, cette date peut
	 * �tre modifiee, notament pour les objectifs r�p�t�s ;
	 * 		> repetition [CategorieRepetition] : Correspond au type de r�p�tition de l'objectif
	 * (AUCUN, si l'objectif est ponctuel ou pass�, QUOTIDIEN, HEBDOMADAIRE, etc) ;
	 * 		> serieActuelle [int] : correspond au nombre de fois cons�cutives que l'objectif a �t� atteint.
	 * (Si l'objectif ne ses r�p�te pas, cette valeur vaut 0 et ne sera jamais affich�e) ;
	 * 		> meilleureSerie [int] : meilleure (plus grande) des s�ries, garde la trace du "record".
	 * 
	 * Cette classe se repose sur les classes LocalDateTime et Duration du package java.time.
	 */
	
	private String nom ;
	private LocalDateTime dateLimite;
	private CategoriesRepetition repetition ;
	private int serieActuelle ;
	private int meilleureSerie ;
	
	
	// Constructeurs
	public Objectif() {
		nom = "Nouvel Objectif" ;
		dateLimite = LocalDateTime.of(2000, 1, 1, 00, 00) ;	
		repetition = CategoriesRepetition.AUCUNE ;
		serieActuelle = 0 ;
		meilleureSerie = 0 ;
		
	}
	
	
	// Utilis�e pour quelques test en d�but de projet, non utilis� dans la version finale.
	public Objectif(String nom , LocalDateTime dateLimite) {
		this.nom = nom ;
		this.dateLimite = dateLimite ;
		this.repetition =  CategoriesRepetition.AUCUNE ;
		serieActuelle = 0 ;
		meilleureSerie = 0 ;
	}
	
	
	// Utilis�e pour quelques test en d�but de projet, non utilis� dans la version finale.
	public Objectif(String nom , LocalDateTime dateLimite, CategoriesRepetition repetition) {
		this.nom = nom ;
		this.dateLimite = dateLimite ;
		this.repetition = repetition ;
		serieActuelle = 0 ;
		meilleureSerie = 0 ;
	}
	
	// Setteurs et Getteurs :
	public void setNom (String nom) {this.nom = nom ;}
	
	public void setDateLimite (LocalDateTime dateLimite) {this.dateLimite = dateLimite ;}
	
	public void setRepetition (CategoriesRepetition categorie) {this.repetition = categorie ;}
	
	public void setSerieActuelle (int serieActuelle) {this.serieActuelle = serieActuelle ;}
	
	public void setMeilleureSerie (int meilleureSerie) {this.meilleureSerie = meilleureSerie ;}
	
	
	
	public String getNom() {return(nom) ;}
	
	public LocalDateTime getDateLimite() {return(dateLimite) ;}
	
	public CategoriesRepetition getRepetition() {return(repetition) ;}
	
	public int getSerieActuelle() {return(serieActuelle) ;}
	
	public int getMeilleureSerie() {return(meilleureSerie) ;}

	
	// ToString :
	public String toString() {
		return("Objectif |\t" 	 + nom +
						"\t|\t"  + dateLimite.toString() + 
						"\t|\t"  + repetition + 
						"\t|\t(" + serieActuelle + 
						"/" 	 + meilleureSerie + 
						")\n") ;
	}
	
	
	
	// Methodes : Mises � jour des objectifs
	
	/*
	 * 1 - Objectif atteint :
	 * 		> Report de la date limite
	 * 		> S�rie actuelle + 1
	 * 		> Test : La s�rie actuelle d�passe-t-elle la meilleure s�rie ?
	 */
	
	public void objectifAtteint() {
		reportObjectif();
		serieActuelle ++ ;
		if(serieActuelle >= meilleureSerie) {
			meilleureSerie = serieActuelle ;
		}
	}
	
	
	
	/*
	 * 2 - Objectif Manqu� :
	 * 		> Report de la date limite
	 * 		> S�rie actuelle ppv 0
	 * 		> (Test de meilleure s�rie non n�cessaire : d�j� effectu� lorsque
	 * 		l'objectif est atteint pr�c�demment)
	 */
	
	public void objectifManque() {
		reportObjectif() ;
		setSerieActuelle(0) ;
	}
	
	
	
	
	// Permet de voir si l'objectif est pass� ou non. Renvoie un booleen.
	public boolean estPasse() {
		return(dateLimite.isBefore(LocalDateTime.now())) ;
	}
	
	
	
	
	/*
	 * Cette m�thode permet de d�placer l'�ch�ance d'un objectif r�p�t� d'au moins (boucle do) 
	 * un(e) jour/semaine/mois/ann�e. NB : la m�thode continue de reporter l'�ch�ance jusqu'� ce
	 * que celle-ci d�passe la date actuelle.
	 */
	public void reportObjectif() {
		 do {
			switch (repetition) {
			case QUOTIDIEN :
				dateLimite = dateLimite.plusDays(1) ;
				break ;
			case HEBDOMADAIRE :
				dateLimite = dateLimite.plusWeeks(1) ;
				break ;
			case MENSUEL :
				dateLimite = dateLimite.plusMonths(1) ;
				break ;
			case ANNUEL :
				dateLimite = dateLimite.plusYears(1) ;
				break ;
			default :
				break ;
			}
		} while(estPasse()) ;
	}
	
	
	
	// Renvoie la valeur absolue d'un nombre, seulement utilis�e dans la m�thode tempsRestant().
	public long valeurAbsolue(long nombre) {
		if (nombre >= 0) { return(nombre) ; }
		else { return( - nombre) ; }
	}
	
	
	/*
	 * Permet de tester si l'utilisateur peut mettre son objectif r�p�t� � jour, c'est � dire si le moment quand cette m�thode 
	 * est appell�e est apr�s l'�ch�ance moins un(e) jous/semaine/mois/ann�e. Il n'est pas n�cessaire de tester si ce moment 
	 * tombe avant l'�ch�ance de l'objectif (celui-ci est test� avant et repouss� au besoin).
	 * 
	 */
	public boolean estBonnePeriode() {
		
		LocalDateTime dateDebut;
		
		switch (repetition) {
		case QUOTIDIEN :
			dateDebut = dateLimite.minusDays(1) ;
			break ;
		case HEBDOMADAIRE :
			dateDebut = dateLimite.minusWeeks(1) ;
			break ;
		case MENSUEL :
			dateDebut = dateLimite.minusMonths(1) ;
			break ;
		case ANNUEL :
			dateDebut = dateLimite.minusYears(1) ;
			break ;
		default :
			return(false) ;
		}
		
		return LocalDateTime.now().isAfter(dateDebut) ;
	}
	
	
	
	/* Affiche le temps restant au format : J - XX jour(s) XX heure(s) XX minute(s) XX seconde(s) dans un String.
	 * Utilis� dans le PanelObjectif.
	 */
	public String tempsRestant() {
		
		Duration delai = Duration.between(LocalDateTime.now(), dateLimite) ;

		long[] duree = {delai.toDays(),
						delai.toHours() % 24,
						delai.toMinutes() % 60,
						delai.toSeconds() % 60} ;
		long valeur ;
		
		String[] unites = {	"jour" ,
							"heure" ,
							"minute" ,
							"seconde"} ;
		
		String retour = "J" ;

		if (duree[3] >= 0) {
			retour = retour.concat(" - ") ;
		} else {
			retour = retour.concat(" + ") ;
		}
		/*
		 * NB : On Affiche les champs en fonctions de ceux n�c�ssaires,
		 * On ajoute les "s" en cons�quence.
		 */
		for(int i = 0 ; i < 4; i++) {
			valeur = valeurAbsolue(duree[i]) ;
			if(valeur > 0 ) {
				retour = retour.concat(valeur + " " + unites[i]); 
			}
			if (valeur >= 1 ) {
				retour = retour.concat("s"); 
			}
			retour = retour.concat(" "); 
		}
 		return(retour) ;
		
	}
	
	
	
	/* Renvoie la date de l'�ch�ance au format jj/mm/aaaa dans un String.
	 * Utilis� dans le PanelObjectif.
	 */
	public String getDateFormat() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy") ;
		return(dateLimite.format(format)) ;
	}
	
	
	
	/* Renvoie l'horraire de l'�ch�ance au format hh:mm dans un String.
	 * Utilis� dans le PanelObjectif.
	 */
	public String getHeureFormat() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss") ;
		return(dateLimite.format(format)) ;
	}
	

	
}