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

    @Query(value = "SELECT * FROM CAV_SHOPPING_CART WHERE ORDER_NUMBER IS NOT NULL AND CLIENT_ID = :idClient", nativeQuery = true)
    List<Panier> findClientNotEmptyPaniers(@Param("idClient") String pseudo);

    List<Panier> findPanierByClientAndNumCommandeNotNull(String numCommande);

}
