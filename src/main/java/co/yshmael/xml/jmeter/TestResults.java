package co.yshmael.xml.jmeter;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class TestResults {
	
	@XStreamAsAttribute
	private String version;
	
	@XStreamImplicit(itemFieldName="sample")
	private List<Sample> sample;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<Sample> getSample() {
		return sample;
	}

	public void setSample(List<Sample> sample) {
		this.sample = sample;
	}

	

}
