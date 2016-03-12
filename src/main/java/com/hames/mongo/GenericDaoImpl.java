package com.hames.mongo;

import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hames.bean.helper.UUIDHelper;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;

@Repository
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDaoImpl.class);

	@Autowired
	protected HamesDataStore hamesDataStore;
	
	protected T entityClass;
	
	/**
	 * Get Collection Name.
	 * @return String
	 */
	public abstract String getCollectionName();
	
	@PostConstruct
	public void createCollection() {	
		if(!hamesDataStore.collectionExists(getCollectionName())){
			hamesDataStore.createCollection(getCollectionName());
		}
	}
	
	
	@Override
	public void save(T t) {
		//setId(t);
		LOGGER.debug("Saving a document of entity: {} with data: {}", t.getClass(),t.toString());
		hamesDataStore.save(t,getCollectionName());		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(String id) {
		return hamesDataStore.findById(id, (Class<T>) entityClass, getCollectionName());
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return hamesDataStore.findAll((Class<T>) entityClass, getCollectionName());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T findByQuery(Query query) {
		return (T) hamesDataStore.findOne(query, entityClass.getClass(),getCollectionName());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAllByQuery(Query query) {
		return (List<T>) hamesDataStore.find(query, entityClass.getClass(),getCollectionName());
	}
	
	@Override
	public Boolean isExists(String id) {
		return hamesDataStore.exists(id, getCollectionName());
	}

	@Override
	public DatatableResponse getPagedDatatable(DatatableRequest request) {
		LOGGER.debug("Building datatable for entity : {} and collection : {}",entityClass.getClass(),getCollectionName());
		request.setClazz(entityClass.getClass());
		request.setMongoCollectionName(getCollectionName());
		return hamesDataStore.getDatatablePagedResult(request);
	}
	
	/**
	 * A private method to set id based on annotation {@link Id}
	 * @param T t
	 */
	private void setId(T t){
		for (Field field : t.getClass().getDeclaredFields()) {
			//Checking @Id annotation is present
			if(field.isAnnotationPresent(Id.class)){
				field.setAccessible(Boolean.TRUE);
				try {
					Object fieldValue = field.get(t);
					if(fieldValue == null){
						field.set(t, UUIDHelper.getUUID());
					}
				} catch (IllegalArgumentException e) {
					LOGGER.error("No such field : {} found.",field);
				} catch (IllegalAccessException e) {
					LOGGER.error("Field : {} is not accesible. Either change the modifier to access the field");
				}
			}
		}
	}
	
}
