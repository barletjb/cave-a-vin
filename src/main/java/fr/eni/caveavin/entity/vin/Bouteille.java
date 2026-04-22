package fr.eni.caveavin.entity.vin;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Table(name = "CAV_BOTTLE")
@Entity
public class Bouteille {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOTTLE_ID")
    private Integer id;

    @Column(name = "NAME", length = 250, unique = true, nullable = false)
    private String nom;

    @Column(name = "SPARKLING", nullable = false)
    private boolean petillant;

    @Column(name = "VINTAGE", length = 100, nullable = false)
    private String millesime;

    @Column(name = "QUANTITY", nullable = false)
    private int quantite;

    @Column(name = "PRICE", precision = 2, nullable = false)
    private float prix;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region region;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "COLOR_ID")
    private Couleur couleur;
}
