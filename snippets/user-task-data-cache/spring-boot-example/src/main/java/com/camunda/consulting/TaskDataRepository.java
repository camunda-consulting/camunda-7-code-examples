package com.camunda.consulting;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDataRepository extends ElasticsearchRepository<TaskData,String> {}
