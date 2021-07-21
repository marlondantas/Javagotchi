package br.com.luaazul.javagotchi.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;


public abstract class AbstractDAO<PK, T> {

	public EntityManager entityManager;
	
	protected AbstractDAO() {	
		this.entityManager = GeradorManagerFactory.getEntityManager();
		this.entityManager.getTransaction().begin();
	}
	
	protected AbstractDAO(EntityManager entityManager) {	
		this.entityManager =entityManager;
		if(!this.entityManager.getTransaction().isActive()) {
			this.entityManager.getTransaction().begin();
		}
	}
	
	protected AbstractDAO(Class<T> persistedClass) {
		this();
	}
	
	@SuppressWarnings("unchecked")
	public T getById(PK pk) {
        return (T) this.entityManager.find(getTypeClass(), pk);
    }
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
        return this.entityManager.createQuery(("FROM " + getTypeClass().getName()))
                .getResultList();
    }
 
	
    public void save(T entity) throws Exception {
    	try {
    		if(!this.entityManager.getTransaction().isActive()) {
    			this.entityManager.getTransaction().begin();
			}
			this.entityManager.persist(entity);
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw new Exception();
		}
    }
 
    public void update(T entity) {
    	try {
    		if(!this.entityManager.getTransaction().isActive()) {
    			this.entityManager.getTransaction().begin();
			}
			this.entityManager.merge(entity);
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
    }
 
    public void delete(T entity) {
    	try {
    		if(!this.entityManager.getTransaction().isActive()) {
    			this.entityManager.getTransaction().begin();
			}
			this.entityManager.remove(entity);
			this.entityManager.getTransaction().commit();
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
    }
 
    
    private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }
    
	/**
	 * CAMADA SERVICE
	 */
	public void beginTransaction(){
		this.entityManager.clear();
		this.entityManager.getTransaction().begin();
    }
     
    public void commit(){
    	this.entityManager.getTransaction().commit();
    }
    
    /**
     * THIS METHOD NEEDS TO BE ALWAYS CALLED
     */
    public void close(){
    	this.entityManager.close();
    }
     
    public void rollBack(){
    	this.entityManager.getTransaction().rollback();
    }
     
    public void clearCache() {
    	this.entityManager.clear();
    	this.entityManager.getEntityManagerFactory().getCache().evictAll();
    }
    
    public EntityManager getEntityManager(){
        return entityManager;
    }
    
    public void setEntityManager(EntityManager entityManager) {
    	this.entityManager = entityManager;
    }
    
}
