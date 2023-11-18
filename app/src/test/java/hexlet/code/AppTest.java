package hexlet.code;

import hexlet.code.models.Url;
import hexlet.code.repositories.UrlCheckRepository;
import hexlet.code.repositories.UrlRepository;
import io.javalin.http.Context;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class AppTest {
    Javalin app;
    private final Context ctx = mock(Context.class);
    private MockWebServer mockWebServer;


    @BeforeEach
    public void setUpMock() throws IOException, SQLException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        app = App.getApp();

    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
        });
    }


    @Test
    public void testUrlPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
        });
    }



    @Test
    public void testWrongUrlIdPage() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/urls/10000");
            assertThat(response.code()).isEqualTo(404);
        }));
    }

    @Test
    public void testAddUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "name=https://www.vk.com";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("name");
        });
    }


    @Test
    public void testNotCorrectUrl() throws SQLException {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=notCorrectUrl";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
            // should be thrown an exception

        });
        assertThat(!(UrlRepository.getEntities().contains("notCorrectUrl")));
    }
    // make a check that there is a new post made in DB L95 & L118. Done

    @Test
    public void testUniqueUrlValidation() throws SQLException {
        var url = new Url("https://javalintest.io");
        UrlRepository.save(url);

        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://javalintest.io";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
        assertThat(UrlRepository.getEntities().contains(url));
    }
    //needs to be checked through DB too. Done
    @Test
    public void testNullUrlValidation() throws SQLException {

        JavalinTest.test(app, (server, client) -> {
            var response = client.post("/urls", "");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
            //Url check must throw an exception
            assertThat(UrlRepository.getEntities().isEmpty());
        });
    }

    @Test
    public void testCheckShowUrl() throws SQLException {
        var url = new Url("https://www.ok.ru");
        UrlRepository.save(url);

        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/" + url.getId());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains("Проверки")
                    .contains("https://www.ok.ru");
        });
    }



    @Test
    public void testTest() throws SQLException, IOException {
        MockWebServer web = new MockWebServer();

        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody(Files.readString(Paths.get("./src/test/resources/test2.html")));
        mockWebServer.enqueue(mockResponse);
        var urlName = mockWebServer.url("/testStoreResponse");
        var url = new Url(urlName.toString());
        UrlRepository.save(url);

        JavalinTest.test(app, (server, client) -> {
            var requestFormParam = "url=" + url.getName();
            assertThat(client.post("/urls", requestFormParam).code()).isEqualTo(200);
            var actualUrl = UrlRepository.findByName(url.getName());
            assertThat(actualUrl).isNotNull();
            assertThat(actualUrl.getName()).isEqualTo(url.getName());

            client.post("/urls/" + actualUrl.getId() + "/checks", "");
            var response = client.get("/urls/" + actualUrl.getId());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains(url.getName());


            var actualCheckUrl = UrlCheckRepository
                    .findLatestChecks().get(actualUrl.getId());
            assertThat(actualCheckUrl).isNotNull();
            assertThat(actualCheckUrl.getStatusCode()).isEqualTo(200);
            assertThat(actualCheckUrl.getTitle())
                    .isEqualTo("Imagine Dragons - Demons");
            assertThat(actualCheckUrl.getDescription())
                    .isEqualTo("When the days are cold and the cards all fold," +
                            " and the saints we see are all made of gold");
            assertThat((actualCheckUrl.getH1())).isEqualTo("Night Vision");
        });

    }
}
//after all I need to recreate a Build.yml file and correct a Read.md file
