package downloadCSV;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.journaldev.spring.model.Person;

@Repository
public class fileDAO {
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	
	public void addFiles() {
	
	}
}
