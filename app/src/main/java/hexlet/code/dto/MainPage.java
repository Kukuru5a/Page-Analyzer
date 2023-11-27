package hexlet.code.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class MainPage {
    private Boolean visited;
    private String currentUser;

    public MainPage(String currentUser) {
        this.currentUser = currentUser;
    }

    public Boolean isVisited() {
        return visited;
    }
}
