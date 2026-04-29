package fr.eni.caveavin.entity.vin;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    private String nom;

    @Column(name = "SPARKLING", nullable = false)
    private boolean petillant;

    @Column(name = "VINTAGE", length = 100, nullable = false)
    @NotBlank
    private String millesime;

    @Column(name = "QUANTITY", nullable = false)
    @NotNull
    @Min(1)
    private int quantite;

    @Column(name = "PRICE", precision = 2, nullable = false)
    @NotNull
    @Min(1)
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
