package fr.eni.caveavin.dao;

import fr.eni.caveavin.entity.vin.Bouteille;
import fr.eni.caveavin.entity.vin.Couleur;
import fr.eni.caveavin.entity.vin.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BouteilleRepository extends JpaRepository<Bouteille, Integer> {

    List<Bouteille> findBouteillesByRegion(Region region);

    List<Bouteille> findBouteillesByCouleur(Couleur couleur);

    List<Bouteille> findByRegion(Region rDB);

    List<Bouteille> findByCouleur(Couleur cDB);

    Bouteille findByNom(String nom);
}
