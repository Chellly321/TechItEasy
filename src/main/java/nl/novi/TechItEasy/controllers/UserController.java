package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.dto.UserDto;
import nl.novi.TechItEasy.dto.UserInputDto;
import nl.novi.TechItEasy.exceptions.BadRequestException;
import nl.novi.TechItEasy.models.User;
import nl.novi.TechItEasy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public ResponseEntity<List<UserDto>> getUsers(){
        var dtos = new ArrayList<UserDto>();

        Collection<User> users = userService.getUsers();

        for (User user : users){
            dtos.add(UserDto.fromUser(user));
        }
        return ResponseEntity.ok().body(dtos);
    }
    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable("username") String username){

        var optionalUser = userService.getUser(username);

        if (optionalUser.isPresent()){
            var user = optionalUser.get();
            return ResponseEntity.ok().body(UserDto.fromUser(user));
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
    @PostMapping(value = "")
    public ResponseEntity<UserDto> createKlant(@RequestBody UserInputDto dto){

        var user = dto.toUser();

        String newUsername = userService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }
    @PutMapping(value = "/{username}")
    public ResponseEntity<UserDto> updateKlant(@PathVariable("username") String username, @RequestBody UserInputDto dto){

        var user = dto.toUser();

        userService.updateUser(username, user);

        return ResponseEntity.noContent().build();
    }
    @DeleteMapping (value = "/{username}")
    public ResponseEntity<Object> deleteKlant(@PathVariable("username") String username){
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username")String username){
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }
    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields){
      try {
          String authorityName = (String) fields.get("authority");
          userService.addAuthority(username, authorityName);
          return ResponseEntity.noContent().build();
      } catch (Exception ex){
          throw new BadRequestException();
      }
    }
    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority){
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }
}
