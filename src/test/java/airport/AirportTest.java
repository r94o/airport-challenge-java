package airport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

public class AirportTest {

    @Test
    public void airportCanLandPlane() {
        Plane plane = mock(Plane.class);
        Airport airport = new Airport();
        assertDoesNotThrow(() -> airport.land(plane));
    }
    @Test
    public void airportCanTakeOffPlane() {
        Plane plane = mock(Plane.class);
        Airport airport = new Airport();
        airport.land(plane);
        assertSame(airport.takeoff(plane), plane);
    }
    @Test
    public void airportCantLandPlaneWhenFull() {
        Plane plane = mock(Plane.class);
        Plane anotherPlane = mock(Plane.class);
        Airport airport = new Airport(1);
        airport.land(plane);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> airport.land(anotherPlane));
        assertTrue(exception.getMessage().contains("Airport is full"));
    }

    @Test
    public void airportCantLandPlaneTwice() {
        Plane plane = mock(Plane.class);
        Airport airport = new Airport();
        airport.land(plane);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> airport.land(plane));
        assertTrue(exception.getMessage().contains("Plane has already landed"));
    }

    @Test
    public void airportCantTakeOffPlaneTwice() {
        Plane plane = mock(Plane.class);
        Airport airport = new Airport();
        airport.land(plane);
        airport.takeoff(plane);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> airport.takeoff(plane));
        assertTrue(exception.getMessage().contains("Plane is not at this airport"));
    }
}
