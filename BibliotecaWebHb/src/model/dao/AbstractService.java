package model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public abstract class AbstractService<T extends AbstractDAO<E>, E> {

	private T dao;

	public AbstractService() {
		dao = createDAO();
	}

	public void persist(E entity) {
		dao.openCurrentSessionwithTransaction();
		dao.persist(entity);
		dao.closeCurrentSessionwithTransaction();
	}

	public void update(E entity) {
		dao.openCurrentSessionwithTransaction();
		dao.update(entity);
		dao.closeCurrentSessionwithTransaction();
	}

	public E findById(Serializable id) {
		dao.openCurrentSession();
		E aula = dao.findById(id);
		dao.closeCurrentSession();
		return aula;
	}

	public void delete(Serializable id) {
		dao.openCurrentSessionwithTransaction();
		E aula = dao.findById(id);
		dao.delete(aula);
		dao.closeCurrentSessionwithTransaction();
	}

	public void delete(E entity) {
		dao.openCurrentSessionwithTransaction();
		dao.delete(entity);
		dao.closeCurrentSessionwithTransaction();
	}

	public void refresh(E entity) {
		dao.openCurrentSessionwithTransaction();
		dao.getCurrentSession().refresh(entity);
		dao.closeCurrentSessionwithTransaction();

	}

	public List<E> findAll() {
		dao.openCurrentSession();
		List<E> all = dao.findAll();
		dao.closeCurrentSession();
		return all;
	}

	public void deleteAll() {
		dao.openCurrentSessionwithTransaction();
		dao.deleteAll();
		dao.closeCurrentSessionwithTransaction();
	}

	public <TYPE extends Object> List<TYPE> executeHQLGeneric(Class<TYPE> typeToReturn, String hql) {
		dao.openCurrentSessionwithTransaction();
		List<TYPE> result = dao.getCurrentSession().createQuery(hql, typeToReturn).getResultList();
		dao.closeCurrentSessionwithTransaction();
		return result;
	}

	public List<E> executeHQLQuery(String hql) {
		dao.openCurrentSessionwithTransaction();
		List<E> result = dao.getCurrentSession().createQuery(hql, dao.getPersistentClass()).getResultList();
		dao.closeCurrentSessionwithTransaction();
		return result;
	}

	public List<E> findWithCriteria(Criterion... criterions) {
		dao.openCurrentSessionwithTransaction();
		Criteria c = dao.getCurrentSession().createCriteria(dao.getPersistentClass());
		for(Criterion criterion : criterions) {
			c.add(criterion);
		}
		
		List<E> result = c.list();
		dao.closeCurrentSessionwithTransaction();
		return result;
	}
	
	public List<E> findWithCriteriaAndOrder(Order order, Criterion... criterions) {
		dao.openCurrentSessionwithTransaction();
		Criteria c = dao.getCurrentSession().createCriteria(dao.getPersistentClass());
		for(Criterion criterion : criterions) {
			c.add(criterion);
		}
		c.addOrder(order); //Order.asc("id");
		List<E> result = c.list();
		dao.closeCurrentSessionwithTransaction();
		return result;
	}


	
	
	public T getDao() {
		return dao;
	}

	public abstract T createDAO();

}
