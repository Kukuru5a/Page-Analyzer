package hexlet.code.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Url {
    private Long id;
    private String name;
    private Timestamp createdAt;
}