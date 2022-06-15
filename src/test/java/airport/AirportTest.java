package airport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AirportTest {

    @Nested
    class underNormalConditions {
        Weather weather = mock(Weather.class);
        Plane plane = mock(Plane.class);
        Airport airport = new Airport(weather);

        @BeforeEach
        public void init() {
            when(weather.generateConditions()).thenReturn("Sunny");
            when(plane.getFlyingStatus()).thenReturn(true).thenReturn(false);
        }
        @Test
        public void airportCanLandPlane() {
            assertDoesNotThrow(() -> airport.land(plane));
        }
        @Test
        public void airportCanTakeOffPlane() {
            airport.land(plane);
            assertSame(airport.takeoff(plane), plane);
        }
        @Test
        public void airportCantLandPlaneTwice() {
            airport.land(plane);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> airport.land(plane));
            assertTrue(exception.getMessage().contains("Plane has already landed"));
        }
        @Test
        public void airportCantTakeOffPlaneTwice() {
            airport.land(plane);
            airport.takeoff(plane);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> airport.takeoff(plane));
            assertTrue(exception.getMessage().contains("Plane is not at this airport"));
        }
        @Test
        public void planeIsMarkedAsFlyingWhenTakingOff() {
            airport.land(plane);
            airport.takeoff(plane);
            verify(plane).setFlyingStatus(true);
        }
        @Test
        public void planeIsMarkedAsNotFlyingWhenLanding() {
            airport.land(plane);
            verify(plane).setFlyingStatus(false);
        }
    }
    @Test
    public void airportCantLandPlaneWhenFull() {
        Weather weather = mock(Weather.class);
        when(weather.generateConditions()).thenReturn("Sunny");
        Plane plane = mock(Plane.class);
        when(plane.getFlyingStatus()).thenReturn(true).thenReturn(false);
        Plane anotherPlane = mock(Plane.class);
        Airport airport = new Airport(1, weather);
        airport.land(plane);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> airport.land(anotherPlane));
        assertTrue(exception.getMessage().contains("Airport is full"));
    }
    @Test
    public void planeCantLandWhenStormy() {
        Plane plane = mock(Plane.class);
        Weather weather = mock(Weather.class);
        when(weather.generateConditions()).thenReturn("Stormy");
        Airport airport = new Airport(weather);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> airport.land(plane));
        assertTrue(exception.getMessage().contains("Plane cannot land due to weather conditions"));
    }
    @Test
    public void planeCantTakeoffWhenStormy() {
        Plane plane = mock(Plane.class);
        when(plane.getFlyingStatus()).thenReturn(true).thenReturn(false);
        Weather weather = mock(Weather.class);
        when(weather.generateConditions()).thenReturn("Sunny").thenReturn("Stormy");
        Airport airport = new Airport(weather);
        airport.land(plane);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> airport.takeoff(plane));
        assertTrue(exception.getMessage().contains("Plane cannot takeoff due to weather conditions"));
    }
}
