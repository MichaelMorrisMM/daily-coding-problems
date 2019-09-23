package michaelmorrismm.dailycodingproblems.airlinesparser;

import javax.json.JsonObject;
import java.util.Objects;

class Airport {

    private String code;
    private String name;

    Airport(JsonObject json) {
        this.code = json.getString("code");
        this.name = json.getString("name");
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Airport && this.code.equals(((Airport) object).getCode());
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
