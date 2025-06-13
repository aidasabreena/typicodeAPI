import com.typicode.api.utils.ApiUtils;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;

public class UserSteps {
    private Response response;

    @Given("I send a GET request to {string}")
    public void i_send_get_request_to(String endpoint){
        response = ApiUtils.get(endpoint);
    }