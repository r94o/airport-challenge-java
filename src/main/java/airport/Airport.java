package airport;

import java.util.ArrayList;

public class Airport {
    private final int CAPACITY;
    private Weather weather;
    ArrayList<Plane> hangar = new ArrayList<Plane>();

    public Airport(int capacity, Weather weather) {
        this.weather = weather;
        this.CAPACITY = capacity;
    }
    public Airport(Weather weather) {
        this.weather = weather;
        this.CAPACITY = 20;
    }
    public void land(Plane plane) {
        if (this.hangar.size() == CAPACITY) throw new ArrayIndexOutOfBoundsException("Airport is full");
        if (weather.generateConditions() == "Stormy") throw new IllegalArgumentException("Plane cannot land due to weather conditions");
        if (!plane.getFlyingStatus()) throw new IllegalArgumentException("Plane has already landed");
        plane.setFlyingStatus(false);
        this.hangar.add(plane);
    }
    public Plane takeoff(Plane plane) {
        if (!this.hangar.contains(plane) || plane.getFlyingStatus()) throw new IllegalArgumentException("Plane is not at this airport");
        if (weather.generateConditions() == "Stormy") throw new IllegalArgumentException("Plane cannot takeoff due to weather conditions");
        plane.setFlyingStatus(true);
        return this.hangar.remove(this.hangar.indexOf(plane));
    }
}
