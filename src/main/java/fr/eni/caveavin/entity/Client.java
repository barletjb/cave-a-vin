package fr.eni.caveavin.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Table(name = "CLIENT")
@Entity
public class Client {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "PSEUDO", length = 255 ,nullable = false)
    private String pseudo;

    @ToString.Exclude
    @Column(name = "PASWORD", length = 68 ,nullable = false)
    private String password;

    @Column(name = "NAME", length = 90 ,nullable = false)
    private String nom;

    @Column(name = "SURNAME", length = 150 ,nullable = false)
    private String prenom;

}
