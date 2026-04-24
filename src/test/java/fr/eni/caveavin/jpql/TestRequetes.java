package fr.eni.caveavin.jpql;


import fr.eni.caveavin.dao.BouteilleRepository;
import fr.eni.caveavin.dao.UtilisateurRepository;
import fr.eni.caveavin.entity.Proprio;
import fr.eni.caveavin.entity.Utilisateur;
import fr.eni.caveavin.entity.client.Client;
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

@DataJpaTest
@Slf4j
class TestRequetes {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
    UtilisateurRepository utilisateurRepository;

	@Autowired
    BouteilleRepository bouteilleRepository;

	Region paysDeLaLoire;
	Couleur blanc;
	List<Bouteille> bouteilles;

	@BeforeEach
	void initDB() {
		jeuDeDonneesBouteilles();
		jeuDeDonneesUtilisateur();
	}

	private void jeuDeDonneesBouteilles() {
		final Couleur rouge = Couleur
				.builder()
				.nom("Rouge")
				.build();

		blanc = Couleur
				.builder()
				.nom("Blanc")
				.build();

		final Couleur rose = Couleur
				.builder()
				.nom("Rosé")
				.build();

		entityManager.persist(rouge);
		entityManager.persist(blanc);
		entityManager.persist(rose);
		entityManager.flush();

		final Region grandEst = Region
				.builder()
				.nom("Grand Est")
				.build();

		paysDeLaLoire = Region
				.builder()
				.nom("Pays de la Loire")
				.build();

		final Region nouvelleAquitaine = Region
				.builder()
				.nom("Nouvelle-Aquitaine")
				.build();

		entityManager.persist(grandEst);
		entityManager.persist(paysDeLaLoire);
		entityManager.persist(nouvelleAquitaine);
		entityManager.flush();

		bouteilles = new ArrayList<>();
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

		bouteilles.forEach(e -> {
			entityManager.persist(e);
			// Vérification de l'identifiant
			assertThat(e.getId()).isGreaterThan(0);
		});
		entityManager.flush();

	}

	private void jeuDeDonneesUtilisateur() {
		final List<Utilisateur> utilisateurs = new ArrayList<>();
		utilisateurs.add(Utilisateur
				.builder()
				.pseudo("harrisonford@email.fr")
				.password("IndianaJones3")
				.nom("Ford")
				.prenom("Harrison")
				.build());

		utilisateurs.add(Proprio
				.builder()
				.pseudo("georgelucas@email.fr")
				.password("Réalisateur&Producteur")
				.nom("Lucas")
				.prenom("George")
				.build());

		utilisateurs.add(Client
				.builder()
				.pseudo("natalieportman@email.fr")
				.password("MarsAttacks!")
				.nom("Portman")
				.prenom("Natalie")
				.build());

		// Contexte de la DB
		utilisateurs.forEach(e -> {
			entityManager.persist(e);
		});
		entityManager.flush();
	}

	@Test
	void test_findBouteillesByRegion(){

		List<Bouteille> listBouteilles = bouteilleRepository.findBouteillesByRegion(paysDeLaLoire);

		log.info(listBouteilles.toString());

		Assertions.assertThat(listBouteilles).hasSize(3);


	}

	@Test
	void test_findBouteillesByCouleur(){

		List<Bouteille> listBouteilles = bouteilleRepository.findBouteillesByCouleur(blanc);

		log.info(listBouteilles.toString());

		Assertions.assertThat(listBouteilles).hasSize(2);

	}

	@Test
	void test_findUtilisiteurByPseudo(){

		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudo("harrisonford@email.fr");

		log.info(utilisateur.toString());

		Assertions.assertThat(utilisateur).isNotNull();

	}

	@Test
	void test_findUtilisiteurByPseudoAndPassword(){

		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByPseudoAndPassword("harrisonford@email.fr", "IndianaJones3");

		log.info(utilisateur.toString());

		Assertions.assertThat(utilisateur).isNotNull();

	}

}
