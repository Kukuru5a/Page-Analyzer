package hexlet.code.dto.urls;

import hexlet.code.models.Url;
import hexlet.code.dto.BasePage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UrlsPage extends BasePage {
    private List<Url> urlList;



}
