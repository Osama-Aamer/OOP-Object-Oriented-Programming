public class TelevisionViewer {
    public static void main(String[] args) {
        Television tv = new Television();

        for (int day = 1; day <= 10; day++) {
            tv.turnOn();

            if (day % 2 == 1) {
                // Odd days: channels 8,9,10,1,2,3
                tv.nextChannel(); // 8
                tv.nextChannel(); // 9
                tv.nextChannel(); // 10
                tv.nextChannel(); // 1
                tv.nextChannel(); // 2
                tv.nextChannel(); // 3
            } else {
                // Even days: channels 4,5,6,7
                tv.nextChannel(); // 4
                tv.nextChannel(); // 5
                tv.nextChannel(); // 6
                tv.nextChannel(); // 7
            }

            tv.turnOff();
        }
    }
}