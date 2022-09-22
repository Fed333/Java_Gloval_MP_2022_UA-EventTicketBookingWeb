package org.fed333.ticket.booking.app.service.component;

import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SaveEntityValidatorTest {

    @Mock
    private UserRepository mockedRepository;

    @InjectMocks
    private SaveEntityValidator<User, Long> validator;

    private User testUser;

    @Before
    public void setUp() {
        testUser = User.builder()
                .id(1L)
                .name("Test")
                .email("test@mail.com").build();
    }

    @Test(expected = RuntimeException.class)
    public void validateCreate_ifExistsShouldThrowException() {
        when(mockedRepository.existsById(testUser.getId())).thenReturn(true);
        validator.validateCreate(testUser);
    }

    @Test
    public void validateCreate_ifNotExistsShouldNotThrowException() {
        when(mockedRepository.existsById(testUser.getId())).thenReturn(false);
        validator.validateCreate(testUser);
    }

    @Test(expected = RuntimeException.class)
    public void validateUpdate_ifNullIdShouldThrowException() {
        testUser.setId(null);
        validator.validateUpdate(testUser);
    }

    @Test(expected = RuntimeException.class)
    public void validateUpdate_ifMissingShouldThrowException() {
        when(mockedRepository.existsById(testUser.getId())).thenReturn(false);
        validator.validateUpdate(testUser);
    }

    @Test
    public void validateUpdate_ifPresentShouldNotThrowException() {
        when(mockedRepository.existsById(testUser.getId())).thenReturn(true);
        validator.validateUpdate(testUser);
    }

}