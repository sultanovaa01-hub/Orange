package api;

import com.github.javafaker.Faker;
import kg.rest.controller.CommentsController;
import kg.rest.controller.PostController;
import kg.rest.controller.UserController;
import kg.rest.models.Comment;
import kg.rest.models.Post;
import kg.rest.models.User;
import kg.rest.utils.CsvUtils;
import kg.xiaomi.utils.file.ConfugurationManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
@Tag("user")

public class UsersTest {
    private static UserController userController;
    private static PostController postController;
    private static CommentsController commentsController;
    private Faker faker;

    @BeforeEach
    void initControllers() {
        userController = new UserController(ConfugurationManager.getBaseConfig().gorestBaseUrl());
        postController = new PostController(ConfugurationManager.getBaseConfig().gorestBaseUrl());
        commentsController = new CommentsController(ConfugurationManager.getBaseConfig().gorestBaseUrl());
        faker = new Faker();
    }

    private User buildRandomUser() {
        return User.builder().name(faker.name().firstName())
                .email(faker.internet().emailAddress())
                .gender(faker.options().option("female", "male"))
                .status(faker.options().option("active", "inactive"))
                .build();
    }
    @Tag("user")
    @Test
    public void shouldCreateUserSuccessfully() {

        User user = buildRandomUser();
        User created = userController.createNewUser(user);

        Assertions.assertThat(userController.getResponse().getStatusCode())
                .as("Expected 201 created")
                .isEqualTo(201);

        Assertions.assertThat(created)
                .as("Created user fields should match")
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(user);

        Assertions.assertThat(created.getId())
                .as("ID is null")
                .isNotNull();

    }
        @Tag("user")
        @Test
        void shouldReturnValidationErrorAndCodeWhenNameIsBlank () {

            User userWithBlankNameField = User.builder()
                    .name("")
                    .email(faker.internet().emailAddress())
                    .gender(faker.options().option("female", "male"))
                    .status(faker.options().option("active", "inactive"))
                    .build();

            userController.createNewUser(userWithBlankNameField);

            Assertions.assertThat(userController.getResponse().getStatusCode())
                    .as("Status code should be 422")
                    .isEqualTo(422);

            List <ApiError> errors = Arrays.asList(userController.getResponse().getBody().as(ApiError[].class));

            Assertions.assertThat(errors)
                    .as("Expected a validation message: name can't be blank")
                    .anyMatch(e->e.getField().equals("name") && e.getMessage().equals("can't be blank"));
    }
    @Tag("user")
    @Test
    void shouldReturnValidationErrorAndCodeWhenEmailIsInvalid (){
        User userWithInvalidEmailField = User.builder()
                .name(faker.name().firstName())
                .email("not-an-email")
                .gender(faker.options().option("female", "male"))
                .status(faker.options().option("active", "inactive"))
                .build();

        userController.createNewUser(userWithInvalidEmailField);

        Assertions.assertThat(userController.getResponse().getStatusCode())
                .as("Status code should be 422")
                .isEqualTo(422);

        List <ApiError> errors = Arrays.asList(userController.getResponse().getBody().as(ApiError[].class));

        Assertions.assertThat(errors)
                .as("Expected a validation message: is invalid")
                .anyMatch(e->e.getField().equals("email") && e.getMessage().equals("is invalid"));
    }
    @Tag("user")
    @Test
    void shouldReturnValidationErrorAndCodeWhenGenderIsInvalid (){
        User userWithInvalidGenderField = User.builder()
                .name(faker.name().firstName())
                .email(faker.internet().emailAddress())
                .gender("blabla")
                .status(faker.options().option("active", "inactive"))
                .build();

        userController.createNewUser(userWithInvalidGenderField);

        Assertions.assertThat(userController.getResponse().getStatusCode())
                .as("Status code should be 422")
                .isEqualTo(422);

        List <ApiError> errors = Arrays.asList(userController.getResponse().getBody().as(ApiError[].class));

        Assertions.assertThat(errors)
                .as("Expected a validation message: can't be blank, can be male of female")
                .anyMatch(e->e.getField().equals("gender") && e.getMessage().equals("can't be blank, can be male of female"));
    }
    @Tag("user")
    @Test
    void shouldReturnErrorWhenEmailIsDuplicate() {
        String repeatedEmail = "aliya2@gmail.com";

        userController.createNewUser(User.builder()
                        .name(faker.name().firstName())
                        .email(repeatedEmail)
                        .gender(faker.options().option("female","male"))
                        .status(faker.options().option("active","inactive"))
                .build());

        userController.createNewUser(User.builder()
                .name(faker.name().firstName())
                .email(repeatedEmail)
                .gender(faker.options().option("female","male"))
                .status(faker.options().option("active","inactive"))
                .build());

        List<ApiError> errors = Arrays.asList(userController.getResponse().as(ApiError[].class));
        Assertions.assertThat(errors)
                .as("Expected error message: has already been taken")
                .anyMatch(e->e.getField().equals("email") && e.getMessage().equals("has already been taken"));
    }

