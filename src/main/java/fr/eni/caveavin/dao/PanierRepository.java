package fr.eni.caveavin.dao;

import fr.eni.caveavin.entity.client.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PanierRepository extends JpaRepository<Panier, Integer> {


    @Query("SELECT p FROM Panier p WHERE p.numCommande IS NULL AND p.client= :idClient")
    List<Panier> findEmptyPanier(@Param("idClient") String pseudo);


    List<Panier> findPanierByClientAndNumCommandeNull(String numCommande);

}
