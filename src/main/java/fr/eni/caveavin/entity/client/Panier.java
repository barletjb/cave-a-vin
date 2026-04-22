package fr.eni.caveavin.entity.client;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Table(name = "CAV_SHOPPING_CART")
@Entity
public class Panier {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHOPPING_CART_ID", unique = true)
    private Integer id;

    @ToString.Exclude
    @Column(name = "PASWORD", length = 68, nullable = false)
    private String password;

    @Column(name = "ORDER_NUMBER", length = 200, nullable = false)
    private String numCommande;

    @Column(name = "SURNAME", precision = 2, nullable = false)
    private float prixTotal;

    @Column(name = "PAID", nullable = false)
    private boolean paye;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOPPING_CART_ID")
    @Builder.Default
    private List<LignePanier> lignePaniers = new ArrayList<>();
}
