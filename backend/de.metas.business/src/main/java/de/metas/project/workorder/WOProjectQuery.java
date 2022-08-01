package de.metas.project.workorder;

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
public class WOProjectQuery
{
	public static final WOProjectQuery ANY = builder().build();

	@NonNull @Builder.Default InSetPredicate<ProjectId> projectIds = InSetPredicate.any();
	@Nullable BPartnerId onlyCustomerId;
	@Nullable UserId onlyResponsibleId;

	public boolean isAny() {return projectIds.isAny() && onlyCustomerId == null && onlyResponsibleId == null;}

	public boolean isNone() {return projectIds.isNone();}
}
