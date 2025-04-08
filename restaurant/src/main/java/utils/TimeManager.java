package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeManager {

    private LocalDateTime currentTime;

    public TimeManager(LocalDateTime startTime) {
        this.currentTime = startTime;
    }

    public static TimeManager startNewGame() {

        return new TimeManager(LocalDateTime.of(2020, 1, 1, 8, 0));
    }

    public void addHours(int hours) {
        currentTime = currentTime.plusHours(hours);
    }

    public void addDays(int days) {
        currentTime = currentTime.plusDays(days);
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public String getFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return currentTime.format(formatter);
    }
}
