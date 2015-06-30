package com.camunda.demo.outerspace.wjax.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.camunda.demo.outerspace.wjax.entity.Article;

@Stateless
public class DemoService {
  
  @PersistenceContext
  private EntityManager entityManager;
  
  @Inject
  private StockService stockService;
  
  public void createDemoData() {
    if (entityManager.find(Article.class, "bag1")==null) {
      Article article1 = new Article();
      article1.setId("bag1");
      article1.setDescription("Awesome crazy bag");
      article1.setImageUrl("http://www.klonblog.com/images/2013/06/cleaver-clutch.jpg");
      article1.setPriceInCents(999);
      entityManager.persist(article1);
      stockService.setAmountOnStock(article1.getId(), 5);
      
      Article article2 = new Article();
      article2.setId("bag2");
      article2.setDescription("Expensive bag which makes you important");
      article2.setImageUrl("http://www.chiemgau24.de/bilder/2011/05/11/1239548/587856154-merkel-handtasche.9.jpg");
      article2.setPriceInCents(4999);
      entityManager.persist(article2);      
      stockService.setAmountOnStock(article2.getId(), 5);

      Article article3 = new Article();
      article3.setId("bag3");
      article3.setDescription("Need some fish?");
      article3.setImageUrl("http://imworld.aufeminin.com/dossiers/D20081030/bagfinish2-135548_L.jpg");
      article3.setPriceInCents(2300);
      entityManager.persist(article3);      
      stockService.setAmountOnStock(article3.getId(), 5);
}
  }
  

}
