package fr.eni.caveavin.dao;

import fr.eni.caveavin.entity.vin.Couleur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouleurRepository extends JpaRepository<Couleur, Integer> {
}
