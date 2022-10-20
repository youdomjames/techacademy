package ca.techacademy.users.service;

import ca.techacademy.users.model.Credentials;
import ca.techacademy.users.model.Profile;
import ca.techacademy.users.model.User;
import ca.techacademy.users.repository.UserRepository;
import ca.techacademy.users.util.Generator;
import ca.techacademy.users.util.enums.Role;
import ca.techacademy.users.util.enums.Status;
import ca.techacademy.users.util.exception.FieldNotSupportedException;
import ca.techacademy.users.util.exception.FraudsterUserException;
import ca.techacademy.users.util.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final Generator generator;

    @Override
    public boolean addNewUser(Profile userProfile, Role role) {

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String temporaryPassword = generator.getAutoGeneratedPassword();
        log.info("Password = " + temporaryPassword);
        userProfile.setTelephoneNumber(userProfile.getTelephoneNumber()
                .replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3"));
        User newUSer = User.builder()
                .role(role)
                .status(Status.ACTIVE)
                .profile(userProfile)
                .credentials(Credentials.builder()
                        .email(userProfile.getEmail())
                        .password(bcrypt.encode(temporaryPassword))
                        .build())
                .build();
        User savedUser = userRepository.save(newUSer);
        return savedUser.getUserId() != null;
    }

    @Override
    public boolean updateUserProfile(String userId, Map<String, String> fields) throws ObjectNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(ObjectNotFoundException::new);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(User.class, key);
            if (field != null){
                throw new FieldNotSupportedException();
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, user, value);
        });
        userRepository.save(user);
        return true;
    }

    @Override
    public Profile getUserById(String userId) throws ObjectNotFoundException {
        return userRepository.findById(userId).orElseThrow(ObjectNotFoundException::new).getProfile();
    }

    @Override
    public Profile getUserByEmail(String userEmail) throws ObjectNotFoundException {
        return userRepository.findByEmail(userEmail).orElseThrow(ObjectNotFoundException::new).getProfile();
    }

    @Override
    public boolean updatePassword(String userId, String oldPassword, String newPassword) throws ObjectNotFoundException {
        userFraudsterCheck(userId, oldPassword);
        User user = userRepository.findById(userId).orElseThrow(ObjectNotFoundException::new);
        user.getCredentials().setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);

        return !bCryptPasswordEncoder.matches(oldPassword, userRepository.findById(userId).orElseThrow(ObjectNotFoundException::new).getCredentials().getPassword());
    }

    @Override
    public boolean pauseUser(String userId) throws ObjectNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(ObjectNotFoundException::new);
        user.setStatus(Status.INACTIVE);
        userRepository.save(user);
        return userRepository.findById(userId).orElseThrow(ObjectNotFoundException::new).getStatus().equals(Status.INACTIVE);
    }

    @Override
    public List<Profile> getAllUsers() throws ObjectNotFoundException {
        return userRepository.findAll().stream().map(User::getProfile).toList();
    }

    private void userFraudsterCheck(String userId, String password) throws FraudsterUserException {
        Optional<User> potentialUser = userRepository.findById(userId);
        if (potentialUser.isEmpty()) {
            throw new FraudsterUserException("{userId = " + userId + ", password = " + password + "}" + "MESSAGE = User Not Found");
        }
        User user = potentialUser.get();

        boolean isfraudster = !bCryptPasswordEncoder.matches(password, user.getCredentials().getPassword());
        if (isfraudster) {
            throw new FraudsterUserException("{userId = " + userId + ", password = " + password + "}" + "MESSAGE = Wrong password");
        }
    }
}
