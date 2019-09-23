package michaelmorrismm.dailycodingproblems.airlinesparser;

import javax.json.JsonObject;
import java.util.Objects;

public class Carrier {

    private String code;
    private String name;

    Carrier(JsonObject json) {
        this.code = json.getString("code");
        this.name = json.getString("name");
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Carrier && this.code.equals(((Carrier) object).getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.code);
    }

    String getCode() {
        return this.code;
    }

    String getName() {
        return this.name;
    }

}
