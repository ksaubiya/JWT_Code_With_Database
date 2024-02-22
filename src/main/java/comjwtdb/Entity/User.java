package comjwtdb.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "user")
public class User implements UserDetails {
/* Query => User Table=>

create table user (user_id varchar(255) not null, u_about varchar(255), u_email varchar(255),
user_name varchar(255), u_pass varchar(255), primary key (user_id)) engine=InnoDB
 */

    @Id
    private String userId;

    @Column(name="user_name")
    private String name;

    @Column(name="u_email")
    private String email;

    @Column(name="u_pass")
    private String password;

    @Column(name="u_about")
    private String about;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private  RefreshToken refreshToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
