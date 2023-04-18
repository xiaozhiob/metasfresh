-- 2022-11-10T13:26:49.587Z
/* DDL */ CREATE TABLE public.PP_Weighting_Spec (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP WITH TIME ZONE NOT NULL, CreatedBy NUMERIC(10) NOT NULL, Description TEXT, IsActive CHAR(1) CHECK (IsActive IN ('Y','N')) NOT NULL, Name VARCHAR(40) NOT NULL, PP_Weighting_Spec_ID NUMERIC(10) NOT NULL, Tolerance_Perc NUMERIC NOT NULL, Updated TIMESTAMP WITH TIME ZONE NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, WeightChecksRequired NUMERIC(10) NOT NULL, CONSTRAINT PP_Weighting_Spec_Key PRIMARY KEY (PP_Weighting_Spec_ID))
;

-- 2022-11-10T13:27:18.869Z
/* DDL */ CREATE TABLE public.PP_Order_Weighting_Run (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP WITH TIME ZONE NOT NULL, CreatedBy NUMERIC(10) NOT NULL, C_UOM_ID NUMERIC(10), DateDoc TIMESTAMP WITH TIME ZONE NOT NULL, Description TEXT, IsActive CHAR(1) CHECK (IsActive IN ('Y','N')) NOT NULL, IsToleranceExceeded CHAR(1) DEFAULT 'N' CHECK (IsToleranceExceeded IN ('Y','N')) NOT NULL, MaxWeight NUMERIC NOT NULL, MinWeight NUMERIC NOT NULL, M_Product_ID NUMERIC(10) NOT NULL, PP_Order_BOMLine_ID NUMERIC(10), PP_Order_ID NUMERIC(10) NOT NULL, PP_Order_Weighting_Run_ID NUMERIC(10) NOT NULL, PP_Weighting_Spec_ID NUMERIC(10) NOT NULL, Processed CHAR(1) DEFAULT 'N' CHECK (Processed IN ('Y','N')) NOT NULL, TargetWeight NUMERIC NOT NULL, Tolerance_Perc NUMERIC NOT NULL, Updated TIMESTAMP WITH TIME ZONE NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, WeightChecksRequired NUMERIC(10) NOT NULL, CONSTRAINT CUOM_PPOrderWeightingRun FOREIGN KEY (C_UOM_ID) REFERENCES public.C_UOM DEFERRABLE INITIALLY DEFERRED, CONSTRAINT MProduct_PPOrderWeightingRun FOREIGN KEY (M_Product_ID) REFERENCES public.M_Product DEFERRABLE INITIALLY DEFERRED, CONSTRAINT PPOrderBOMLine_PPOrderWeightingRun FOREIGN KEY (PP_Order_BOMLine_ID) REFERENCES public.PP_Order_BOMLine DEFERRABLE INITIALLY DEFERRED, CONSTRAINT PPOrder_PPOrderWeightingRun FOREIGN KEY (PP_Order_ID) REFERENCES public.PP_Order DEFERRABLE INITIALLY DEFERRED, CONSTRAINT PP_Order_Weighting_Run_Key PRIMARY KEY (PP_Order_Weighting_Run_ID), CONSTRAINT PPWeightingSpec_PPOrderWeightingRun FOREIGN KEY (PP_Weighting_Spec_ID) REFERENCES public.PP_Weighting_Spec DEFERRABLE INITIALLY DEFERRED)
;

-- 2022-11-10T13:27:27.640Z
/* DDL */ CREATE TABLE public.PP_Order_Weighting_RunCheck (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP WITH TIME ZONE NOT NULL, CreatedBy NUMERIC(10) NOT NULL, C_UOM_ID NUMERIC(10) NOT NULL, Description TEXT, IsActive CHAR(1) CHECK (IsActive IN ('Y','N')) NOT NULL, Line NUMERIC(10) NOT NULL, PP_Order_Weighting_RunCheck_ID NUMERIC(10) NOT NULL, PP_Order_Weighting_Run_ID NUMERIC(10) NOT NULL, Updated TIMESTAMP WITH TIME ZONE NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, Weight NUMERIC NOT NULL, CONSTRAINT CUOM_PPOrderWeightingRunCheck FOREIGN KEY (C_UOM_ID) REFERENCES public.C_UOM DEFERRABLE INITIALLY DEFERRED, CONSTRAINT PP_Order_Weighting_RunCheck_Key PRIMARY KEY (PP_Order_Weighting_RunCheck_ID), CONSTRAINT PPOrderWeightingRun_PPOrderWeightingRunCheck FOREIGN KEY (PP_Order_Weighting_Run_ID) REFERENCES public.PP_Order_Weighting_Run DEFERRABLE INITIALLY DEFERRED)
;