    @Tag("user")
    @Test
    void shouldResponseWithinTimeLimits() {
        userController.createNewUser(buildRandomUser());

        Assertions.assertThat(userController.getResponse().getTime())
                .as("Response time exceeded 2 seconds")
                .isLessThan(2000);
    }
    @Tag("user")
    @Test
    void shouldHaveCorrectSecurityHeaders() {
        userController.getAllUsers();

        Assertions.assertThat(userController.getResponse().getHeader("x-frame-options"))
                .as("x-frame-options header should be SAMEORIGIN")
                .isEqualTo("SAMEORIGIN");
    }
    @Tag("user")
    @Test
    void shouldUpdateUserFully() {

        User created = userController.createNewUser(buildRandomUser());
        int generatedId = created.getId();

        userController.updateUserDetails(generatedId, created);

        Assertions.assertThat(userController.getResponse().getStatusCode())
                .as("Expected 200 OK on PUT")
                .isEqualTo(200);
    }
    @Tag("user")
    @Test
    void shouldPartiallyUpdateUser() {

        User created = userController.createNewUser(buildRandomUser());
        int generatedId = created.getId();

        User patch = User.builder()
                .name(created.getName() + "_edited")
                .email(created.getEmail())
                .gender(created.getGender())
                .status(created.getStatus())
                .build();
        User patched = userController.partialUpdateUser(generatedId, patch);

        Assertions.assertThat(patched.getName())
                .as("Name should contain patch suffix")
                .endsWith("_edited");
    }
    @Tag("user")
    @Test
    void shouldReturnValidationErrorWhenPostTitleIsBlank () {

        User created = userController.createNewUser(buildRandomUser());

        postController.createNewPost(created.getId(), Post.builder()
                .title("")
                .body(faker.book().genre())
                .build());

        Assertions.assertThat(postController.getResponse().getStatusCode())
                .as("Status code should be 422")
                .isEqualTo(422);

        List <ApiError> errors = Arrays.asList(postController.getResponse().as(ApiError[].class));

        Assertions.assertThat(errors)
                .as("Expected a validation message: can't be blank")
                .anyMatch(e->e.getField().equals("title") && e.getMessage().equals("can't be blank"));
    }

    @Tag("user")
    @Test
    void shouldReturnValidationErrorWhenPostBodyIsBlank () {

        User created = userController.createNewUser(buildRandomUser());

        postController.createNewPost(created.getId(), Post.builder()
                .title(faker.book().title())
                .body("")
                .build());

        List <ApiError> errors = Arrays.asList(postController.getResponse().as(ApiError[].class));

        Assertions.assertThat(errors)
                .as("Expected a validation message: can't be blank")
                .anyMatch(e->e.getField().equals("body") && e.getMessage().equals("can't be blank"));
    }

    @Tag("user")
    @Test
    void shouldReturnErrorWhenCreatingPostForNonExistentUser() {
    int nonExistingUserId = 999999;

    postController.createNewPost(nonExistingUserId,Post.builder()
            .title(faker.book().title())
            .body(faker.book().genre())
            .build());
    List<ApiError> errors = Arrays.asList(postController.getResponse().as(ApiError[].class));
    Assertions.assertThat(errors)
            .as("Expected message: must exist")
            .anyMatch(e->e.getField().equals("user") && e.getMessage().equals("must exist"));

    Assertions.assertThat(postController.getResponse().getStatusCode())
            .as("Expected status code: 422")
            .isEqualTo(422);
    }

    @Tag("user")
    @Test
    void shouldCreatePostForUser() {

        User created = userController.createNewUser(buildRandomUser());
        int generatedId = created.getId();

        Post post = Post.builder()
                .title(faker.book().title())
                .body(faker.book().genre())
                .build();
        postController.createNewPost(generatedId, post);

    }

    @Tag("user")
    @Test
    void shouldCreateCommentOnPost(){

        User created = userController.createNewUser(buildRandomUser());
        Post post = postController.createNewPost(created.getId(), Post.builder()
                .title(faker.book().title())
                .body(faker.book().genre())
                .build());

        Comment comment = Comment.builder().name(created.getName()).email(created.getEmail())
                .body(faker.options().option("good!", "bad!")).build();
        commentsController.createComment(post.getId(),comment);

       Assertions.assertThat(commentsController.getResponse().getStatusCode())
               .as("Expected status code is 201")
               .isEqualTo(201);
    }

