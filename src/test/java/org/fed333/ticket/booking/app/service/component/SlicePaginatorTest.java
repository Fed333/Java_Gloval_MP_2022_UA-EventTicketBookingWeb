package org.fed333.ticket.booking.app.service.component;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SlicePaginator.class})
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class SlicePaginatorTest {

    private final List<Integer> testList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private final Stream<Integer> testStream = testList.stream();

    @Autowired
    private SlicePaginator slicePaginator;

    @Test(expected = RuntimeException.class)
    public void paginateList_ifInvalidCursorShouldThrowException() {
        int cursor = 0, size = 10;
        slicePaginator.paginateList(testList, cursor, size);
    }

    @Test(expected = RuntimeException.class)
    public void paginateStream_ifInvalidCursorShouldThrowException() {
        int cursor = 0, size = 10;
        slicePaginator.paginateStream(testStream, cursor, size);
    }

    @Test(expected = RuntimeException.class)
    public void paginateList_ifInvalidSizeShouldThrowException() {
        int cursor = 1, size = -1;
        slicePaginator.paginateList(testList, cursor, size);
    }

    @Test(expected = RuntimeException.class)
    public void paginateStream_ifInvalidSizeShouldThrowException() {
        int cursor = 1, size = -1;
        slicePaginator.paginateStream(testStream, cursor, size);
    }

    @Test
    public void paginateList_shouldGetSubList() {
        int cursor = 2, size = 4;
        List<Integer> expectedList = Arrays.asList(5, 6, 7, 8);

        assertThat(slicePaginator.paginateList(testList, cursor, size)).isEqualTo(expectedList);
    }

    @Test
    public void paginateStream_shouldGetSubList() {
        Stream<Integer> testStream = testList.stream();
        int cursor = 2, size = 4;
        List<Integer> expectedList = Arrays.asList(5, 6, 7, 8);

        assertThat(slicePaginator.paginateStream(testStream, cursor, size).collect(Collectors.toList())).isEqualTo(expectedList);
    }

    @Test
    public void paginateList_shouldGetFullList() {
        int cursor = 1, size = 10;
        List<Integer> expectedList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(slicePaginator.paginateList(testList, cursor, size)).isEqualTo(expectedList);
    }

    @Test
    public void paginateStream_shouldGetFullList() {
        int cursor = 1, size = 10;
        List<Integer> expectedList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(slicePaginator.paginateStream(testStream, cursor, size).collect(Collectors.toList())).isEqualTo(expectedList);
    }

    @Test
    public void paginateList_ifSizeOutOfBoundShouldGetFullList() {
        int cursor = 1, size = 100;
        List<Integer> expectedList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(slicePaginator.paginateList(testList, cursor, size)).isEqualTo(expectedList);
    }

    @Test
    public void paginateStream_ifSizeOutOfBoundShouldGetFullList() {
        Stream<Integer> testStream = testList.stream();
        int cursor = 1, size = 100;
        List<Integer> expectedList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(slicePaginator.paginateStream(testStream, cursor, size).collect(Collectors.toList())).isEqualTo(expectedList);
    }

    @Test
    public void paginateList_ifSizeOutOfBoundShouldGetSubList() {
        int cursor = 2, size = 4;
        List<Integer> expectedList = Arrays.asList(5, 6, 7, 8);

        assertThat(slicePaginator.paginateList(testList, cursor, size)).isEqualTo(expectedList);
    }

    @Test
    public void paginateStream_ifSizeOutOfBoundShouldGetSubList() {
        int cursor = 2, size = 4;
        List<Integer> expectedList = Arrays.asList(5, 6, 7, 8);

        assertThat(slicePaginator.paginateStream(testStream, cursor, size).collect(Collectors.toList())).isEqualTo(expectedList);
    }

}