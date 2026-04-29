package fr.eni.caveavin.controller;

import fr.eni.caveavin.dao.BouteilleRepository;
import fr.eni.caveavin.entity.vin.Bouteille;
import fr.eni.caveavin.service.BouteilleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/caveavin/bouteilles")
@RequiredArgsConstructor
public class BouteilleController {

    private final BouteilleService bouteilleService;


    @GetMapping
    public ResponseEntity<?> getAllBottles() {

        List<Bouteille> bouteilleList = bouteilleService.chargerToutesBouteilles();

        if (bouteilleList == null || bouteilleList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(bouteilleList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBottle(@PathVariable("id") String id) {

        try {
            int idBouteille = Integer.parseInt(id);
            return ResponseEntity.ok(bouteilleService.chargerBouteilleParId(idBouteille));

        } catch (NumberFormatException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'id doit etre un entier ");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
