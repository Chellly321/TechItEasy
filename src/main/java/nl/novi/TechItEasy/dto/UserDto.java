package nl.novi.TechItEasy.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nl.novi.TechItEasy.models.Authority;
import nl.novi.TechItEasy.models.User;

import java.util.Set;

public class UserDto {

    public String username;
    public String password;
    public Boolean enabled;
    public String apikey;
    public String email;
    @JsonSerialize
    public Set<Authority> authorities;

    public static UserDto fromUser(User user){

        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApikey();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();

        return dto;

    }
}
