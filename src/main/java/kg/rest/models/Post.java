package kg.rest.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Post extends BaseModel{

    Integer id;
    Integer user_id;
    String title;
    String body;
}
