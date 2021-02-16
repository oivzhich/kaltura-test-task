package model;

import io.restassured.response.Response;
import lombok.Data;
import utils.HttpRequestSender;

import java.util.Map;

@Data
public class UserActionable {
    private String apiVersion;
    private Integer partnerId;
    private User user;
    private String password;

    public UserActionable() {
        this.apiVersion = "6.0.0";
        this.partnerId = 3197;
        this.user = new User();
        this.password = "password_SlLVWDLl";
    }

    public Response register() {
        String call = "service/ottuser/action/register";
        return HttpRequestSender.post(call, this);
    }

    public Response login() {
        String call = "service/ottuser/action/login";
        return HttpRequestSender.post(call, Map.of(
                "apiVersion", this.apiVersion,
                "partnerId", this.partnerId,
                "username", this.user.getUsername(),
                "password", this.getPassword()
        ));
    }
}
