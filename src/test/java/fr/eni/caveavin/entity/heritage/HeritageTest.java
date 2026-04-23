package fr.eni.caveavin.entity.heritage;

import fr.eni.caveavin.dao.ClientRepository;
import fr.eni.caveavin.dao.ProprioRepository;
import fr.eni.caveavin.dao.UtilisateurRepository;
import fr.eni.caveavin.entity.Proprio;
import fr.eni.caveavin.entity.Utilisateur;
import fr.eni.caveavin.entity.client.Client;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@DataJpaTest
public class HeritageTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    ProprioRepository proprioRepository;

    @Autowired
    ClientRepository clientRepository;

    @BeforeEach
    public void initDB() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
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
                .siret("12345678901234")
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
    }

    //TODO
}
