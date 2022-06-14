package airport;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertSame;
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
        assertSame(airport.takeoff(), plane);
    }
}
