package fr.eni.caveavin.entity.association;


import fr.eni.caveavin.dao.AdresseRepository;
import fr.eni.caveavin.dao.ClientRepository;
import fr.eni.caveavin.entity.client.Adresse;
import fr.eni.caveavin.entity.client.Client;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
@Slf4j
class AssociationClientTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test_sauverClient(){

        Adresse adresse = Adresse.builder()
                .rue("Rue Test")
                .city("VILLE Test")
                .codePostal("12345")
                .build();

        Client client = Client.builder()
                .pseudo("PSEUDO")
                .password("123")
                .nom("TEST_NOM")
                .prenom("TEST_PRENOM")
                .adresse(adresse)
                .build();

        final Client clientDB = clientRepository.save(client);
        log.info(clientDB.toString());

        org.assertj.core.api.Assertions.assertThat(clientDB.getAdresse()).isNotNull();

    }

    @Test
    void test_deleteClient(){

        Adresse adresse = Adresse.builder()
                .rue("Rue Test")
                .city("VILLE Test")
                .codePostal("12345")
                .build();

        Client client = Client.builder()
                .pseudo("PSEUDO")
                .password("123")
                .nom("TEST_NOM")
                .prenom("TEST_PRENOM")
                .adresse(adresse)
                .build();

        entityManager.persist(client);
        entityManager.flush();

        clientRepository.delete(client);

        Client c = entityManager.find(Client.class, client.getPseudo());

        Assertions.assertNull(c);

    }

    @Test
    void test_orphanRemoval(){

        Adresse adresse = Adresse.builder()
                .rue("Rue Test")
                .city("VILLE Test")
                .codePostal("12345")
                .build();

        Client client = Client.builder()
                .pseudo("PSEUDO")
                .password("123")
                .nom("TEST_NOM")
                .prenom("TEST_PRENOM")
                .adresse(adresse)
                .build();

        entityManager.persist(client);
        entityManager.flush();

        client.setAdresse(null);
        clientRepository.delete(client);

        Adresse add = entityManager.find(Adresse.class, adresse.getId());

        Assertions.assertNull(add);

    }

}
