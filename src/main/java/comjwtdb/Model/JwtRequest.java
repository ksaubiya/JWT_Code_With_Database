package comjwtdb.Model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtRequest {
    private String email;

    private String password;


}
