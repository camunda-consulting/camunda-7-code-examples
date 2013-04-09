package com.camunda.fox.demo.orderconfirmation.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 * This is an abstract base class for building view-centric lists. 
 * 
 * It supports paging and sorting, the parameters for which are held as conversational state.
 *
 * @author meyerd
 */
// This is not an EJB, as no transactions are needed
@ConversationScoped
public abstract class AbstractEntityList<T> implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @PersistenceContext
  private EntityManager entityManager;
  
  @Inject 
  private Conversation conversation;  
  
  // paging 
  protected int pageNumber = 0;
  protected int pageSize = 5;
  // sorting 
  protected String sortColumn;
  protected boolean sortAsc = true;
  
  protected List<T> list;
  
  public List<T> getList() {
    if(list==null) {
      loadList();
    }
    return list;
  }
  
  public void clearList() {
    list = null;
  }
       
  protected abstract Class<T> getEntityType();

  protected void loadList() {         
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> query = createQuery(cb);

    // retrieval of the list + paging
    list = entityManager.createQuery(query)
        .setMaxResults(pageSize)
        .setFirstResult(getFirstResult())
        .getResultList();
      
    // this begins a unit of work:
    if(conversation.isTransient()) {
      conversation.begin();
    }
  }
  
  // this can be subclassed to add custom restrictions to the query / replace the query
  protected CriteriaQuery<T> createQuery(CriteriaBuilder cb) {
    CriteriaQuery<T> query = cb.createQuery(getEntityType());
    // building the base query: "select e from EntityType e"
    Root<T> e = query.from(getEntityType());
    query.select(e);

    // adding sort restrictions
    if(sortColumn != null) {
      Path<Object> sortField = e.get(sortColumn);
      if (sortAsc) {
        query.orderBy(cb.asc(sortField));
      } else {
        query.orderBy(cb.desc(sortField));
      }
    }
    return query;
  }
  
  // paging ////////////////////////////////////////
    
  protected int getFirstResult() {
    return pageNumber*pageSize;
  }
    
  public void nextPage() {
    pageNumber += 1;
    clearList();
  }
  
  public void prevPage() {
    if(pageNumber == 0) {
      return;
    }
    pageNumber -= 1;
    clearList();
  }
  
  public boolean isHasMore(){
    return getMaxResults() > (pageNumber * pageSize) + pageSize;
  }
  
  public int getMaxResults() {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> cq = cb.createQuery(Long.class);
    cq.select(cb.count(cq.from(getEntityType())));
    return entityManager.createQuery(cq).getSingleResult().intValue();
  }
  
  public boolean isHasLess(){
    return pageNumber>0;
  }
  
  // Sorting ///////////////////////////////////////
   
  public void setSortColumn(String sortColumn) {
    this.sortColumn = sortColumn;
    pageNumber = 0;
    sortAsc = !sortAsc;
    clearList();
  }
    
  public String getSortColumn() {
    return sortColumn;
  }
  
  public String getSortOrder() {
    return (sortAsc ? "ASC" : "DESC");
  }
  
  
}
