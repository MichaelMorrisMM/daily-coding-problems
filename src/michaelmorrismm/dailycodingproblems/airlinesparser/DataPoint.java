package michaelmorrismm.dailycodingproblems.airlinesparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

class DataPoint {

    private String description;
    private String value;

    DataPoint(String description, String value) {
        this.description = description;
        this.value = value;
    }

    Element toXMLElement(Document doc) {
        Element element = doc.createElement("DataPoint");
        element.setAttribute("description", this.description);
        element.setAttribute("value", this.value);
        return element;
    }

    String getDescription() {
        return description;
    }

    String getValue() {
        return value;
    }

}
