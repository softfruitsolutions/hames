package hames.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AbstractDaoImpl implements AbstractDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public Transaction getTransaction(){
		return getSession().getTransaction();
	}
	
	public <T> void saveOrUpdate(T t){
		getSession().beginTransaction();
		try{
			getSession().saveOrUpdate(t);
			getTransaction().commit();
		}catch(Exception ex){
			getTransaction().rollback();
		}finally{
			getSession().close();
		}
		
	}
	
}
