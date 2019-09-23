package michaelmorrismm.dailycodingproblems.airlinesparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.json.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.*;

class Parser {

    private File airlinesFile;
    private OutputStream output;

    private JsonArray fileAsArray = JsonValue.EMPTY_JSON_ARRAY;
    private Map<String, Airport> airportMap = new HashMap<>();
    private Map<String, Carrier> carrierMap = new HashMap<>();
    private Map<String, Time> timeMap = new HashMap<>();
    private List<StatisticGroup> statistics = new LinkedList<>();

    Parser(final File airlinesFile, OutputStream output) {
        this.airlinesFile = airlinesFile;
        this.output = output;
    }

    void parse() throws Exception {
        try (JsonReader reader = Json.createReader(new FileInputStream(this.airlinesFile))) {
            fileAsArray = reader.readArray();
        }

        // Requested data points
        int totalNumberOfFlights = 0;
        int numberOfFlightsDelayedBySecurity = 0;
        int numberOfFlightsDelayedByCarrier = 0;
        int numberOfFlightsDelayedByNationalAviationSystem = 0;
        Map<Airport, Integer> airportSecurityDelays = new HashMap<>();
        Map<Airport, Integer> airportTotalFlights = new HashMap<>();

        for (JsonObject group : fileAsArray.getValuesAs(JsonObject.class)) {
            String airportCode = group.getJsonObject("airport").getString("code");
            if (!this.airportMap.containsKey(airportCode)) {
                this.airportMap.put(airportCode, new Airport(group.getJsonObject("airport")));
            }
            Airport airport = this.airportMap.get(airportCode);

            String carrierCode = group.getJsonObject("carrier").getString("code");
            if (!this.carrierMap.containsKey(carrierCode)) {
                this.carrierMap.put(carrierCode, new Carrier(group.getJsonObject("carrier")));
            }
            Carrier carrier = this.carrierMap.get(carrierCode);

            String timeLabel = group.getJsonObject("time").getString("label");
            if (!this.timeMap.containsKey(timeLabel)) {
                this.timeMap.put(timeLabel, new Time(group.getJsonObject("time")));
            }
            Time time = this.timeMap.get(timeLabel);

            StatisticGroup stats = new StatisticGroup(airport, carrier, time, group.getJsonObject("statistics"));
            this.statistics.add(stats);

            // Process data points
            totalNumberOfFlights += stats.getFlightsStat(StatisticGroup.FLIGHT_STATUS_TOTAL);
            numberOfFlightsDelayedBySecurity += stats.getNumOfDelays(StatisticGroup.DELAY_TYPE_SECURITY);
            numberOfFlightsDelayedByCarrier += stats.getNumOfDelays(StatisticGroup.DELAY_TYPE_CARRIER);
            numberOfFlightsDelayedByNationalAviationSystem += stats.getNumOfDelays(StatisticGroup.DELAY_TYPE_NATIONAL_AVIATION_SYSTEM);
            if (!airportSecurityDelays.containsKey(airport)) {
                airportSecurityDelays.put(airport, 0);
            }
            airportSecurityDelays.put(airport, airportSecurityDelays.get(airport) + stats.getNumOfDelays(StatisticGroup.DELAY_TYPE_SECURITY));
            if (!airportTotalFlights.containsKey(airport)) {
                airportTotalFlights.put(airport, 0);
            }
            airportTotalFlights.put(airport, airportTotalFlights.get(airport) + stats.getFlightsStat(StatisticGroup.FLIGHT_STATUS_TOTAL));
        }

        // Final computation of data points and XML creation
        Airport airportWithMaxSecurityDelays = null;
        Airport airportWithMinSecurityDelays = null;
        for (Map.Entry<Airport, Integer> entry : airportSecurityDelays.entrySet()) {
            if (airportWithMaxSecurityDelays == null || airportSecurityDelays.get(airportWithMaxSecurityDelays) < entry.getValue()) {
                airportWithMaxSecurityDelays = entry.getKey();
            }
            if (airportWithMinSecurityDelays == null || airportSecurityDelays.get(airportWithMinSecurityDelays) > entry.getValue()) {
                airportWithMinSecurityDelays = entry.getKey();
            }
        }

        Airport airportWithMaxFlights = null;
        for (Map.Entry<Airport, Integer> entry : airportTotalFlights.entrySet()) {
            if (airportWithMaxFlights == null || airportTotalFlights.get(airportWithMaxFlights) < entry.getValue()) {
                airportWithMaxFlights = entry.getKey();
            }
        }

        Document dataPointsDoc = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .newDocument();
        Element root = dataPointsDoc.createElement("DataPoints");
        dataPointsDoc.appendChild(root);

        List<DataPoint> dataPoints = new ArrayList<>(Arrays.asList(
                new DataPoint(
                        "Total number of airports",
                        "" + this.airportMap.keySet().size()),
                new DataPoint(
                        "Total number of flights",
                        "" + totalNumberOfFlights),
                new DataPoint(
                        "Percentage of total flights delayed by 'security'",
                        String.format("%.2f%%", (double) numberOfFlightsDelayedBySecurity / totalNumberOfFlights * 100)),
                new DataPoint(
                        "Percentage of total flights delayed by 'carrier'",
                        String.format("%.2f%%", (double) numberOfFlightsDelayedByCarrier / totalNumberOfFlights * 100)),
                new DataPoint(
                        "Percentage of total flights delayed by 'national aviation system'",
                        String.format("%.2f%%", (double) numberOfFlightsDelayedByNationalAviationSystem / totalNumberOfFlights * 100)),
                new DataPoint(
                        "Airport with the highest number of delays due to 'security'",
                        airportWithMaxSecurityDelays.getCode()),
                new DataPoint(
                        "Airport with the lowest number of delays due to 'security'",
                        airportWithMinSecurityDelays.getCode()),
                new DataPoint(
                        "Airport with the most total flights",
                        airportWithMaxFlights.getCode())
        ));
        for (DataPoint dataPoint : dataPoints) {
            root.appendChild(dataPoint.toXMLElement(dataPointsDoc));
        }

        Transformer transformer = TransformerFactory
                .newInstance()
                .newTransformer();
        transformer.transform(new DOMSource(dataPointsDoc), new StreamResult(this.output));
    }

}
