package fr.eni.caveavin.controller;

import fr.eni.caveavin.dao.BouteilleRepository;
import fr.eni.caveavin.entity.vin.Bouteille;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/caveavin")
@RequiredArgsConstructor
public class BouteilleController {

    private final BouteilleRepository bouteilleRepository;


    @GetMapping("/bouteilles")
    public ResponseEntity<?> getAllBottles(){

        List<Bouteille> bouteilleList = bouteilleRepository.findAll();

        if(bouteilleList == null | bouteilleList.isEmpty() ){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(bouteilleList);
    }


}
