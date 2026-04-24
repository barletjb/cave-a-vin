package fr.eni.caveavin.entity.client;


import fr.eni.caveavin.entity.vin.Bouteille;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Table(name = "CAV_LINE")
@Entity
public class LignePanier {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LINE_ID", unique = true)
    private Integer id;

    @Column(name = "QUANTITY")
    private int quantite;

    @Column(name = "PRICE", precision = 2)
    private float prix;

    @ManyToOne
    @JoinColumn(name = "BOTTLE_ID", nullable = false)
    private Bouteille bouteille;
}
