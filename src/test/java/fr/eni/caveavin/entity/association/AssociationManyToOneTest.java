package fr.eni.caveavin.entity.association;


import fr.eni.caveavin.dao.BouteilleRepository;
import fr.eni.caveavin.entity.vin.Bouteille;
import fr.eni.caveavin.entity.vin.Couleur;
import fr.eni.caveavin.entity.vin.Region;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@Slf4j
class AssociationManyToOneTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BouteilleRepository bouteilleRepository;


    //Color
    private Couleur rouge;
    private Couleur blanc;
    private Couleur rose;

    //Region
    private Region grandEst;
    private Region paysDeLaLoire;
    private Region nouvelleAquitaine;


    @BeforeEach
    void initDB() {

        rouge = Couleur.builder()
                .nom("rouge")
                .build();
        blanc = Couleur.builder()
                .nom("blanc")
                .build();
        rose = Couleur.builder()
                .nom("rosé")
                .build();

        grandEst = Region.builder()
                .nom("Grand Est")
                .build();

        paysDeLaLoire = Region.builder()
                .nom("Pays de la Loire")
                .build();

        nouvelleAquitaine = Region.builder()
                .nom("Nouvelle Aquitaine")
                .build();

        entityManager.persist(rose);
        entityManager.persist(rouge);
        entityManager.persist(blanc);

        entityManager.persist(nouvelleAquitaine);
        entityManager.persist(grandEst);
        entityManager.persist(paysDeLaLoire);
        entityManager.flush();
    }

    private List<Bouteille> jeuDeDonnees() {
        List<Bouteille> bouteilles = new ArrayList<>();
        bouteilles.add(Bouteille
                .builder()
                .nom("Blanc du DOMAINE ENI Ecole")
                .millesime("2022")
                .prix(23.95f)
                .quantite(1298)
                .region(paysDeLaLoire)
                .couleur(blanc)
                .build());
        bouteilles.add(Bouteille
                .builder()
                .nom("Rouge du DOMAINE ENI Ecole")
                .millesime("2018")
                .prix(11.45f)
                .quantite(987)
                .region(paysDeLaLoire)
                .couleur(rouge)
                .build());
        bouteilles.add(Bouteille
                .builder()
                .nom("Blanc du DOMAINE ENI Service")
                .millesime("2022")
                .prix(34)
                .petillant(true)
                .quantite(111)
                .region(grandEst)
                .couleur(blanc)
                .build());
        bouteilles.add(Bouteille
                .builder()
                .nom("Rouge du DOMAINE ENI Service")
                .millesime("2012")
                .prix(8.15f)
                .quantite(344)
                .region(paysDeLaLoire)
                .couleur(rouge)
                .build());
        bouteilles.add(Bouteille
                .builder()
                .nom("Rosé du DOMAINE ENI")
                .millesime("2020")
                .prix(33)
                .quantite(1987)
                .region(nouvelleAquitaine)
                .couleur(rose)
                .build());
        return bouteilles;
    }

    @Test
    void test_save(){

        Bouteille bouteille = Bouteille.builder()
                .nom("DOMAINE ENI TEST")
                .petillant(true)
                .millesime("2020")
                .quantite(100)
                .prix(10f)
                .region(nouvelleAquitaine)
                .couleur(rose)
                .build();

        Bouteille bouteilleDB = bouteilleRepository.save(bouteille);

        log.info(bouteilleDB.toString());

        Assertions.assertThat(bouteilleDB.getId()).isGreaterThan(0);

    }

    @Test
    void test_save_bouteilles_regions_couleurs(){

        List<Bouteille> bouteilles = jeuDeDonnees();

        for (Bouteille b : bouteilles){
            bouteilleRepository.save(b);

            Assertions.assertThat(b).isNotNull();
            Assertions.assertThat(b.getCouleur()).isNotNull();
            Assertions.assertThat(b.getRegion()).isNotNull();

            log.info(b.toString());
        }
    }

    @Test
    void test_delete() {
        Bouteille bouteille = Bouteille.builder()
                .nom("Rouge du DOMAINE ENI Service")
                .millesime("2012")
                .prix(8.15f)
                .quantite(344)
                .region(paysDeLaLoire)
                .couleur(rouge)
                .build();

        entityManager.persist(bouteille);
        entityManager.flush();

        Integer couleurId = bouteille.getCouleur().getId();
        Integer regionId = bouteille.getRegion().getId();

        bouteilleRepository.delete(bouteille);

        Couleur couleurDB = entityManager.find(Couleur.class, couleurId);
        Region regionDB = entityManager.find(Region.class, regionId);

        Assertions.assertThat(couleurDB).isNotNull();
        Assertions.assertThat(regionDB).isNotNull();
    }
}
