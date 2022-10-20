package ca.techacademy.users.service;

import ca.techacademy.users.model.Profile;
import ca.techacademy.users.util.enums.Role;
import ca.techacademy.users.util.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Map;

public interface UserService {
    boolean addNewUser(Profile userProfile, Role role);
    boolean updateUserProfile(String userId, Map<String, String> fields) throws ObjectNotFoundException;
    Profile getUserById(String userId) throws ObjectNotFoundException;
    Profile getUserByEmail(String userEmail) throws ObjectNotFoundException;
    boolean updatePassword(String userId, String oldPassword, String newPassword) throws ObjectNotFoundException;
    boolean pauseUser(String userId) throws ObjectNotFoundException;
    List<Profile> getAllUsers() throws ObjectNotFoundException;
}
