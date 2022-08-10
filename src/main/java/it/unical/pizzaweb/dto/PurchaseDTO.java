package it.unical.pizzaweb.dto;

import it.unical.pizzaweb.authentication.entities.User;
import it.unical.pizzaweb.entities.ProductInPurchase;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class PurchaseDTO {

    private Long id;
    private Date date;
    private User buyer;
    private Integer quantity;
    private List<ProductInPurchase> productInPurchaseList;

}
