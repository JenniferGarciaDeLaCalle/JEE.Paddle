package api;

import java.util.Calendar;

import business.api.Uris;
import business.wrapper.TokenWrapper;
import business.wrapper.TrainingWrapper;
import business.wrapper.UserWrapper;
import business.wrapper.UserWrapperBuilder;

public class RestService {

	public static final String URL = "http://localhost:8080/JEE.Paddle.0.0.1-SNAPSHOT" + Uris.SERVLET_MAP;

    public void deleteAll() {
        new RestBuilder<TokenWrapper>(RestService.URL).path(Uris.ADMINS).basicAuth(this.loginAdmin(), "").delete().build();
    }

    public String loginAdmin() {
        TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth("admin", "admin").clazz(TokenWrapper.class)
                .post().build();
        return token.getToken();
    }
    
    public String loginTrainer() {
        TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth("trainer", "trainer").clazz(TokenWrapper.class)
                .post().build();
        return token.getToken();
    }
    
    public String loginUser(String username, String password) {
    	TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth(username, password)
                .clazz(TokenWrapper.class).post().build();
        return token.getToken();
    }
    
    public String registerAndLoginPlayer() {
        UserWrapper player = new UserWrapperBuilder().build();
        new RestBuilder<Object>(URL).path(Uris.USERS).body(player).post().build();
        TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth(player.getUsername(), player.getPassword())
                .clazz(TokenWrapper.class).post().build();
        return token.getToken();
    }

    public void createCourt(String id) {
        new RestBuilder<Object>(URL).path(Uris.COURTS).param("id", id).basicAuth(this.loginAdmin(), "").post().build();
    }
    
    public void createTraining(Calendar startDate, Calendar finishDate, int courtId, int trainerId) {
        TrainingWrapper trainingWrapper = new TrainingWrapper(startDate, finishDate, courtId, trainerId);
        new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).path(Uris.TRAINERS).basicAuth(this.loginTrainer(), "").body(trainingWrapper)
        		.post().build();
    }

}
