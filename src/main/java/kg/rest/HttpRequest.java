package kg.rest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import kg.xiaomi.utils.file.ConfugurationManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
@Data
public class HttpRequest {

    protected String url;
    protected RequestSpecification requestSpecification;
    protected Response response;

    private static final String SLASH = "/";

    public HttpRequest(String url) {
        this.url = url;
        this.requestSpecification = given().baseUri(url).header("Authorization","Bearer " +
                ConfugurationManager.getBaseConfig().bearerToken()).contentType(ContentType.JSON).accept(ContentType.JSON);
    }

    public Response get(String endpoint){
        log.info("Perfomed GET {}",endpoint);
        this.response = given().spec(requestSpecification).get(endpoint);
        logResponse();
        return this.response;
    }

    public Response post (String endPoint, String body){
        log.info("Performed POST {}",endPoint);
        log.info("Body is {}",body);
        this.response = given().spec(requestSpecification).body(body).post(endPoint);
        logResponse();
        return this.response;
    }
    public Response put (String endPoint, String body){
        log.info("Performed PUT {}",endPoint);
        log.info("Body is {}",body);
        this.response = given().spec(requestSpecification).body(body).put(endPoint);
        logResponse();
        return this.response;
    }

    public Response patch (String endPoint, String body){
        log.info("Performed PATCH {}",endPoint);
        log.info("Body is {}",body);
        this.response = given().spec(requestSpecification).body(body).patch(endPoint);
        logResponse();
        return this.response;
    }

    private void logResponse (){
        log.warn("Response is ");
        log.warn(getResponse().getBody().asPrettyString());
        log.warn("Status code is {}",getResponse().getStatusCode());
    }

    public String getEndpoint (String...endPoints){
    StringBuilder endPoint = new StringBuilder();
    for (String arg : endPoints) {
        endPoint.append(arg).append(SLASH);
    }
        return endPoint.substring(0,endPoint.length()-1);
    }
}
