package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class OrganizationCheckRequest {
     private boolean EGRULAddressFrod;
     private boolean inJuridicalDebtorsList;
     private boolean inInscrupuloursVedorsRegistry;
     private boolean resident;
     private Integer contractPriceDowngradePercent;
}
