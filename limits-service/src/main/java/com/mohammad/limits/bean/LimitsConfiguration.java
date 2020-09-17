package com.mohammad.limits.bean;

//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties("limits-service")
@ConfigurationPropertiesScan("limits-service")
public class LimitsConfiguration {

	private int minimum;
	private int maximum;
	
	public LimitsConfiguration() { }
	
	public LimitsConfiguration(int maximum, int minimum) {
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
}
