package airport;

import java.util.ArrayList;

public class Airport {
    private final int CAPACITY;
    public Airport(int capacity) {
        this.CAPACITY = capacity;
    }
    public Airport() {
       this.CAPACITY = 20;
    }
    ArrayList<Plane> hangar = new ArrayList<Plane>();
    public void land(Plane plane) {
        if (this.hangar.size() == CAPACITY) throw new ArrayIndexOutOfBoundsException("Airport is full");
        if (!plane.getFlyingStatus()) throw new IllegalArgumentException("Plane has already landed");
        plane.setFlyingStatus(false);
        this.hangar.add(plane);

    }
    public Plane takeoff(Plane plane) {
        if (!this.hangar.contains(plane)) throw new IllegalArgumentException("Plane is not at this airport");
        plane.setFlyingStatus(true);
        return this.hangar.remove(this.hangar.indexOf(plane));
    }
}
