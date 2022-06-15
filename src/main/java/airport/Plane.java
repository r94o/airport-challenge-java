package airport;

public class Plane {
    private boolean flyingStatus = true;

    public void setFlyingStatus(Boolean status) {
        this.flyingStatus = status;

    }
    public boolean getFlyingStatus() {
        return this.flyingStatus;
    }
}
