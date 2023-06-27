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
import de.metas.camel.externalsystems.ebay.api.model.PaymentDisputeActivity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This type is used by the base response of the getActivities method, and includes a log of all activities of a payment dispute, from creation to resolution.
 */
@ApiModel(description = "This type is used by the base response of the getActivities method, and includes a log of all activities of a payment dispute, from creation to resolution.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-07-20T14:45:55.981728+02:00[Europe/Berlin]")
public class PaymentDisputeActivityHistory
{
	public static final String SERIALIZED_NAME_ACTIVITY = "activity";
	@SerializedName(SERIALIZED_NAME_ACTIVITY)
	private List<PaymentDisputeActivity> activity = null;

	public PaymentDisputeActivityHistory activity(List<PaymentDisputeActivity> activity)
	{

		this.activity = activity;
		return this;
	}

	public PaymentDisputeActivityHistory addActivityItem(PaymentDisputeActivity activityItem)
	{
		if (this.activity == null)
		{
			this.activity = new ArrayList<>();
		}
		this.activity.add(activityItem);
		return this;
	}

	/**
	 * This array holds all activities of a payment dispute, from creation to resolution. For each activity, the activity type, the actor, and a timestamp is shown. The getActivities response is dynamic, and grows with each recorded activity.
	 * 
	 * @return activity
	 **/
	@javax.annotation.Nullable
	@ApiModelProperty(value = "This array holds all activities of a payment dispute, from creation to resolution. For each activity, the activity type, the actor, and a timestamp is shown. The getActivities response is dynamic, and grows with each recorded activity.")

	public List<PaymentDisputeActivity> getActivity()
	{
		return activity;
	}

	public void setActivity(List<PaymentDisputeActivity> activity)
	{
		this.activity = activity;
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
		PaymentDisputeActivityHistory paymentDisputeActivityHistory = (PaymentDisputeActivityHistory)o;
		return Objects.equals(this.activity, paymentDisputeActivityHistory.activity);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(activity);
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("class PaymentDisputeActivityHistory {\n");
		sb.append("    activity: ").append(toIndentedString(activity)).append("\n");
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
