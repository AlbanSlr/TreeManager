package fr.treemanager.models.association;

import java.util.Calendar;
import java.util.Date;

public class BudgetYear {

    private Date begin;
    private Date end;

    public BudgetYear() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        this.begin = calendar.getTime();

        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);

        this.end = calendar.getTime();

    }

    public boolean include(Date date) {
        return date.after(begin) && date.before(end);
    }

    public void nextYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.begin);
        calendar.add(Calendar.YEAR, 1);
        this.begin = calendar.getTime();

        calendar.setTime(this.end);
        calendar.add(Calendar.YEAR, 1);
        this.end = calendar.getTime();
    }

    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.begin);
        int year = calendar.get(Calendar.YEAR);
        return year + " - " + (year + 1);
    }
}
