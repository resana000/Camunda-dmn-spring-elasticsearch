package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrganizationCheckDto {
     private boolean EGRULAddressFrod;
     private boolean inJuridicalDebtorsList;
     private boolean inInscrupuloursVedorsRegistry;
     private boolean resident;
     private Integer contractPriceDowngradePercent;
}
