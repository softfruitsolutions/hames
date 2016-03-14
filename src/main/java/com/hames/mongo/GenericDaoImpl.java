package com.hames.mongo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.ClassUtils;

import com.hames.bean.helper.UUIDHelper;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;
import com.hames.util.peer.ClassUtil;

@Repository
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDaoImpl.class);

	@Autowired
	protected HamesDataStore hamesDataStore;
	
	protected Class<T> entityClass;
	
	/**
	 * Get Collection Name.
	 * @return String
	 */
	public abstract String getCollectionName();
	
	public GenericDaoImpl() {
		this.entityClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDaoImpl.class);
	}
	
	@PostConstruct
	public void createCollection() {	
		if(!hamesDataStore.collectionExists(getCollectionName())){
			hamesDataStore.createCollection(getCollectionName());
		}
	}
	
	
	@Override
	public void save(T t) {
		setId(t);
		LOGGER.debug("Saving a document of entity: {} with data: {}", t.getClass(),t.toString());
		hamesDataStore.save(t,getCollectionName());		
	}

	@Override
	public T findById(String id) {
		return hamesDataStore.findById(id, entityClass, getCollectionName());
	}


	@Override
	public List<T> findAll() {
		return hamesDataStore.findAll(entityClass, getCollectionName());
	}
	
	@Override
	public T findByQuery(Query query) {
		return (T) hamesDataStore.findOne(query, entityClass,getCollectionName());
	}
	
	@Override
	public List<T> findAllByQuery(Query query) {
		return (List<T>) hamesDataStore.find(query, entityClass,getCollectionName());
	}
	
	@Override
	public Boolean isExists(String id) {
		return hamesDataStore.exists(id, getCollectionName());
	}

	@Override
	public DatatableResponse getPagedDatatable(DatatableRequest request) {
		LOGGER.debug("Building datatable for entity : {} and collection : {}",entityClass.getClass(),getCollectionName());
		request.setClazz(entityClass);
		request.setMongoCollectionName(getCollectionName());
		return hamesDataStore.getDatatablePagedResult(request);
	}
	
	/**
	 * A private method to set id based on annotation {@link Id}
	 * @param T t
	 */
	private void setId(T t){
		for (Field field : ClassUtil.getAllFields(new ArrayList<Field>(), t.getClass())) {
			//Checking @Id annotation is present
			if(field.isAnnotationPresent(Id.class)){
				field.setAccessible(Boolean.TRUE);
				try {
					String fieldValue = (String) field.get(t);
					if(fieldValue == null || fieldValue.isEmpty()){
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
