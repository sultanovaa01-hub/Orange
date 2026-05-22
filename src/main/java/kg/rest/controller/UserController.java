package kg.rest.controller;

import kg.rest.HttpRequest;
import kg.rest.endPoints.Endpoint;
import kg.rest.models.User;

public class UserController extends HttpRequest {
    public UserController(String url) {
        super(url);
    }

    public User[] getAllUsers() {
        return super.get(getEndpoint(Endpoint.PUBLIC, Endpoint.V2, Endpoint.USERS)).as(User[].class);
    }

    public User createNewUser(User user) {
        super.post(getEndpoint(Endpoint.PUBLIC, Endpoint.V2, Endpoint.USERS), user.toJSON());
        if (response.getStatusCode() == 201) {
            return response.as(User.class);
        }
        return null;
    }

    public User updateUserDetails(int id, User user) {
        super.put(getEndpoint(Endpoint.PUBLIC, Endpoint.V2, Endpoint.USERS, String.valueOf(id)), user.toJSON());
        if (response.getStatusCode() == 200) {
            return response.as(User.class);
        }
        return null;
    }

    public User partialUpdateUser (int id, User user){
        super.patch(getEndpoint(Endpoint.PUBLIC,Endpoint.V2,Endpoint.USERS, String.valueOf(id)), user.toJSON());
        if (response.getStatusCode() == 200) {
            return response.as(User.class);
        }
        return null;
    }
}