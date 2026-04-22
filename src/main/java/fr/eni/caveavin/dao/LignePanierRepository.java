package fr.eni.caveavin.dao;

import fr.eni.caveavin.entity.client.LignePanier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LignePanierRepository extends JpaRepository<LignePanier, Integer> {
}
