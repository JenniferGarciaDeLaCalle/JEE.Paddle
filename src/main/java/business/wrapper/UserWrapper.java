package business.wrapper;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

import data.entities.User;

public class UserWrapper {

    private String username;

    private String email;

    private String password;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar birthDate;

    public UserWrapper() {
    }

    public UserWrapper(String username, String email, String password, Calendar birthDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }
    
    public UserWrapper(User user) {
        this(user.getUsername(), user.getEmail(), user.getPassword(), user.getBirthDate());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        //String time = new SimpleDateFormat("dd-MMM-yyyy ").format(birthDate.getTime());
        return "UserWrapper [username=" + username + ", email=" + email + ", password=" + password + ", birthDate=" + birthDate + "]";
    }

}
