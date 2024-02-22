package comjwtdb.Model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtResponse {
    private String token;
    private String refreshToken;
    private String username;

}
/*

"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYWtoaUBnbWFpbC5jb20iLCJpYXQiOjE3MDU1NzMzNzUsImV4cCI6MTcwNTU3Njk3NX0.nMw3rsxdZ4XmUJ3IFk93ImVYsacJAACjWmbQQY9kz3rpJH0uNwGvCWFDl_3O-qRp1mScBHZxmYfFCDGRxKX54g",
    "refreshToken": "7edc97ad-efb3-4cb4-bf3d-9887da20fc64",
    "username": "sakhi@gmail.com"
 */