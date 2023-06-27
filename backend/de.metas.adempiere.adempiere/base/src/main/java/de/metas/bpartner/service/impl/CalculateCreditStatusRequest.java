/*
 * #%L
 * de.metas.adempiere.adempiere.base
 * %%
 * Copyright (C) 2023 metas GmbH
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

package de.metas.bpartner.service.impl;

import de.metas.bpartner.service.BPartnerStats;
import de.metas.common.util.CoalesceUtil;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static java.math.BigDecimal.ZERO;

@Value
public class CalculateCreditStatusRequest
{
	BPartnerStats stat;
	BigDecimal additionalAmt;
	Timestamp date;
	boolean forceCheckCreditStatus;

	@Builder
	private CalculateCreditStatusRequest(
			@NonNull BPartnerStats stat,
			@NonNull Timestamp date,
			BigDecimal additionalAmt,
			Boolean forceCheckCreditStatus)
	{
		this.stat = stat;
		this.date = date;
		this.additionalAmt = CoalesceUtil.coalesce(additionalAmt, ZERO);
		this.forceCheckCreditStatus = CoalesceUtil.coalesce(forceCheckCreditStatus, false);
	}
}

