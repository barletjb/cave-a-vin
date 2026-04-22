package fr.eni.caveavin.dao;

import fr.eni.caveavin.entity.vin.Bouteille;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BouteilleRepository extends JpaRepository<Bouteille, Integer> {
}
