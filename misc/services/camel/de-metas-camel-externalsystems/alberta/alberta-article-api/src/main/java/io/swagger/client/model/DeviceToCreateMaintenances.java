/*
 * Artikel - Warenwirtschaft (Basis)
 * Synchronisation der Artikel mit Kumavision
 *
 * OpenAPI spec version: 1.0.2
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * DeviceToCreateMaintenances
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-12-02T17:09:48.943Z[GMT]")
public class DeviceToCreateMaintenances {
  @SerializedName("maintenanceCode")
  private String maintenanceCode = null;

  @SerializedName("maintenanceInterval")
  private String maintenanceInterval = null;

  @SerializedName("lastServiceDate")
  private String lastServiceDate = null;

  @SerializedName("nextServiceDate")
  private String nextServiceDate = null;

  public DeviceToCreateMaintenances maintenanceCode(String maintenanceCode) {
    this.maintenanceCode = maintenanceCode;
    return this;
  }

   /**
   * Wartungsplanvorlagecode
   * @return maintenanceCode
  **/
  @Schema(example = "WP33334444", description = "Wartungsplanvorlagecode")
  public String getMaintenanceCode() {
    return maintenanceCode;
  }

  public void setMaintenanceCode(String maintenanceCode) {
    this.maintenanceCode = maintenanceCode;
  }

  public DeviceToCreateMaintenances maintenanceInterval(String maintenanceInterval) {
    this.maintenanceInterval = maintenanceInterval;
    return this;
  }

   /**
   * Wartungsintervall
   * @return maintenanceInterval
  **/
  @Schema(example = "2x im Jahr", description = "Wartungsintervall")
  public String getMaintenanceInterval() {
    return maintenanceInterval;
  }

  public void setMaintenanceInterval(String maintenanceInterval) {
    this.maintenanceInterval = maintenanceInterval;
  }

  public DeviceToCreateMaintenances lastServiceDate(String lastServiceDate) {
    this.lastServiceDate = lastServiceDate;
    return this;
  }

   /**
   * Letzte Prüfung
   * @return lastServiceDate
  **/
  @Schema(example = "01.06.2020", description = "Letzte Prüfung")
  public String getLastServiceDate() {
    return lastServiceDate;
  }

  public void setLastServiceDate(String lastServiceDate) {
    this.lastServiceDate = lastServiceDate;
  }

  public DeviceToCreateMaintenances nextServiceDate(String nextServiceDate) {
    this.nextServiceDate = nextServiceDate;
    return this;
  }

   /**
   * Nächste Prüfung
   * @return nextServiceDate
  **/
  @Schema(example = "01.12.2020", description = "Nächste Prüfung")
  public String getNextServiceDate() {
    return nextServiceDate;
  }

  public void setNextServiceDate(String nextServiceDate) {
    this.nextServiceDate = nextServiceDate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeviceToCreateMaintenances deviceToCreateMaintenances = (DeviceToCreateMaintenances) o;
    return Objects.equals(this.maintenanceCode, deviceToCreateMaintenances.maintenanceCode) &&
        Objects.equals(this.maintenanceInterval, deviceToCreateMaintenances.maintenanceInterval) &&
        Objects.equals(this.lastServiceDate, deviceToCreateMaintenances.lastServiceDate) &&
        Objects.equals(this.nextServiceDate, deviceToCreateMaintenances.nextServiceDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maintenanceCode, maintenanceInterval, lastServiceDate, nextServiceDate);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeviceToCreateMaintenances {\n");
    
    sb.append("    maintenanceCode: ").append(toIndentedString(maintenanceCode)).append("\n");
    sb.append("    maintenanceInterval: ").append(toIndentedString(maintenanceInterval)).append("\n");
    sb.append("    lastServiceDate: ").append(toIndentedString(lastServiceDate)).append("\n");
    sb.append("    nextServiceDate: ").append(toIndentedString(nextServiceDate)).append("\n");
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
