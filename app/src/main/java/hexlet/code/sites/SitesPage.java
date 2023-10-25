package hexlet.code.sites;

import hexlet.code.models.Url;
import hexlet.code.pages.BasePage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SitesPage extends BasePage {
    private List<Url> sitesPage;
}
