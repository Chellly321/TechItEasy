package nl.novi.TechItEasy.service;

import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.models.Authority;
import nl.novi.TechItEasy.models.User;
import nl.novi.TechItEasy.repositories.UserRepository;
import nl.novi.TechItEasy.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private AuthorityRepository authorityRepository;

    public Collection<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUser(String username){
        return userRepository.findById(username);
    }
    public boolean userExists(String username){
        return userRepository.existsById(username);
    }

    public String createUser(User user){
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        user.setApikey(randomString);
        User newUser = userRepository.save(user);
        return newUser.getUsername();
    }

    public void deleteUser(String username){
        userRepository.deleteById(username);
    }
    public void updateUser(String username, User newUser){
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }
    public Set<Authority> getAuthorities(String username){
       if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
       User user = userRepository.findById(username).get();
       return user.getAuthorities();
    }
    public void addAuthority(String username, String authority){
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }
    public void removeAuthority(String username, String authority){
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

}

