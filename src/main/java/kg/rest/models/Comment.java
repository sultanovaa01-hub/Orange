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

public class Comment extends BaseModel{
    Integer id;
    Integer post_id;
    String name;
    String email;
    String body;

}
