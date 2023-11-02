package hexlet.code.dto.urls;

import hexlet.code.dto.BasePage;
import hexlet.code.models.Url;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter

@AllArgsConstructor
@ToString
public class UrlsPg extends BasePage {
    List<Url> urlList;
}
