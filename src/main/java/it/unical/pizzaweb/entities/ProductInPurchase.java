package it.unical.pizzaweb.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "product_in_purchase")
public class ProductInPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Pizza pizza;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Purchase purchase;

}
