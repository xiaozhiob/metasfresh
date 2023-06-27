/*
 * #%L
 * de-metas-common-rest_api
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

package de.metas.common.rest_api.v2.project.workorder;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;

//dev-note: to be kept in sync with AD_Reference_ID=299
public enum JsonDurationUnit
{
	Year("Y"),
	Month("M"),
	Day("D"),
	Hour("h"),
	Minute("m"),
	Second("s");

	@Getter
	private final String code;

	JsonDurationUnit(@NonNull final String code)
	{
		this.code = code;
	}

	@NonNull
	public static JsonDurationUnit ofCode(@NonNull final String code)
	{
		final JsonDurationUnit targetFieldType = typesByCode.get(code);

		if (targetFieldType == null)
		{
			throw new IllegalArgumentException("JsonDurationUnit does not support code: " + code);
		}
		return targetFieldType;
	}

	private static final ImmutableMap<String, JsonDurationUnit> typesByCode = Maps.uniqueIndex(Arrays.asList(values()), JsonDurationUnit::getCode);
}
