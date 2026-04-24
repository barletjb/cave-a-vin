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

    @Column(name = "ORDER_NUMBER", length = 200, nullable = true)
    private String numCommande;

    @Column(name = "SURNAME", precision = 2, nullable = true)
    private float prixTotal;

    @Column(name = "PAID", nullable = true)
    private boolean paye;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOPPING_CART_ID")
    @Builder.Default
    private List<LignePanier> lignesPanier = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CLIENT_PSEUDO", nullable = false)
    private Client client;
}
