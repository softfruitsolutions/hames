package hames.core.dao;

public interface AbstractDao {

	public <T> void saveOrUpdate(T t);
}
