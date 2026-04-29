package fr.eni.caveavin.controller;

import fr.eni.caveavin.entity.vin.Bouteille;
import fr.eni.caveavin.service.BouteilleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'id doit etre un entier");
        }
    }

    @GetMapping("/region/{id}")
    public ResponseEntity<?> getBottlebyRegion(@PathVariable("id") String id) {

        try {
            int idRegion = Integer.parseInt(id);
            return ResponseEntity.ok(bouteilleService.chargerBouteillesParRegion(idRegion));

        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'id doit etre un entier ");
        }
    }

    @GetMapping("/couleur/{id}")
    public ResponseEntity<?> getBottlebyColor(@PathVariable("id") String id) {

        try {
            int idCouleur = Integer.parseInt(id);
            return ResponseEntity.ok(bouteilleService.chargerBouteillesParCouleur(idCouleur));

        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'id doit etre un entier ");
        }
    }

    @PostMapping
    public ResponseEntity<?> createBottle(@Valid @RequestBody Bouteille bouteille) {

        try {
            bouteilleService.ajouter(bouteille);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Erreur lors de la création");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateBottle(@Valid @RequestBody Bouteille bouteille) {

        try {
            bouteilleService.ajouter(bouteille);
            return ResponseEntity.status(HttpStatus.OK).body("Modification effectuée");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Erreur lors de la modification");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBottle(@PathVariable String id) {

        try {
            bouteilleService.supprimer(Integer.parseInt(id));
            return ResponseEntity.status(HttpStatus.OK).body("Suppression effectuée");
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("L'id doit etre un entier");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Erreur lors de la suppression");
        }
    }
}
