/*
 * Fulfillment API
 * Use the Fulfillment API to complete the process of packaging, addressing, handling, and shipping each order on behalf of the seller, in accordance with the payment method and timing specified at checkout.
 *
 * The version of the OpenAPI document: v1.19.7
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package de.metas.camel.externalsystems.ebay.api.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import de.metas.camel.externalsystems.ebay.api.model.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * This type is used by the payment dispute methods, and is relevant if the buyer will be returning the item to the seller.
 */
@ApiModel(description = "This type is used by the payment dispute methods, and is relevant if the buyer will be returning the item to the seller.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-07-20T14:45:55.981728+02:00[Europe/Berlin]")
public class ReturnAddress
{
	public static final String SERIALIZED_NAME_ADDRESS_LINE1 = "addressLine1";
	@SerializedName(SERIALIZED_NAME_ADDRESS_LINE1)
	private String addressLine1;

	public static final String SERIALIZED_NAME_ADDRESS_LINE2 = "addressLine2";
	@SerializedName(SERIALIZED_NAME_ADDRESS_LINE2)
	private String addressLine2;

	public static final String SERIALIZED_NAME_CITY = "city";
	@SerializedName(SERIALIZED_NAME_CITY)
	private String city;

	public static final String SERIALIZED_NAME_COUNTRY = "country";
	@SerializedName(SERIALIZED_NAME_COUNTRY)
	private String country;

	public static final String SERIALIZED_NAME_COUNTY = "county";
	@SerializedName(SERIALIZED_NAME_COUNTY)
	private String county;

	public static final String SERIALIZED_NAME_FULL_NAME = "fullName";
	@SerializedName(SERIALIZED_NAME_FULL_NAME)
	private String fullName;

	public static final String SERIALIZED_NAME_POSTAL_CODE = "postalCode";
	@SerializedName(SERIALIZED_NAME_POSTAL_CODE)
	private String postalCode;

	public static final String SERIALIZED_NAME_PRIMARY_PHONE = "primaryPhone";
	@SerializedName(SERIALIZED_NAME_PRIMARY_PHONE)
	private Phone primaryPhone;

	public static final String SERIALIZED_NAME_STATE_OR_PROVINCE = "stateOrProvince";
	@SerializedName(SERIALIZED_NAME_STATE_OR_PROVINCE)
	private String stateOrProvince;

	public ReturnAddress addressLine1(String addressLine1)
	{

		this.addressLine1 = addressLine1;
		return this;
	}

	/**
	 * The first line of the street address.
	 * 
	 * @return addressLine1
	 **/
	@javax.annotation.Nullable
	@ApiModelProperty(value = "The first line of the street address.")

	public String getAddressLine1()
	{
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1)
	{
		this.addressLine1 = addressLine1;
	}

	public ReturnAddress addressLine2(String addressLine2)
	{

		this.addressLine2 = addressLine2;
		return this;
	}

	/**
	 * The second line of the street address. This line is not always necessarily, but is often used for apartment number or suite number, or other relevant information that can not fit on the first line.
	 * 
	 * @return addressLine2
	 **/
	@javax.annotation.Nullable
	@ApiModelProperty(value = "The second line of the street address. This line is not always necessarily, but is often used for apartment number or suite number, or other relevant information that can not fit on the first line.")

	public String getAddressLine2()
	{
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2)
	{
		this.addressLine2 = addressLine2;
	}

	public ReturnAddress city(String city)
	{

		this.city = city;
		return this;
	}

	/**
	 * The city of the return address.
	 * 
	 * @return city
	 **/
	@javax.annotation.Nullable
	@ApiModelProperty(value = "The city of the return address.")

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public ReturnAddress country(String country)
	{

		this.country = country;
		return this;
	}

