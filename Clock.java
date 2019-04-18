import java.util.Calendar;

public class Clock {
  Calendar c=Calendar.getInstance();
  int timeOfDay=c.get(Calendar.HOUR_OF_DAY);
  public String tellPhase() {
    if(timeOfDay>=0 && timeOfDay<=12) {
      return "Good morning";
    }
    else if(timeOfDay>12 && timeOfDay<=17) {
      return "Good evening";
    }
    if(timeOfDay>17 && timeOfDay<=24) {
      return "Good evening";
    }
    return null;
  }
}
