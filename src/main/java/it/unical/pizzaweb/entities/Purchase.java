package it.unical.pizzaweb.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.unical.pizzaweb.authentication.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "date", unique = true)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User buyer;

    @OneToMany(mappedBy = "purchase")
    @JsonManagedReference
    private List<ProductInPurchase> productInPurchaseList;

}
