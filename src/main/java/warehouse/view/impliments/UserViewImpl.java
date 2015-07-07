package warehouse.view.impliments;

import warehouse.servise.interfaces.UserServise;
import warehouse.view.interfaces.UserView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Олег on 06.07.2015.
 */
public class UserViewImpl implements UserView {
    @Autowired
    UserServise userServise;

    public UserViewImpl(UserServise userServise) {
        this.userServise = userServise;
    }

    public UserViewImpl() {
    }

    public UserServise getUserServise() {
        return userServise;
    }

    public void setUserServise(UserServise userServise) {
        this.userServise = userServise;
    }
}
