package pl.edu.wszib.magazyn.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.magazyn.dao.iUserDAO;
import pl.edu.wszib.magazyn.model.User;
import pl.edu.wszib.magazyn.model.view.RegistrationModel;
import pl.edu.wszib.magazyn.services.iUserService;
import pl.edu.wszib.magazyn.session.SessionObject;

import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements iUserService {

    private final Pattern lengthPattern = Pattern.compile("[A-Za-z0-9._-]{5}.*");

    @Autowired
    iUserDAO userDAO;

    @Autowired
    SessionObject sessionObject;

    @Override
    public Pattern getLengthPattern() {
        return lengthPattern;
    }

    @Override
    public void authenticate(User user) {
        User userFromDB = userDAO.getUserByLogin(user.getLogin());
        if(userFromDB!=null && userFromDB.getPass().equals(user.getPass())) {
            this.sessionObject.setLoggedUser(userFromDB);
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }

    @Override
    public boolean register(RegistrationModel registrationModel) {
        if(this.userDAO.getUserByLogin(registrationModel.getLogin()) != null){
            return false;
        }
        User newUser = new User(registrationModel.getLogin(),registrationModel.getPass(), User.Role.USER);
        return this.userDAO.persistUser(newUser);
    }
}
