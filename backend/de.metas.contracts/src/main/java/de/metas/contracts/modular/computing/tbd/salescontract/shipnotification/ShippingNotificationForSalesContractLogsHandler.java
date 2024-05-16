/*
 * #%L
 * de.metas.contracts
 * %%
 * Copyright (C) 2024 metas GmbH
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

package de.metas.contracts.modular.computing.tbd.salescontract.shipnotification;

import de.metas.contracts.modular.ModularContractService;
import de.metas.contracts.modular.computing.IComputingMethodHandler;
import de.metas.contracts.modular.invgroup.interceptor.ModCntrInvoicingGroupRepository;
import de.metas.contracts.modular.log.ModularContractLogDAO;
import de.metas.contracts.modular.workpackage.impl.AbstractShippingNotificationLogHandler;
import de.metas.lang.SOTrx;
import de.metas.shippingnotification.ShippingNotificationService;
import lombok.NonNull;
import org.springframework.stereotype.Component;

/**
 * @deprecated If needed, please move/use code in the new computing methods in package de.metas.contracts.modular.computing.salescontract
 */
@Deprecated
@Component
public class ShippingNotificationForSalesContractLogsHandler extends AbstractShippingNotificationLogHandler
{
	@NonNull
	private final ShippingNotificationForSalesModularContractHandler computingMethod;

	public ShippingNotificationForSalesContractLogsHandler(
			@NonNull final ShippingNotificationForSalesModularContractHandler computingMethod,
			@NonNull final ShippingNotificationService notificationService,
			@NonNull final ModCntrInvoicingGroupRepository modCntrInvoicingGroupRepository,
			@NonNull final ModularContractService modularContractService,
			@NonNull final ModularContractLogDAO contractLogDAO)
	{
		super(notificationService, modCntrInvoicingGroupRepository, contractLogDAO, modularContractService);
		this.computingMethod = computingMethod;
	}

	@Override
	public @NonNull IComputingMethodHandler getComputingMethod()
	{
		return computingMethod;
	}

	@Override
	public SOTrx getSOTrx()
	{
		return SOTrx.SALES;
	}
}
