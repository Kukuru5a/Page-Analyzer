package hexlet.code.dto.urls;

import com.fasterxml.jackson.databind.ser.Serializers;
import hexlet.code.models.Url;
import hexlet.code.dto.BasePage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class UrlPage extends BasePage {
    private Url url;
}
