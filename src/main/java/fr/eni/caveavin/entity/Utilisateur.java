package fr.eni.caveavin.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@SuperBuilder
@Table(name = "CAV_USER")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Utilisateur {

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

}
