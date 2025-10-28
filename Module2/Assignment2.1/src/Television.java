//task1
public class Television {
    private boolean isOn;
    private int channel;
    private int day;

    public Television() {
        this.isOn = false;
        this.channel = 1;
        this.day = 1;
    }

    public void turnOn() {
        if (!isOn) {
            isOn = true;
            System.out.println("Woke up, day " + day);
        }
    }

    public void turnOff() {
        if (isOn) {
            isOn = false;
            System.out.println("Falling asleep");
            day++;
        }
    }

    public void nextChannel() {
        if (isOn) {
            channel = (channel % 10) + 1; // 1 to 10 cycle
            System.out.println("Watching channel " + channel);
        }
    }

    public boolean isOn() {
        return isOn;
    }
}