package fr.eni.caveavin.dao;

import fr.eni.caveavin.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {


    Utilisateur findUtilisateurByPseudo(String pseudo);

    Utilisateur findUtilisateurByPseudoAndPassword(String pseudo, String password);

}
