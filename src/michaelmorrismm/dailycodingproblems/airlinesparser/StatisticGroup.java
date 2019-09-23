package michaelmorrismm.dailycodingproblems.airlinesparser;

import javax.json.JsonObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StatisticGroup {

    private Airport airport;
    private Carrier carrier;
    private Time time;

    private Map<String, Integer> flights = new HashMap<>();
    private Map<String, Integer> numOfDelays = new HashMap<>();
    private Map<String, Integer> minutesDelayed = new HashMap<>();

    static final String FLIGHT_STATUS_CANCELLED = "cancelled";
    static final String FLIGHT_STATUS_ON_TIME = "on time";
    static final String FLIGHT_STATUS_TOTAL = "total";
    static final String FLIGHT_STATUS_DELAYED = "delayed";
    static final String FLIGHT_STATUS_DIVERTED = "diverted";

    static final String DELAY_TYPE_LATE_AIRCRAFT = "late aircraft";
    static final String DELAY_TYPE_WEATHER = "weather";
    static final String DELAY_TYPE_CARRIER = "carrier";
    static final String DELAY_TYPE_SECURITY = "security";
    static final String DELAY_TYPE_TOTAL = "total";
    static final String DELAY_TYPE_NATIONAL_AVIATION_SYSTEM = "national aviation system";

    StatisticGroup(Airport airport, Carrier carrier, Time time, JsonObject json) {
        this.airport = airport;
        this.carrier = carrier;
        this.time = time;

        JsonObject flightsJSON = json.getJsonObject("flights");
        for (String flightsKey : flightsJSON.keySet()) {
            this.flights.put(flightsKey, flightsJSON.getInt(flightsKey));
        }
        JsonObject numOfDelaysJSON = json.getJsonObject("# of delays");
        for (String numOfDelaysKey : numOfDelaysJSON.keySet()) {
            this.numOfDelays.put(numOfDelaysKey, numOfDelaysJSON.getInt(numOfDelaysKey));
        }
        JsonObject minsDelayedJSON = json.getJsonObject("minutes delayed");
        for (String minsDelayedKey : minsDelayedJSON.keySet()) {
            this.minutesDelayed.put(minsDelayedKey, minsDelayedJSON.getInt(minsDelayedKey));
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof StatisticGroup) {
            StatisticGroup objectAsStatisticGroup = (StatisticGroup) object;
            return this.airport.equals(objectAsStatisticGroup.getAirport()) &&
                    this.carrier.equals(objectAsStatisticGroup.getCarrier()) &&
                    this.time.equals(objectAsStatisticGroup.getTime());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.airport, this.carrier, this.time);
    }

    Airport getAirport() {
        return this.airport;
    }

    Carrier getCarrier() {
        return this.carrier;
    }

    Time getTime() {
        return this.time;
    }

    Integer getFlightsStat(String flightStatus) {
        return this.flights.getOrDefault(flightStatus, 0);
    }

    Integer getNumOfDelays(String delayType) {
        return this.numOfDelays.getOrDefault(delayType, 0);
    }

    Integer getMinutesDelayed(String delayType) {
        return this.minutesDelayed.getOrDefault(delayType, 0);
    }

}
