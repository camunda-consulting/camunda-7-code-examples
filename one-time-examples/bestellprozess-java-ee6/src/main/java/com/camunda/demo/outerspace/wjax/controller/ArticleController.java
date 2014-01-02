package com.camunda.demo.outerspace.wjax.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.camunda.demo.outerspace.wjax.entity.Article;
import com.camunda.demo.outerspace.wjax.service.DemoService;

@ApplicationScoped
public class ArticleController {

  @Inject
  private DemoService demoService;

  @PersistenceContext
  private EntityManager entityManager;

  @PostConstruct
  public void createDemoArticle() {
    demoService.createDemoData();
  }

  @SuppressWarnings("unchecked")
  @Produces
  @Named
  public List<Article> articles() {
    return (List<Article>) entityManager.createQuery("select a from Article a").getResultList();
  }
}
