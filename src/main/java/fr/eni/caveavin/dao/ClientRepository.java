package fr.eni.caveavin.dao;

import fr.eni.caveavin.entity.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
