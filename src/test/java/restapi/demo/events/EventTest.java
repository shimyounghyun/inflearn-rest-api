package restapi.demo.events;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    public void builder() {
        Event event = Event.builder()
                .name("Spring REST API")
                .description("REST API development with Spring")
                .build();
        assertNotNull(event);
    }

    @Test
    public void javaBean() {
        // Given
        String name = "Event";
        String description = "Spring";

        // When
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        //Then
        assertEquals(event.getName(), name);
        assertEquals(event.getDescription(), description);
    }

    @ParameterizedTest
//    @CsvSource({
//            "0, 0, true",
//            "100, 0, false",
//            "0, 100, false"
//    })
    @MethodSource("isFree")
    public void testFree(int basePrice, int maxPrice, boolean isFree) {
        // Given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();
        // When
        event.update();

        // Then
        if (isFree)
            assertTrue(event.isFree());
        else
            assertFalse(event.isFree());
    }

    private static Stream<Arguments> isFree() {
        return Stream.of(
                Arguments.of(0,0,true),
                Arguments.of(100, 0, false),
                Arguments.of(0, 100, false)
        );
    }

    @ParameterizedTest
    @MethodSource("isOffline")
    public void testOffline() {
        // Given
        Event event = Event.builder()
                .location("강남역")
                .build();

        // When
        event.update();

        // Then
        assertTrue(event.isOffline());

        // Given
        event = Event.builder()
                .build();

        // When
        event.update();

        // Then
        assertFalse(event.isOffline());
    }

    private static Stream<Arguments> isOffline() {
        return Stream.of(
                Arguments.of("강남", true),
                Arguments.of(null, false),
                Arguments.of("    ", false)
        );
    }
}