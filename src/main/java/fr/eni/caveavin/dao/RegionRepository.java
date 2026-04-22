package fr.eni.caveavin.dao;

import fr.eni.caveavin.entity.vin.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}
