package hexlet.code.models;

import hexlet.code.pages.BasePage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UrlPage {
    private Url url;
    private BasePage page;

    public UrlPage(Url url) {
        this.url = url;
    }
}
