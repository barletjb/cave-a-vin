package fr.eni.caveavin.dao;

import fr.eni.caveavin.entity.client.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier, Integer> {
}
