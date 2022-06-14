package airport;

public class Airport {
    private Plane hangar;

    public void land(Plane plane) {
        this.hangar = plane;
    }
    public Plane takeoff() {
        return this.hangar;
    }
}
