package com.tourMaster.letsTour.modals;

import java.util.List;

public class Filter {
    public Filter(String type, List<String> values) {
        this.type = type;
        this.values = values;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    private String type;
    private List<String> values;
}
