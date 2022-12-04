package it.unical.pizzaweb.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "image")
    private String image;

    @ManyToMany
    @JoinTable(
            name = "pizza_ingredient",
            joinColumns = { @JoinColumn(name = "pizza_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "ingredient_id", referencedColumnName = "id") }
    )
    private List<Ingredient> ingredients;

}
