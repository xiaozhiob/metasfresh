/*
 * #%L
 * de.metas.business
 * %%
 * Copyright (C) 2022 metas GmbH
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

package de.metas.project.workorder.calendar;

import de.metas.bpartner.BPartnerId;
import de.metas.project.ProjectId;
import de.metas.user.UserId;
import de.metas.util.InSetPredicate;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;

@Value
@Builder
public class WOProjectCalendarQuery
{
	public static final WOProjectCalendarQuery ANY = builder().build();

	@NonNull @Builder.Default InSetPredicate<ProjectId> projectIds = InSetPredicate.any();
	@Nullable BPartnerId onlyCustomerId;
	@Nullable UserId onlyResponsibleId;

	public boolean isAny() {return projectIds.isAny() && onlyCustomerId == null && onlyResponsibleId == null;}

	public boolean isNone() {return projectIds.isNone();}
}
