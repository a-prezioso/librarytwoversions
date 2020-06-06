package model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;


public abstract class AbstractDAO<T> {


	private Session currentSession;
    
    private Transaction currentTransaction;

    public Session openCurrentSession() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        return currentSession;
    }
 
    public Session openCurrentSessionwithTransaction() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
    public void closeCurrentSession() {
        currentSession.close();
    }
    
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
	
    public Session getCurrentSession() {
        return currentSession;
    }
 
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
 
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
 
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
    
    public void persist(T entity) {
        getCurrentSession().save(entity);
    }
 
    public void update(T entity) {
        getCurrentSession().update(entity);
    }
 
    public T findById(Serializable id) {
        T object = (T) getCurrentSession().get(getPersistentClass(), id);
        return object; 
    }
 
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }
 
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        List<T> objects = (List<T>) getCurrentSession().createCriteria(getPersistentClass()).list();
        return objects;
    }
 
    public void deleteAll() {
        List<T> entityList = findAll();
        for (T entity : entityList) {
            delete(entity);
        }
    }
	
	public abstract Class<T> getPersistentClass();
	
	
	
}
