package org.compiere.model;

import de.metas.ad_reference.ReferenceId;
import de.metas.adempiere.service.IColumnBL;
import de.metas.logging.LogManager;
import de.metas.util.Check;
import lombok.Getter;
import lombok.NonNull;
import org.adempiere.ad.column.AdColumnId;
import org.adempiere.ad.table.api.TableName;
import org.adempiere.ad.validationRule.AdValRuleId;
import org.compiere.util.DisplayType;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * PO Info Column Info Value Object
 *
 * @author Jorg Janke
 * @version $Id: POInfoColumn.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 */
public final class POInfoColumn implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1667303121090497293L;

	private static final Logger logger = LogManager.getLogger(POInfoColumn.class);

	public POInfoColumn(
			@NonNull final AdColumnId AD_Column_ID,
			final String tableName,
			final String columnName,
			final String columnSQL,
			final int displayTypeParam,
			final boolean isMandatory,
			final boolean isUpdateable,
			final String defaultLogic,
			final String columnLabel,
			final String columnDescription,
			final boolean isKey,
			final boolean isParent,
			@Nullable final ReferenceId ad_Reference_Value_ID,
			@Nullable final TableName AD_Reference_Value_TableName,
			final int ad_Reference_Value_KeyColumn_DisplayType,
			final AdValRuleId AD_Val_Rule_ID,
			final int fieldLength,
			final String valueMin,
			final String valueMax,
			final boolean isTranslated,
			final boolean isEncrypted,
			final boolean isAllowLogging,
			final boolean isRestAPICustomColumn)
	{
		this.AD_Column_ID = AD_Column_ID;
		ColumnName = columnName;
		this.tableName = tableName;
		this.virtualColumn = !Check.isEmpty(columnSQL, false); // trimWhitespaces=false to preserve back compatibility
		if (virtualColumn)
		{
			this.ColumnSQL = columnSQL;
			this.sqlColumnForSelect = ColumnSQL + " AS " + ColumnName;
		}
		else
		{
			this.ColumnSQL = null;
			this.sqlColumnForSelect = ColumnName;
		}

		if (isString(tableName, columnName, displayTypeParam, ad_Reference_Value_ID, ad_Reference_Value_KeyColumn_DisplayType))
		{
			this.displayType = org.compiere.util.DisplayType.String;
			this.ColumnClass = String.class;
		}
		else if (columnName.equals("Posted") || columnName.equals("Processed") || columnName.equals("Processing"))
		{
			this.displayType = displayTypeParam;
			this.ColumnClass = Boolean.class;
		}
		else if (IColumnBL.isRecordIdColumnName(columnName))
		{
			this.displayType = org.compiere.util.DisplayType.ID;
			this.ColumnClass = Integer.class;
		}
		else
		{
			this.displayType = displayTypeParam;
			this.ColumnClass = org.compiere.util.DisplayType.getClass(displayTypeParam, true);
		}

		IsMandatory = isMandatory;
		IsUpdateable = isUpdateable;
		DefaultLogic = defaultLogic;
		//ColumnLabel = columnLabel;
		//ColumnDescription = columnDescription;
		IsKey = isKey;
		IsParent = isParent;
		//
		this.AD_Reference_Value_ID = ad_Reference_Value_ID;
		// ValidationCode = validationCode;
		this.AD_Val_Rule_ID = AD_Val_Rule_ID;
		//
		FieldLength = fieldLength;
		ValueMin = valueMin;
		ValueMin_BD = toBigDecimalOrNull(ValueMin, "ValueMin");
		ValueMax = valueMax;
		ValueMax_BD = toBigDecimalOrNull(ValueMax, "ValueMax");
		IsTranslated = isTranslated;
		IsEncrypted = isEncrypted;
		IsAllowLogging = isAllowLogging;
		IsRestAPICustomColumn = isRestAPICustomColumn;

		this._referencedTableName = computeReferencedTableName(this.displayType, AD_Reference_Value_TableName);
	}   // Column

	private static boolean isString(
			final String tableName,
			final String columnName,
			final int displayType,
			@Nullable final ReferenceId ad_Reference_Value_ID,
			final int ad_Reference_Value_KeyColumn_DisplayType)
	{
		if (org.compiere.util.DisplayType.String == displayType)
		{
			return true;
		}

		// FIXME: HARDCODED
		if (columnName.equals("AD_Language")
				|| columnName.equals("EntityType"))
		{
			return true;
		}

		// task #500: Also allow type String for non-numeric types with a reference value (Table and search)
		if (ad_Reference_Value_ID != null
				&& (isTableDisplayType(displayType) || isSearchDisplayType(displayType)))
		{
			final boolean isNumericKey = org.compiere.util.DisplayType.isID(ad_Reference_Value_KeyColumn_DisplayType);
			return !isNumericKey;
		}

		return false;
	}

	private static boolean isSearchDisplayType(final int displayType)
	{
		return org.compiere.util.DisplayType.Search == displayType;
	}

	private static boolean isTableDisplayType(final int displayType)
	{
		return org.compiere.util.DisplayType.Table == displayType;
	}

	/**
	 * Column ID
	 */
	final AdColumnId AD_Column_ID;
	/**
	 * Column Name
	 */
	private final String ColumnName;

	private final String tableName;

	/**
	 * Virtual Column SQL
	 */
	private final String ColumnSQL;
	/**
	 * Is Virtual Column
	 */
	private final boolean virtualColumn;
	/**
	 * Is Lazy Loading
	 */
	boolean IsLazyLoading;
	/**
	 * Display Type
	 */
	@Getter private final int displayType;
	/**
	 * Data Type
	 */
	final Class<?> ColumnClass;
	/**
	 * Mandatory
	 */
	final boolean IsMandatory;
	/**
	 * Default Value
	 */
	final String DefaultLogic;
	/**
	 * Updateable
	 */
	final boolean IsUpdateable;
	/**
	 * PK
	 */
	final boolean IsKey;
	/**
	 * FK to Parent
	 */
	private final boolean IsParent;
	/**
	 * Translated
	 */
	private final boolean IsTranslated;
	/**
	 * Encrypted
	 */
	final boolean IsEncrypted;
	/**
	 * Allow Logging
	 */
	final boolean IsAllowLogging;

	/**
	 * Reference Value
	 */
	final ReferenceId AD_Reference_Value_ID;
	/**
	 * Validation
	 */
	// public String ValidationCode;
	final AdValRuleId AD_Val_Rule_ID;

	/**
	 * Field Length
	 */
	final int FieldLength;
	/**
	 * Min Value
	 */
	final String ValueMin;
	/**
	 * Max Value
	 */
	final String ValueMax;
	/**
	 * Min Value
	 */
	final BigDecimal ValueMin_BD;
	/**
	 * Max Value
	 */
	final BigDecimal ValueMax_BD;

	final boolean IsRestAPICustomColumn;

	/* package */ boolean IsCalculated = false;
	// metas: us215
	/* package */ boolean IsUseDocumentSequence = false;
	// metas: 05133
	/* package */ boolean IsStaleable = true;
	// metas: 01537
	/* package */ boolean IsSelectionColumn;

	private final String sqlColumnForSelect;

	private final Optional<String> _referencedTableName;

	/**
	 * String representation
	 *
	 * @return info
	 */
	@Override
	public String toString()
	{
		return "POInfo.Column["
				+ ColumnName
				+ ",ID=" + AD_Column_ID
				+ ",DisplayType=" + this.displayType
				+ ",ColumnClass=" + ColumnClass
				+ "]";
	}    // toString

	@Nullable
	private static BigDecimal toBigDecimalOrNull(final String valueStr, final String name)
	{
		if (Check.isEmpty(valueStr, true))
		{
			return null;
		}

		try
		{
			return new BigDecimal(valueStr.trim());
		}
		catch (final Exception ex) // i.e. NumberFormatException
		{
			logger.error("Cannot parse " + name + "=" + valueStr, ex);
		}

		return null;
	}

	public String getColumnName()
	{
		return this.ColumnName;
	}

	public AdColumnId getAD_Column_ID()
	{
		return this.AD_Column_ID;
	}

	boolean isTranslated()
	{
		return this.IsTranslated;
	}

	public String getColumnSQL()
	{
		return this.ColumnSQL;
	}

	public boolean isVirtualColumn()
	{
		return this.virtualColumn;
	}

	public String getColumnSqlForSelect()
	{
		return sqlColumnForSelect;
	}

	public ReferenceId getAD_Reference_Value_ID()
	{
		return AD_Reference_Value_ID;
	}

	public boolean isSelectionColumn()
	{
		return IsSelectionColumn;
	}

	public boolean isMandatory()
	{
		return IsMandatory;
	}

	public boolean isKey()
	{
		return IsKey;
	}

	public boolean isParent()
	{
		return IsParent;
	}

	public boolean isStaleable()
	{
		return IsStaleable;
	}

	public boolean isLookup()
	{
		return org.compiere.util.DisplayType.isLookup(displayType);
	}

	public boolean isRestAPICustomColumn()
	{
		return IsRestAPICustomColumn;
	}

	@Nullable
	public String getReferencedTableNameOrNull()
	{
		return _referencedTableName.orElse(null);
	}

	private static Optional<String> computeReferencedTableName(
			final int displayType,
			@Nullable final TableName adReferenceValueTableName)
	{
		// Special lookups (Location, Locator etc)
		final String refTableName = DisplayType.getTableName(displayType);
		if (refTableName != null)
		{
			return Optional.of(refTableName);
		}

		if (DisplayType.isLookup(displayType) && adReferenceValueTableName != null)
		{
			return Optional.of(adReferenceValueTableName.getAsString());
		}

		return Optional.empty();
	}

	public boolean isPasswordColumn()
	{
		return DisplayType.isPassword(ColumnName, displayType);
	}
}    // POInfoColumn
