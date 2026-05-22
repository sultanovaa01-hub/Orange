package kg.rest.controller;

import kg.rest.HttpRequest;
import kg.rest.endPoints.Endpoint;
import kg.rest.models.Post;

public class PostController extends HttpRequest {
    public PostController(String url) {
        super(url);
    }

    public Post [] getAllPosts (){
        return super.get(getEndpoint(Endpoint.PUBLIC,Endpoint.V2,Endpoint.POSTS)).as(Post[].class);
    }

    public Post createNewPost (int user_id,Post post){
        super.post(getEndpoint(Endpoint.PUBLIC,Endpoint.V2,Endpoint.USERS, String.valueOf(user_id),Endpoint.POSTS), post.toJSON());
        if (response.getStatusCode() == 201) {
            return response.as(Post.class);
        }
        return null;
    }
}
