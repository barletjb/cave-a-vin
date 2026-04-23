package fr.eni.caveavin.dao;

import fr.eni.caveavin.entity.Proprio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProprioRepository extends JpaRepository<Proprio, String> {
}
