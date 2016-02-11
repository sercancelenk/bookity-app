package sr.api.persistence.domain;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Version;

public abstract class Base {
	
	@Id
	@JsonProperty("id")
	private String id;
	
	@Version
	@JsonProperty("version")
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
	
}
