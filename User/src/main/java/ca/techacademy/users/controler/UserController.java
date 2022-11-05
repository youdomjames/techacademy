package ca.techacademy.users.controler;

import ca.techacademy.users.model.DTO.CredentialsDTO;
import ca.techacademy.users.model.Profile;
import ca.techacademy.users.service.UserService;
import ca.techacademy.users.util.enums.Role;
import ca.techacademy.users.util.exception.DuplicateObjectException;
import ca.techacademy.users.util.exception.FieldNotSupportedException;
import ca.techacademy.users.util.exception.FraudsterUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ExceptionHandler(DuplicateObjectException.class)
    public ResponseEntity<Profile> addUserHandler(@RequestBody Profile profile, @RequestParam Role role){
        return new ResponseEntity<>(userService.addNewUser(profile, role), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getUserByIdHandler(@PathVariable("id") String userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Profile> getUserByEmailHandler(@RequestParam String email){
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }
    @PatchMapping("{id}")
    @ExceptionHandler(FieldNotSupportedException.class)
    public ResponseEntity<Profile> updateFieldsHandler(@PathVariable("id") String userId, @RequestBody Map<String, Object> fields){
        return new ResponseEntity<>(userService.updateUserProfile(userId, fields), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @ExceptionHandler(FraudsterUserException.class)
    public ResponseEntity<Boolean> updatePasswordHandler(@PathVariable("id") String userId, @RequestBody CredentialsDTO credentialsDTO){
        return new ResponseEntity<>(userService.updatePassword(userId, credentialsDTO), HttpStatus.OK);
    }
    @PutMapping("/{id}/inactivate")
    public ResponseEntity<Boolean> pauseUserHandler(@PathVariable("id") String userId){
        return new ResponseEntity<>(userService.pauseUser(userId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Profile>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
