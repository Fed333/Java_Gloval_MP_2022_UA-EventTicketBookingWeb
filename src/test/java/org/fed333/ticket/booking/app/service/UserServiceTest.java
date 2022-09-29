package org.fed333.ticket.booking.app.service;

import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.repository.UserAccountRepository;
import org.fed333.ticket.booking.app.repository.UserRepository;
import org.fed333.ticket.booking.app.service.component.SaveEntityValidator;
import org.fed333.ticket.booking.app.util.PageUtil;
import org.fed333.ticket.booking.app.util.comparator.UserEqualityComparator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fed333.ticket.booking.app.utils.TestingDataUtils.createTestUser;
import static org.fed333.ticket.booking.app.utils.TestingDataUtils.createTestUserWithName;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository mockedRepository;

    @Mock
    private UserAccountService accountService;

    @Mock
    private UserAccountRepository accountRepository;

    @Mock
    private SaveEntityValidator<User, Long> mockedValidator;

    @InjectMocks
    private UserService userService;

    private User testUser;

    private UserEqualityComparator userComparator;

    @Before
    public void setUp() {
        userService.setSaveUserValidator(mockedValidator);
        testUser = createTestUser(1L);
        userComparator = new UserEqualityComparator();
    }

    @Test
    public void getUserById_shouldReturnFromRepository() {
        Long id = testUser.getId();
        when(mockedRepository.getById(id)).thenReturn(testUser);

        assertThat(userService.getUserById(id)).isEqualTo(testUser);
    }

    @Test
    public void getUserByEmail_shouldReturnFromRepository() {
        String email = testUser.getEmail();
        when(mockedRepository.getAllByEmail(email)).thenReturn(Collections.singletonList(testUser));

        assertThat(userService.getUserByEmail(email)).isEqualTo(testUser);
    }

    @Test
    public void getUserByEmail_ifMissingShouldReturnNull() {
        String email = testUser.getEmail();
        when(mockedRepository.getAllByEmail(email)).thenReturn(new ArrayList<>());

        assertThat(userService.getUserByEmail(email)).isEqualTo(null);
    }

    @Test(expected = RuntimeException.class)
    public void getUserByEmail_ifMoreThanOneUserFoundShouldThrowException() {
        String email = testUser.getEmail();
        when(mockedRepository.getAllByEmail(email)).thenReturn(Arrays.asList(createTestUser(1L), createTestUser(2L)));

        assertThat(userService.getUserByEmail(email)).isEqualTo(null);
    }

    @Test
    public void getUsersByName_shouldReturnFromRepository() {
        String testName = "testName";
        int cursor = 1, size = 5;
        PageUtil page = new PageUtil(cursor, size);
        List<User> testUsers = Stream.iterate(1L, i -> i + 1).limit(size).map(i -> createTestUserWithName(i, testName)).collect(Collectors.toList());
        when(mockedRepository.getAllByName(testName, page.getOffset(), page.getSize())).thenReturn(testUsers);

        assertThat(userService.getUsersByName(testName, page)).usingElementComparator(userComparator).isEqualTo(testUsers);
    }

    @Test
    public void createUser_shouldInvokeValidator() {
        userService.createUser(testUser);

        verify(mockedValidator).validateCreate(testUser);
    }

    @Test
    public void createUser_shouldInvokeRepository() {
        userService.createUser(testUser);

        verify(mockedRepository).save(testUser);
    }

    @Test
    public void updateUser_shouldInvokeValidator() {
        userService.updateUser(testUser);

        verify(mockedValidator).validateUpdate(testUser);
    }

    @Test
    public void updateUser_shouldInvokeRepository() {
        userService.updateUser(testUser);

        verify(mockedRepository).save(testUser);
    }

    @Test
    public void deleteUser_shouldInvokeRepository() {
        userService.deleteUser(testUser.getId());

        verify(mockedRepository).remove(testUser.getId());
    }

    @Test
    public void deleteUser_ifUserDeletedShouldReturnTrue() {
        when(mockedRepository.remove(testUser.getId())).thenReturn(testUser);
        boolean actual = userService.deleteUser(testUser.getId());

        assertThat(actual).isTrue();
    }

    @Test
    public void deleteUser_ifNoUserDeletedShouldReturnFalse() {
        boolean actual = userService.deleteUser(testUser.getId());

        assertThat(actual).isFalse();
    }
}