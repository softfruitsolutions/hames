package hames.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AbstractDaoImpl implements AbstractDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public Transaction getTransaction(){
		return getSession().getTransaction();
	}
	
	@Transactional
	public <T> void insert(T t){
		getSession().beginTransaction();
		try{
			getSession().save(t);
			getTransaction().commit();
		}catch(Exception ex){
			getTransaction().rollback();
			System.err.println(ex);
		}finally{
			getSession().close();
		}
		
	}
	
}
