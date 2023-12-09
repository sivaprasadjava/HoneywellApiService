package org.honeywell.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityBean {
	
	@JsonProperty("name")
    private String name;

    @JsonProperty("state")
    private String state;

    @JsonProperty("lat")
    private String lat;

    @JsonProperty("lon")
    private String lon;

}
