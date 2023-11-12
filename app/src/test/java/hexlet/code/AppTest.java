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
    public void testHomePage() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
        }));
    }

    @Test
    public void testUrlPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testPostUrlPage() {
        JavalinTest.test(app, ((server, client) -> {
            var response = client.post("/urls");
            assertThat(response.code()).isEqualTo(200);
        }));
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
    public void testNotFoundUrlById() {
        JavalinTest.test(app, (server, client) -> {
            client.delete("/test/delete/777");
            var response = client.get("/urls/777");
            assertThat(response.code()).isEqualTo(404);
        });
    }

    @Test
    public void testNotCorrectUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=notCorrectUrl";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }

    @Test
    public void testUniqUrlValidation() throws SQLException {
        var url = new Url("https://javalintest.io");
        UrlRepository.save(url);

        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://javalintest.io";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }

    @Test
    public void testNullUrlValidation() throws SQLException {

        JavalinTest.test(app, (server, client) -> {
            var response = client.post("/urls", "");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
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
    public void testParsingResponse() throws SQLException, IOException {
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setBody(Files.readString(Paths.get("./src/test/resources/test.html")));

        mockWebServer.enqueue(mockResponse);
        var urlName = mockWebServer.url("/testParsingResponse");
        var url = new Url(urlName.toString());
        UrlRepository.save(url);

        JavalinTest.test(app, (server, client) -> {
            var response = client.post("/urls/" + url.getId() + "/checks", "");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains("Hello, World!</td>")
                    .contains("Sample Page</td>")
                    .contains("Open source Java</td>");
        });
    }


    @Test
    public void testTest() throws SQLException {
        MockWebServer web = new MockWebServer();

        String uRl = web.url("https://www.ok.ru").toString().replaceAll("/$", "");
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=" + uRl;
            assertThat(client.post("/urls", requestBody).code()).isEqualTo(200);
            var actualUrl = UrlRepository.findByName(uRl);
            assertThat(actualUrl).isNotNull();
            assertThat(actualUrl.getName()).isEqualTo(uRl);

            client.post("/urls/" + actualUrl.getId() + "/checks", "");
            var response = client.get("/urls/" + actualUrl.getId());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains(uRl);

            var actualCheckUrl = UrlCheckRepository
                    .findLatestChecks().get(actualUrl.getId());
            assertThat(actualCheckUrl).isNotNull();
            assertThat(actualCheckUrl.getStatusCode()).isEqualTo(200);
            assertThat(actualCheckUrl.getTitle())
                    .isEqualTo("Социальная сеть Одноклассники. Общение с друзьями в ОК."
                            + " Ваше место встречи с одноклассниками");
            assertThat(actualCheckUrl.getDescription())
                    .isEqualTo("Одноклассники.ру это социальная сеть,"
                            + " где вы можете найти своих старых друзей."
                            + " Общение, онлайн игры, подарки и открытки для друзей."
                            + " Приходите в ОК, делитесь своими эмоциями с друзьями, коллегами и одноклассниками.");
        });

    }
//    @Test
//    public void testFlash() throws SQLException {
//        var web = new MockWebServer();
//        String uRl = web.url("https://www.ok.ru").toString().replaceAll("/$", "");
//        JavalinTest.test(app, (server, client) -> {
//            var requestBody = "url=" + uRl;
//            assertThat(client.post("/urls", requestBody).code()).isEqualTo(200);
//            client.post("/urls", requestBody);
//            client.get("/urls");
//            var page = new BasePage();
//            assertThat(page.getFlash()).isEqualTo("Страница успешно добавлена");
//        });
        //как сделать тест на флешки ???

//    }
}