	/**
	 * The country&#39;s two-digt, ISO 3166-1 country code. See the enumeration type for a country&#39;s value. For implementation help, refer to &lt;a href&#x3D;&#39;https://developer.ebay.com/api-docs/sell/fulfillment/types/ba:CountryCodeEnum&#39;&gt;eBay API documentation&lt;/a&gt;
	 * 
	 * @return country
	 **/
	@javax.annotation.Nullable
	@ApiModelProperty(value = "The country's two-digt, ISO 3166-1 country code. See the enumeration type for a country's value. For implementation help, refer to <a href='https://developer.ebay.com/api-docs/sell/fulfillment/types/ba:CountryCodeEnum'>eBay API documentation</a>")

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public ReturnAddress county(String county)
	{

		this.county = county;
		return this;
	}

	/**
	 * The county of the return address. Counties are not applicable to all countries.
	 * 
	 * @return county
	 **/
	@javax.annotation.Nullable
	@ApiModelProperty(value = "The county of the return address. Counties are not applicable to all countries.")

	public String getCounty()
	{
		return county;
	}

	public void setCounty(String county)
	{
		this.county = county;
	}

	public ReturnAddress fullName(String fullName)
	{

		this.fullName = fullName;
		return this;
	}

	/**
	 * The full name of return address owner.
	 * 
	 * @return fullName
	 **/
	@javax.annotation.Nullable
	@ApiModelProperty(value = "The full name of return address owner.")

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public ReturnAddress postalCode(String postalCode)
	{

		this.postalCode = postalCode;
		return this;
	}

	/**
	 * The postal code of the return address.
	 * 
	 * @return postalCode
	 **/
	@javax.annotation.Nullable
	@ApiModelProperty(value = "The postal code of the return address.")

	public String getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	public ReturnAddress primaryPhone(Phone primaryPhone)
	{

		this.primaryPhone = primaryPhone;
		return this;
	}

	/**
	 * Get primaryPhone
	 * 
	 * @return primaryPhone
	 **/
	@javax.annotation.Nullable
	@ApiModelProperty(value = "")

	public Phone getPrimaryPhone()
	{
		return primaryPhone;
	}

	public void setPrimaryPhone(Phone primaryPhone)
	{
		this.primaryPhone = primaryPhone;
	}

	public ReturnAddress stateOrProvince(String stateOrProvince)
	{

		this.stateOrProvince = stateOrProvince;
		return this;
	}

	/**
	 * The state or province of the return address.
	 * 
	 * @return stateOrProvince
	 **/
	@javax.annotation.Nullable
	@ApiModelProperty(value = "The state or province of the return address.")

	public String getStateOrProvince()
	{
		return stateOrProvince;
	}

	public void setStateOrProvince(String stateOrProvince)
	{
		this.stateOrProvince = stateOrProvince;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		ReturnAddress returnAddress = (ReturnAddress)o;
		return Objects.equals(this.addressLine1, returnAddress.addressLine1) &&
				Objects.equals(this.addressLine2, returnAddress.addressLine2) &&
				Objects.equals(this.city, returnAddress.city) &&
				Objects.equals(this.country, returnAddress.country) &&
				Objects.equals(this.county, returnAddress.county) &&
				Objects.equals(this.fullName, returnAddress.fullName) &&
				Objects.equals(this.postalCode, returnAddress.postalCode) &&
				Objects.equals(this.primaryPhone, returnAddress.primaryPhone) &&
				Objects.equals(this.stateOrProvince, returnAddress.stateOrProvince);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(addressLine1, addressLine2, city, country, county, fullName, postalCode, primaryPhone, stateOrProvince);
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("class ReturnAddress {\n");
		sb.append("    addressLine1: ").append(toIndentedString(addressLine1)).append("\n");
		sb.append("    addressLine2: ").append(toIndentedString(addressLine2)).append("\n");
		sb.append("    city: ").append(toIndentedString(city)).append("\n");
		sb.append("    country: ").append(toIndentedString(country)).append("\n");
		sb.append("    county: ").append(toIndentedString(county)).append("\n");
		sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
		sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
		sb.append("    primaryPhone: ").append(toIndentedString(primaryPhone)).append("\n");
		sb.append("    stateOrProvince: ").append(toIndentedString(stateOrProvince)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o)
	{
		if (o == null)
		{
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}
