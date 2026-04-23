package fr.eni.caveavin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder
@Table(name = "PROPRIO")
@Entity
public class Proprio extends Utilisateur{

    @Column(name = "SIRET", unique = true, nullable = false)
    private String siret;
}
