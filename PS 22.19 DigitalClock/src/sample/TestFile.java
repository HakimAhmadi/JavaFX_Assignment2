package sample;

import java.util.Calendar;
import java.util.Date;

public class TestFile {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        // if it's after noon
        if(cal.get(Calendar.AM_PM) == Calendar.PM) {
            int clockHour = cal.get(Calendar.HOUR);
            int clockTotalMin = (clockHour + 12)*60 + cal.get(Calendar.MINUTE);

            if(clockTotalMin >= 1440)
                clockTotalMin = cal.get(Calendar.MINUTE);
                if (!Character.isDigit(clockTotalMin)){
                    System.out.println(clockTotalMin);
                }


        }
        else {
            // if it's before noon
            int clockHour = cal.get(Calendar.HOUR);
            if (!Character.isDigit(clockHour)){
                System.out.println(clockHour);

            }
        }


    }
}
