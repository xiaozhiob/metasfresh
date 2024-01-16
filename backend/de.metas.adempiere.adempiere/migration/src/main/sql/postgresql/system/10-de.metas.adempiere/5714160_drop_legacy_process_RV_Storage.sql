/*
 * #%L
 * de.metas.adempiere.adempiere.migration-sql
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

-- Run mode: SWING_CLIENT

-- Name: Lagerbestand - Detail
-- Action Type: R
-- Report: RV_Storage
-- 2023-12-20T19:23:08.255Z
DELETE FROM  AD_Menu_Trl WHERE AD_Menu_ID=411
;

-- 2023-12-20T19:23:08.262Z
DELETE FROM AD_Menu WHERE AD_Menu_ID=411
;

-- 2023-12-20T19:23:08.266Z
DELETE FROM AD_TreeNodeMM n WHERE Node_ID=411 AND EXISTS (SELECT * FROM AD_Tree t WHERE t.AD_Tree_ID=n.AD_Tree_ID AND t.AD_Table_ID=116)
;

-- Value: RV_Storage
-- 2023-12-20T19:23:13.110Z
DELETE FROM  AD_Process_Trl WHERE AD_Process_ID=236
;

-- 2023-12-20T19:23:13.116Z
DELETE FROM AD_Process WHERE AD_Process_ID=236
;

