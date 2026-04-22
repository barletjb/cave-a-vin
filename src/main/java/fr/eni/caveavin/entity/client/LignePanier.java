package fr.eni.caveavin.entity.client;


import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "QUANTITY", nullable = false)
    private int quantite;

    @Column(name = "PRICE", precision = 2, nullable = false)
    private float prix;
}
