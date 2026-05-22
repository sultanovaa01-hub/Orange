package api;

import kg.rest.controller.CommentsController;
import kg.rest.controller.PostController;
import kg.rest.controller.UserController;
import kg.xiaomi.utils.file.ConfugurationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EndToEndTest {

    public class UsersTest {
        private static UserController userController;
        private static PostController postController;
        private static CommentsController commentsController;

        @BeforeEach
        void initControllers() {
            userController = new UserController(ConfugurationManager.getBaseConfig().gorestBaseUrl());
            postController = new PostController(ConfugurationManager.getBaseConfig().gorestBaseUrl());
            commentsController = new CommentsController(ConfugurationManager.getBaseConfig().gorestBaseUrl());
        }

        @Test
        public void shouldPerformAllGorestActions (){

        }
    }
}