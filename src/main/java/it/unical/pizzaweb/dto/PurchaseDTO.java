package it.unical.pizzaweb.dto;

import it.unical.pizzaweb.authentication.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class PurchaseDTO {

    private Long id;
    private Date date;
    private UserDTO buyer;
    private Double price;
    private List<ProductInPurchaseDTO> productInPurchaseList;

}
