package fr.eni.caveavin.service;

import fr.eni.caveavin.entity.vin.Bouteille;

import java.util.List;


public interface BouteilleService {
	List<Bouteille> chargerToutesBouteilles();
	
	Bouteille chargerBouteilleParId(int idBouteille);

	List<Bouteille> chargerBouteillesParRegion(int idRegion);

	List<Bouteille> chargerBouteillesParCouleur(int idCouleur);
	
	void ajouter(Bouteille bouteille);

	void supprimer(int idBouteille);
}
