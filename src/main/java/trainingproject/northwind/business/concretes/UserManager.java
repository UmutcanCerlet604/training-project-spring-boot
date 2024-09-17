package trainingproject.northwind.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingproject.northwind.business.abstracts.UserService;
import trainingproject.northwind.core.dataAccess.UserDao;
import trainingproject.northwind.core.entities.User;
import trainingproject.northwind.core.utilities.results.DataResult;
import trainingproject.northwind.core.utilities.results.Result;
import trainingproject.northwind.core.utilities.results.SuccessDataResult;
import trainingproject.northwind.core.utilities.results.SuccessResult;

@Service
public class UserManager implements UserService {

    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }
    // UserDao'yu parametre alan bir injection pattern'ı uyguladık.
    // Burada gevşek bir bağımlılık oluşturduk.
    // Autowired projede UserDao'yu implemente eden nesnenin referansını bize verecek.
    // UserDao bir interface'dir. Tek başına bir işe yaramaz.
    // Spring arka tarafta bu repository'i implemente eder. Bize aslında UserDao için Spring'in bir nesnesini verir.
    // Spring arka tarafta IoC için harika bir yapıya sahipir.
    @Override
    public Result add(User user) {
        this.userDao.save(user);
        return new SuccessResult("Kullanıcı eklendi");
    }

    @Override
    public DataResult<User> findByEmail(String email) {
        return new SuccessDataResult<User>
                (this.userDao.findByEmail(email), "Kullanıcı Bulundu");
    }
}
