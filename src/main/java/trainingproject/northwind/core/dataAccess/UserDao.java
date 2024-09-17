package trainingproject.northwind.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import trainingproject.northwind.core.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}

/*
    JPARepository diğer repository türlerine göre daha kullanışlıdır.
    Kullanıcı ekleme işlemi JPARepository sayesinde hazırdır.
    Burada ilk adımda kullanıcıya email üzerinden ulaşma işlemini yazacağız.
*/