package fr.eni.caveavin.entity.association;


import fr.eni.caveavin.dao.PanierRepository;
import fr.eni.caveavin.entity.client.LignePanier;
import fr.eni.caveavin.entity.client.Panier;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Slf4j
class OneToManyUniTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PanierRepository panierRepository;

    private Panier panierEnDB() {
        final Panier panier = new Panier();
        final LignePanier lp = LignePanier
                .builder()
                .quantite(3)
                .prix(3 * 11.45f)
                .build();
        panier.getLignePaniers().add(lp);
        panier.setPrixTotal(lp.getPrix());

        entityManager.persist(panier);
        entityManager.flush();

        assertThat(panier.getId()).isGreaterThan(0);
        assertThat(panier.getId()).isGreaterThan(0);

        return panier;
    }


    @Test
    void test_save_nouvelleLigne_nouveauPanier(){

        final Panier panier = new Panier();
        final LignePanier lp = LignePanier
                .builder()
                .quantite(4)
                .prix(4 * 23.95f)
                .build();
        panier.getLignePaniers().add(lp);
        panier.setPrixTotal(lp.getPrix());

        final Panier panierDB = panierRepository.save(panier);

        log.info(panierDB.toString());

        Assertions.assertThat(panierDB.getId()).isNotNull();
        Assertions.assertThat(panierDB.getLignePaniers()).hasSize(1);

    }

    @Test
    void test_save_nouvelleLigne_Panier(){

        final Panier panier = panierEnDB();
        final LignePanier lp = LignePanier
                .builder()
                .quantite(10)
                .prix(10 * 23.95f)
                .build();
        panier.getLignePaniers().add(lp);
        panier.setPrixTotal(panier.getPrixTotal() + lp.getPrix());

        final Panier panierDB = panierRepository.save(panier);

        log.info(panierDB.toString());

        Assertions.assertThat(panierDB.getId()).isNotNull();
        Assertions.assertThat(panierDB.getLignePaniers()).hasSize(2);

    }


    @Test
    void test_delete(){

        final Panier panier = panierEnDB();

        panierRepository.delete(panier);

        Panier p = entityManager.find(Panier.class, panier.getId());

        org.junit.jupiter.api.Assertions.assertNull(p);

    }

    @Test
    void test_orphanRemoval(){

        final Panier panier = panierEnDB();

        List<Integer> listLignePanier = new ArrayList<>();

        for(LignePanier lp: panier.getLignePaniers()){
            listLignePanier.add(lp.getId());
        }

        panier.getLignePaniers().clear();
        panierRepository.delete(panier);

        Panier p = entityManager.find(Panier.class, panier.getId());

        org.junit.jupiter.api.Assertions.assertNull(p);


        for (Integer i : listLignePanier) {
           LignePanier lignePanier = entityManager.find(LignePanier.class, i );
            Assertions.assertThat(lignePanier).isNull();
        }



    }
}
