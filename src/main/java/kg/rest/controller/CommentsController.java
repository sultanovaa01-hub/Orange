package kg.rest.controller;

import kg.rest.HttpRequest;
import kg.rest.endPoints.Endpoint;
import kg.rest.models.Comment;

public class CommentsController extends HttpRequest {
    public CommentsController(String url) {
        super(url);
    }

    public Comment [] getAllComments (){
        return super.get(getEndpoint(Endpoint.PUBLIC,Endpoint.V2,Endpoint.COMMENTS)).as(Comment[].class);
    }

    public Comment createComment (int post_id,Comment comment){
        super.post(getEndpoint(Endpoint.PUBLIC,Endpoint.V2,Endpoint.POSTS, String.valueOf(post_id),Endpoint.COMMENTS),
                comment.toJSON());
        if (response.getStatusCode() == 201){
            return response.as(Comment.class);
        } return null;

    }
}
