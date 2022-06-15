package airport;

import org.powermock.api.mockito.PowerMockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherTest {

    Weather weather = new Weather();

    public void generatesStormyCondition() {
        PowerMockito.mockStatic(Math.class);
        PowerMockito.when(Math.random()).thenReturn(0.8);
        assertEquals(weather.generateConditions(), "Stormy");
    }

    public void generatesSunnyCondition() {
        PowerMockito.mockStatic(Math.class);
        PowerMockito.when(Math.random()).thenReturn(0.2);
        assertEquals(weather.generateConditions(), "Sunny");
    }
}
