package ca.techacademy.users.service;

import ca.techacademy.users.model.DTO.CredentialsDTO;
import ca.techacademy.users.model.Profile;
import ca.techacademy.users.util.enums.Role;
import ca.techacademy.users.util.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Map;

public interface UserService {
    Profile addNewUser(Profile userProfile, Role role);
    Profile updateUserProfile(String userId, Map<String, Object> fields) throws ObjectNotFoundException;
    Profile getUserById(String userId) throws ObjectNotFoundException;
    Profile getUserByEmail(String userEmail) throws ObjectNotFoundException;
    boolean updatePassword(String userId, CredentialsDTO credentialsDTO) throws ObjectNotFoundException;
    boolean pauseUser(String userId) throws ObjectNotFoundException;
    List<Profile> getAllUsers() throws ObjectNotFoundException;
}
