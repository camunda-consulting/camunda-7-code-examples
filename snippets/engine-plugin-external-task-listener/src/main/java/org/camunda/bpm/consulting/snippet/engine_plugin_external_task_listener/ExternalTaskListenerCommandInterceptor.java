package org.camunda.bpm.consulting.snippet.engine_plugin_external_task_listener;

import static org.camunda.bpm.consulting.snippet.engine_plugin_external_task_listener.CompleteExternalTaskWithoutLockCmd.completeExternalTaskWithoutLock;

import java.util.concurrent.CompletableFuture;

import org.camunda.bpm.engine.externaltask.ExternalTask;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.db.entitymanager.cache.CachedDbEntity;
import org.camunda.bpm.engine.impl.db.entitymanager.cache.DbEntityCache;
import org.camunda.bpm.engine.impl.db.entitymanager.cache.DbEntityState;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandInterceptor;
import org.camunda.bpm.engine.impl.persistence.entity.ExternalTaskEntity;

public class ExternalTaskListenerCommandInterceptor extends CommandInterceptor {

  private ExternalTaskListener externalTaskListener;

  public ExternalTaskListenerCommandInterceptor(ExternalTaskListener externalTaskListener) {
    this.externalTaskListener = externalTaskListener;
  }

  @Override
  public <T> T execute(Command<T> command) {
    // execute command first
    final T result = next.execute(command);

    // after that find all new External Tasks
    DbEntityCache cache = Context.getCommandContext().getDbEntityManager().getDbEntityCache();
    for (ExternalTaskEntity externalTaskEntity : cache.getEntitiesByType(ExternalTaskEntity.class)) {
      CachedDbEntity cachedEntity = cache.getCachedEntity(externalTaskEntity);
      if (DbEntityState.TRANSIENT.equals(cachedEntity.getEntityState())) {
        externalTaskListener.notify(externalTaskEntity);
      }
    }

    return result;
  }

}