    @Tag("user")
    @Test
    void shouldReturnErrorWhenCommentNameIsBlank() {
        User created = userController.createNewUser(buildRandomUser());
        Post post = postController.createNewPost(created.getId(), Post.builder()
                .title(faker.book().title())
                .body(faker.book().genre())
                .build());

        Comment comment = Comment.builder()
                .name("")
                .email(created.getEmail())
                .body(faker.options().option("good!", "bad!"))
                .build();
        commentsController.createComment(post.getId(),comment);

        List <ApiError> errors = Arrays.asList(commentsController.getResponse().as(ApiError[].class));

        Assertions.assertThat(errors)
                .as("Expected message: can't be blank")
                .anyMatch(e->e.getField().equals("name") && e.getMessage().equals("can't be blank"));
    }

    @Tag("user")
    @Test
    void shouldReturnErrorWhenCommentEmailIsBlank() {
        User created = userController.createNewUser(buildRandomUser());
        Post post = postController.createNewPost(created.getId(), Post.builder()
                .title(faker.book().title())
                .body(faker.book().genre())
                .build());

        Comment comment = Comment.builder()
                .name(created.getName())
                .email("")
                .body(faker.options().option("good!", "bad!"))
                .build();
        commentsController.createComment(post.getId(),comment);

        List <ApiError> errors = Arrays.asList(commentsController.getResponse().as(ApiError[].class));

        Assertions.assertThat(errors)
                .as("Expected message: can't be blank, is invalid")
                .anyMatch(e->e.getField().equals("email") && e.getMessage().equals("can't be blank, is invalid"));
    }

    @Tag("user")
    @Test
    void shouldReturnErrorWhenCommentEmailIsInvalid() {
        User created = userController.createNewUser(buildRandomUser());
        Post post = postController.createNewPost(created.getId(), Post.builder()
                .title(faker.book().title())
                .body(faker.book().genre())
                .build());

        Comment comment = Comment.builder()
                .name(created.getName())
                .email("not-an-email")
                .body(faker.options().option("good!", "bad!"))
                .build();
        commentsController.createComment(post.getId(),comment);

        List <ApiError> errors = Arrays.asList(commentsController.getResponse().as(ApiError[].class));

        Assertions.assertThat(errors)
                .as("Expected message: is invalid")
                .anyMatch(e->e.getField().equals("email") && e.getMessage().equals("is invalid"));
    }

    @Tag("user")
    @Test
    void shouldReturnErrorWhenCommentBodyIsBlank() {
        User created = userController.createNewUser(buildRandomUser());
        Post post = postController.createNewPost(created.getId(), Post.builder()
                .title(faker.book().title())
                .body(faker.book().genre())
                .build());

        Comment comment = Comment.builder()
                .name(created.getName())
                .email(created.getEmail())
                .body("")
                .build();
        commentsController.createComment(post.getId(),comment);

        List <ApiError> errors = Arrays.asList(commentsController.getResponse().as(ApiError[].class));

        Assertions.assertThat(errors)
                .as("Expected message: can't be blank")
                .anyMatch(e->e.getField().equals("body") && e.getMessage().equals("can't be blank"));
    }

    @Tag("user")
    @Test
    void shouldReturnErrorWhenCreatingCommentForNonExistingPost() {
        User created = userController.createNewUser(buildRandomUser());
        int nonExistingPostId = 9999999;

        Comment comment = Comment.builder()
                .name(created.getName())
                .email(created.getEmail())
                .body("")
                .build();
        commentsController.createComment(nonExistingPostId,comment);

        List <ApiError> errors = Arrays.asList(commentsController.getResponse().as(ApiError[].class));

        Assertions.assertThat(errors)
                .as("Expected message: must exist")
                .anyMatch(e->e.getField().equals("post") && e.getMessage().equals("must exist"));
    }

    @Tag("user")
    @Test
    void shouldSaveUserInfoToCsvFile(){

        User created = userController.createNewUser(buildRandomUser());
        User created2 = userController.createNewUser(buildRandomUser());

        List<User> users = List.of(created, created2);
        CsvUtils.writeUsersToCsv(users);

        File CsvFile = new File("src/test/resources/users.csv");

        Assertions.assertThat(CsvFile)
                .as("Csv file should exist")
                .exists();
        Assertions.assertThat(CsvFile)
                .as("Csv file should exist")
                .isNotEmpty();
    }
}
