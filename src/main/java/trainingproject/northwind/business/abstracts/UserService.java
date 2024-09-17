package trainingproject.northwind.business.abstracts;

import trainingproject.northwind.core.entities.User;
import trainingproject.northwind.core.utilities.results.DataResult;
import trainingproject.northwind.core.utilities.results.Result;


public interface UserService {
    Result add (User user);
    DataResult<User> findByEmail(String email);
}
