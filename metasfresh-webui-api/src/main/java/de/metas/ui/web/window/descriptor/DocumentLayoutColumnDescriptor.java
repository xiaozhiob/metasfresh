package de.metas.ui.web.window.descriptor;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.util.Check;
import org.adempiere.util.GuavaCollectors;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

/*
 * #%L
 * metasfresh-webui-api
 * %%
 * Copyright (C) 2016 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

public class DocumentLayoutColumnDescriptor
{
	public static final Builder builder()
	{
		return new Builder();
	}

	private final List<DocumentLayoutElementGroupDescriptor> elementGroups;

	private DocumentLayoutColumnDescriptor(final Builder builder)
	{
		super();
		elementGroups = ImmutableList.copyOf(builder.buildElementGroups());
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.add("elementGroups", elementGroups)
				.toString();
	}

	public List<DocumentLayoutElementGroupDescriptor> getElementGroups()
	{
		return elementGroups;
	}

	public boolean hasElementGroups()
	{
		return !elementGroups.isEmpty();
	}

	public static final class Builder
	{
		private final List<DocumentLayoutElementGroupDescriptor.Builder> elementGroupsBuilders = new ArrayList<>();

		private Builder()
		{
			super();
		}

		public DocumentLayoutColumnDescriptor build()
		{
			return new DocumentLayoutColumnDescriptor(this);
		}

		private List<DocumentLayoutElementGroupDescriptor> buildElementGroups()
		{
			return elementGroupsBuilders
					.stream()
					.map(elementGroupBuilder -> elementGroupBuilder.build())
					.filter(elementGroup -> elementGroup.hasElementLines())
					.collect(GuavaCollectors.toImmutableList());
		}

		public Builder addElementGroup(final DocumentLayoutElementGroupDescriptor.Builder elementGroupBuilder)
		{
			Check.assumeNotNull(elementGroupBuilder, "Parameter elementGroupBuilder is not null");
			elementGroupsBuilders.add(elementGroupBuilder);
			return this;
		}
		
		public DocumentLayoutElementDescriptor.Builder findElementBuilderByFieldName(final String fieldName)
		{
			for (final DocumentLayoutElementGroupDescriptor.Builder elementGroupBuilder : elementGroupsBuilders)
			{
				final DocumentLayoutElementDescriptor.Builder elementBuilder = elementGroupBuilder.findElementBuilderByFieldName(fieldName);
				if (elementBuilder == null)
				{
					continue;
				}

				return elementBuilder;

			}
			return null;
		}

	}
}
