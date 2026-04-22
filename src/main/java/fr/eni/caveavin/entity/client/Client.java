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
@Table(name = "CAV_CLIENT")
@Entity
public class Client {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "PSEUDO", length = 255 ,nullable = false, unique = true)
    private String pseudo;

    @ToString.Exclude
    @Column(name = "PASWORD", length = 68 ,nullable = false)
    private String password;

    @Column(name = "NAME", length = 90 ,nullable = false)
    private String nom;

    @Column(name = "SURNAME", length = 150 ,nullable = false)
    private String prenom;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "adress_id")
    private Adresse adresse;

}
