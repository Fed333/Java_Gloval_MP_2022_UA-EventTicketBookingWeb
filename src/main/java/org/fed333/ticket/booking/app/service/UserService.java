package org.fed333.ticket.booking.app.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.repository.UserRepository;
import org.fed333.ticket.booking.app.service.component.SaveEntityValidator;
import org.fed333.ticket.booking.app.service.component.SlicePaginator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Setter
    private SlicePaginator paginator;

    @Setter
    private SaveEntityValidator<User, Long> saveUserValidator;

    private void init(){
        saveUserValidator.setRepository(userRepository);
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public User getUserByEmail(String email) {
        List<User> users = userRepository.getAllByEmail(email);
        if (users.isEmpty()){
            log.info("No user with email {} was found.", email);
            return null;
        }
        if (users.size() > 1) {
            throw new RuntimeException("More than one users was found!");
        }
        return users.get(0);
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return paginator.paginateStream(userRepository.getAll().stream().filter(u->u.getName().contains(name)), pageNum, pageSize).collect(Collectors.toList());
    }

    public User createUser(User user) {
        saveUserValidator.validateCreate(user);
        User saved = userRepository.save(user);
        log.info("User {} has been created successfully.", saved);
        return saved;
    }

    public User updateUser(User user) {
        saveUserValidator.validateUpdate(user);
        User saved = userRepository.save(user);
        log.info("User {} has been updated successfully.", saved);
        return saved;
    }

    public boolean deleteUser(long userId) {
        User removed = userRepository.remove(userId);
        if (Objects.nonNull(removed)) {
            log.info("User {} was deleted successfully.", removed);
            return true;
        }
        return false;
    }

}