package de.metas.inbound.mail;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.dsl.mail.Mail;
import org.springframework.integration.mail.support.DefaultMailHeaderMapper;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import de.metas.logging.LogManager;
import lombok.NonNull;

/*
 * #%L
 * de.metas.inbound.mail
 * %%
 * Copyright (C) 2018 metas GmbH
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

@Component
public class InboundEMailServer implements InitializingBean, InboundEMailConfigChangedListener
{
	private static final String IMAP_USER_FLAG = "metasfresh-inbound-mail";

	private static final Logger logger = LogManager.getLogger(InboundEMailServer.class);

	private final InboundEMailService emailService;
	private final InboundEMailConfigRepository configsRepo;
	private final IntegrationFlowContext flowContext;

	private final Set<InboundEMailConfigId> loadedConfigIds = ConcurrentHashMap.newKeySet();

	public InboundEMailServer(
			@NonNull final InboundEMailService emailService,
			@NonNull final InboundEMailConfigRepository configsRepo,
			@NonNull final IntegrationFlowContext flowContext)
	{
		this.emailService = emailService;
		this.configsRepo = configsRepo;
		this.flowContext = flowContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		loadAll();
		logger.info("Started with {} inbound configs", loadedConfigIds.size());
	}

	private static String toFlowId(final InboundEMailConfigId configId)
	{
		return configId.getAsString();
	}

	public void loadAll()
	{
		reload(configsRepo.getAll());
	}

	private void reload(final Collection<InboundEMailConfig> configs)
	{
		final HashSet<InboundEMailConfigId> configIdsToUnload = new HashSet<>(loadedConfigIds);

		for (final InboundEMailConfig config : configs)
		{
			reload(config);
			configIdsToUnload.remove(config.getId());
		}

		for (final InboundEMailConfigId configId : configIdsToUnload)
		{
			unloadById(configId);
		}
	}

	public void reload(final InboundEMailConfigId id)
	{
		final InboundEMailConfig config = configsRepo.getById(id);
		reload(config);
	}

	public void reload(final InboundEMailConfig config)
	{
		unloadById(config.getId());
		load(config);
	}

	public void unloadById(final InboundEMailConfigId configId)
	{
		final boolean wasLoaded = loadedConfigIds.remove(configId);
		if (!wasLoaded)
		{
			logger.info("Skip unloading inbound mail for {} because it's not loaded", configId);
			return;
		}

		try
		{
			flowContext.remove(toFlowId(configId));

			logger.info("Unloaded inbound mail for {}", configId);
		}
		catch (final Exception ex)
		{
			logger.warn("Unloading inbound mail for {} failed. Ignored.", configId, ex);
		}
	}

	private void load(final InboundEMailConfig config)
	{
		final IntegrationFlow flow = createIntegrationFlow(config);

		flowContext.registration(flow)
				.id(toFlowId(config.getId()))
				.register();

		loadedConfigIds.add(config.getId());

		logger.info("Loaded inbound mail for {}", config);
	}

	private IntegrationFlow createIntegrationFlow(final InboundEMailConfig config)
	{
		return IntegrationFlows
				.from(Mail.imapIdleAdapter(config.getUrl())
						.javaMailProperties(p -> p.put("mail.debug", Boolean.toString(config.isDebugProtocol())))
						.userFlag(IMAP_USER_FLAG)
						.shouldMarkMessagesAsRead(false)
						.shouldDeleteMessages(false)
						.shouldReconnectAutomatically(true)
						.headerMapper(new DefaultMailHeaderMapper()))
				.handle(InboundEMailMessageHandler.builder()
						.config(config)
						.emailService(emailService)
						.build())
				.get();
	}

	@Override
	public void onInboundEMailConfigChanged(final Set<InboundEMailConfigId> changedConfigIds)
	{
		final List<InboundEMailConfig> newOrChangedConfigs = configsRepo.getByIds(changedConfigIds);
		final Set<InboundEMailConfigId> newOrChangedConfigIds = newOrChangedConfigs.stream()
				.map(InboundEMailConfig::getId)
				.collect(ImmutableSet.toImmutableSet());
		final Set<InboundEMailConfigId> deletedConfigIds = Sets.difference(changedConfigIds, newOrChangedConfigIds);

		deletedConfigIds.forEach(this::unloadById);
		reload(newOrChangedConfigs);
	}
}
