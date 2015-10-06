package hames.core.dao;

import java.util.List;

public interface AbstractDao {

	/**
	 * Save or Update an entity
	 * @param t
	 */
	public <T> void saveOrUpdate(T t);
	
	/**
	 * Find one from an entity data
	 * @return
	 */
	public <T> T findOne(Class<?> clazz,Long id);
	/**
	 * Find all of an entity
	 * @param clazz
	 * @return
	 */
	public <T> List<T> findAll(Class<?> clazz);
}
