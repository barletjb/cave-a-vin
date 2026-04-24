package fr.eni.caveavin.jpql;


import fr.eni.caveavin.dao.BouteilleRepository;
import fr.eni.caveavin.dao.ClientRepository;
import fr.eni.caveavin.dao.PanierRepository;
import fr.eni.caveavin.entity.Proprio;
import fr.eni.caveavin.entity.Utilisateur;
import fr.eni.caveavin.entity.client.Client;
import fr.eni.caveavin.entity.client.LignePanier;
import fr.eni.caveavin.entity.client.Panier;
import fr.eni.caveavin.entity.vin.Bouteille;
import fr.eni.caveavin.entity.vin.Couleur;
import fr.eni.caveavin.entity.vin.Region;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
class TestManyToOnePanierClient {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    PanierRepository panierRepository;

    @Autowired
    BouteilleRepository bouteilleRepository;

    @Autowired
    ClientRepository clientRepository;

    @BeforeEach
    void initDB() {
        final List<Couleur> couleurs = new ArrayList<>();
        couleurs.add(Couleur
                .builder()
                .nom("Blanc")
                .build());
        couleurs.add(Couleur
                .builder()
                .nom("Rouge")
                .build());

        couleurs.forEach(item -> {
            entityManager.persist(item);
            assertThat(item.getId()).isGreaterThan(0);
        });
        entityManager.flush();

        final List<Region> regions = new ArrayList<>();
        regions.add(Region
                .builder()
                .nom("Pays de la Loire")
                .build());

        regions.add(Region
                .builder()
                .nom("Grand Est")
                .build());

        regions.forEach(item -> {
            entityManager.persist(item);
            assertThat(item.getId()).isGreaterThan(0);
        });
        entityManager.flush();

        final List<Bouteille> bouteilles = new ArrayList<>();
        bouteilles.add(Bouteille
                .builder()
                .nom("DOMAINE ENI Ecole")
                .millesime("2022")
                .prix(11.45f)
                .quantite(1298)
                .region(regions.get(0))
                .couleur(couleurs.get(0))
                .build());

        bouteilles.add(Bouteille
                .builder()
                .nom("DOMAINE ENI Service")
                .millesime("2015")
                .prix(23.95f)
                .quantite(2998)
                .region(regions.get(1))
                .couleur(couleurs.get(1))
                .build());

        bouteilles.forEach(item -> {
            entityManager.persist(item);
            assertThat(item.getId()).isGreaterThan(0);
        });
        entityManager.flush();

    }

    private List<Panier> jeuDeDonnees() {
        final List<Bouteille> bouteilles = bouteilleRepository.findAll();
        assertThat(bouteilles).isNotNull();
        assertThat(bouteilles).isNotEmpty();
        assertThat(bouteilles.size()).isEqualTo(2);

        final List<Panier> paniers = new ArrayList<>();

        final Panier p1 = new Panier();
        int qte1 = 3;
        final Bouteille b1 = bouteilles.get(0);
        final LignePanier lp1 = LignePanier
                .builder()
                .bouteille(b1)
                .quantite(qte1)
                .prix(qte1 * b1.getPrix())
                .build();
        p1.getLignesPanier().add(lp1);
        p1.setPrixTotal(lp1.getPrix());
        paniers.add(p1);

		final Panier p2 = new Panier();
        int qte2 = 10;
        final Bouteille b2 = bouteilles.get(1);
        final LignePanier lp2 = LignePanier
                .builder()
                .bouteille(b2)
                .quantite(qte2)
                .prix(qte2 * b2.getPrix())
                .build();
        p2.getLignesPanier().add(lp2);
        p2.setPrixTotal(lp2.getPrix());
        paniers.add(p2);

        return paniers;
    }

	private Client jeudeDonnee(){

		final Client client = Client
				.builder()
				.pseudo("natalieportman@email.fr")
				.password("MarsAttacks!")
				.nom("Portman")
				.prenom("Natalie")
				.build();

		entityManager.persist(client);
		entityManager.flush();

		return client;

	}

    @Test
	void test_save_unPanier(){

		Panier panier = jeuDeDonnees().getFirst();

		Client clientDB = jeudeDonnee();

		panier.setClient(clientDB);

		Panier panierDB = panierRepository.save(panier);

		log.info(panier.toString());

		Assertions.assertThat(panierDB).isNotNull();
	}

	@Test
	void test_save_paniers_unClient(){

		List<Panier> listePaniers = jeuDeDonnees();

		Client clientDB = jeudeDonnee();

		listePaniers.forEach(panier -> {
			panier.setClient(clientDB);
		});

		List<Panier> listePaniersDB = panierRepository.saveAll(listePaniers);

		listePaniersDB.forEach(panier -> {
			Assertions.assertThat(panier.getClient()).isEqualTo(clientDB);
		});

	}

	@Test
	void test_delete(){

		List<Panier> listePaniers = jeuDeDonnees();
		List<Integer> paniersId = new ArrayList<>();

		Client clientDB = jeudeDonnee();

		listePaniers.forEach(panier -> {
			panier.setClient(clientDB);
			entityManager.persist(panier);
			paniersId.add(panier.getId());

		});

		entityManager.flush();

		clientRepository.delete(clientDB);

		List<Panier> paniersDB = new ArrayList<>();

		paniersId.forEach(id -> {
			paniersDB.add(entityManager.find(Panier.class, id));
		});

		paniersDB.forEach(panier -> {
			Assertions.assertThat(panier.getId()).isNotNull();
		});

	}

}
