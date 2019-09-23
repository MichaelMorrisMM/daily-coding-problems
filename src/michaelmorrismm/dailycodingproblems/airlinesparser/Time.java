package michaelmorrismm.dailycodingproblems.airlinesparser;

import javax.json.JsonObject;
import java.util.Objects;

public class Time {

    private String label;
    private Integer year;
    private Integer month;

    Time(JsonObject json) {
        this.label = json.getString("label");
        this.year = json.getInt("year");
        this.month = json.getInt("month");
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Time && this.label.equals(((Time) object).getLabel());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.label);
    }

    String getLabel() {
        return this.label;
    }

    Integer getYear() {
        return this.year;
    }

    Integer getMonth() {
        return this.month;
    }

}
