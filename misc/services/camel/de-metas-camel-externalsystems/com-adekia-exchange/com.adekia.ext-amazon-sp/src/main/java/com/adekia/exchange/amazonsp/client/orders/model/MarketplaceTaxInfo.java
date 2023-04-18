/*
 * Selling Partner API for Orders
 * The Selling Partner API for Orders helps you programmatically retrieve order information. These APIs let you develop fast, flexible, custom applications in areas like order synchronization, order research, and demand-based decision support tools.
 *
 * OpenAPI spec version: v0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.adekia.exchange.amazonsp.client.orders.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Tax information about the marketplace.
 */
@Schema(description = "Tax information about the marketplace.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-07-02T14:17:07.399+02:00")
public class MarketplaceTaxInfo {
  @SerializedName("TaxClassifications")
  private List<TaxClassification> taxClassifications = null;

  public MarketplaceTaxInfo taxClassifications(List<TaxClassification> taxClassifications) {
    this.taxClassifications = taxClassifications;
    return this;
  }

  public MarketplaceTaxInfo addTaxClassificationsItem(TaxClassification taxClassificationsItem) {
    if (this.taxClassifications == null) {
      this.taxClassifications = new ArrayList<TaxClassification>();
    }
    this.taxClassifications.add(taxClassificationsItem);
    return this;
  }

   /**
   * A list of tax classifications that apply to the order.
   * @return taxClassifications
  **/
  @Schema(description = "A list of tax classifications that apply to the order.")
  public List<TaxClassification> getTaxClassifications() {
    return taxClassifications;
  }

  public void setTaxClassifications(List<TaxClassification> taxClassifications) {
    this.taxClassifications = taxClassifications;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MarketplaceTaxInfo marketplaceTaxInfo = (MarketplaceTaxInfo) o;
    return Objects.equals(this.taxClassifications, marketplaceTaxInfo.taxClassifications);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taxClassifications);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MarketplaceTaxInfo {\n");
    
    sb.append("    taxClassifications: ").append(toIndentedString(taxClassifications)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

