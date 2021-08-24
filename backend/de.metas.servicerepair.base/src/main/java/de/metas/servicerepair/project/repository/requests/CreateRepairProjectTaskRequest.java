/*
 * #%L
 * de.metas.servicerepair.base
 * %%
 * Copyright (C) 2021 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

package de.metas.servicerepair.project.repository.requests;

import de.metas.handlingunits.HuId;
import de.metas.inout.InOutAndLineId;
import de.metas.organization.OrgId;
import de.metas.product.ProductId;
import de.metas.project.ProjectId;
import de.metas.quantity.Quantity;
import de.metas.servicerepair.customerreturns.WarrantyCase;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.adempiere.mm.attributes.AttributeSetInstanceId;

@Value
@Builder
public class CreateRepairProjectTaskRequest
{
	@NonNull ProjectId projectId;
	@NonNull OrgId orgId;
	@NonNull InOutAndLineId customerReturnLineId;

	@NonNull ProductId productId;
	@NonNull @Builder.Default AttributeSetInstanceId asiId = AttributeSetInstanceId.NONE;
	@NonNull WarrantyCase warrantyCase;
	@NonNull Quantity qtyRequired;

	@NonNull HuId repairVhuId;
}
