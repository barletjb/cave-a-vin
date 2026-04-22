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
@Table(name = "CAV_ADDRESS")
@Entity
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "ADRESS_ID", unique = true)
    private Integer id;

    @Column(name = "STREET", length = 250, nullable = false)
    private String rue;

    @Column(name = "POSTAL_CODE", length = 5, nullable = false)
    private String codePostal;

    @Column(name = "CITY", length = 250, nullable = false)
    private String city;
}
