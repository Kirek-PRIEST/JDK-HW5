public class Fork {
    private volatile boolean used;

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isUsed() {
        return used;
    }
}
