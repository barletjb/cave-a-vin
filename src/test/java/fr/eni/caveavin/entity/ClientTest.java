package fr.eni.caveavin.entity;

import fr.eni.caveavin.dao.ClientRepository;
import fr.eni.caveavin.entity.client.Client;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
@Slf4j
class ClientTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test_sauverClient(){

        Client client1 = Client.builder()
                .pseudo("PSEUDO")
                .password("123")
                .nom("TEST_NOM")
                .prenom("TEST_PRENOM")
                .build();

        final Client clientDB = clientRepository.save(client1);
        log.info(clientDB.toString());

        org.assertj.core.api.Assertions.assertThat(clientDB).isNotNull();
        org.assertj.core.api.Assertions.assertThat(clientDB).isEqualTo(client1);

    }

    @Test
    void test_supprimerClient(){

        Client client2 = Client.builder()
                .pseudo("PSEUDO2")
                .password("123")
                .nom("TEST_NOM2")
                .prenom("TEST_PRENOM2")
                .build();

        entityManager.persist(client2);
        entityManager.flush();

        clientRepository.delete(client2);

        Client c = entityManager.find(Client.class, client2.getPseudo());

        Assertions.assertNull(c);

    }


}