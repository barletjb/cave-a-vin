package fr.eni.caveavin.jpql;

import fr.eni.caveavin.entity.client.LignePanier;
import fr.eni.caveavin.entity.vin.Bouteille;
import fr.eni.caveavin.entity.vin.Couleur;
import fr.eni.caveavin.entity.vin.Region;
import fr.eni.caveavin.dao.LignePanierRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

@Slf4j
@DataJpaTest
public class TestManyToOneLignePanierBouteille {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	LignePanierRepository lignePanierRepository;
	private Bouteille b1;

	@BeforeEach
	public void initDB() {
		final Couleur blanc = Couleur
				.builder()
				.nom("Blanc")
				.build();

		entityManager.persist(blanc);

		final Region paysDeLaLoire = Region
				.builder()
				.nom("Pays de la Loire")
				.build();

		entityManager.persist(paysDeLaLoire);
		entityManager.flush();

		b1 = Bouteille
				.builder()
				.nom("DOMAINE ENI Ecole")
				.millesime("2022")
				.prix(23.95f)
				.quantite(1298)
				.region(paysDeLaLoire)
				.couleur(blanc)
				.build();
		entityManager.persist(b1);
		entityManager.flush();
	}

	@Test
	void test_save(){

		LignePanier lp = LignePanier.builder()
				.bouteille(b1)
				.build();

		lignePanierRepository.save(lp);

		log.info(lp.toString());

		Assertions.assertThat(lp.getBouteille()).isEqualTo(b1);

	}


	@Test
	void test_delete(){

		LignePanier lp = LignePanier.builder()
				.bouteille(b1)
				.build();

		entityManager.persist(lp);
		entityManager.flush();

		log.info(lp.toString());

		Integer id = b1.getId();

		lignePanierRepository.delete(lp);

		Bouteille bouteilleDB = entityManager.find(Bouteille.class, id);

		Assertions.assertThat(bouteilleDB).isNotNull();

	}
}
