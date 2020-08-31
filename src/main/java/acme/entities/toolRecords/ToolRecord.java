
package acme.entities.toolRecords;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.datatypes.CustomEmail;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ToolRecord extends DomainEntity {

	// Serialisation identifier ---------------------------

	private static final long	serialVersionUID	= 1L;

	// Attribute ------------------------------------------

	@NotBlank
	private String				title;

	@NotBlank
	private String				activitySector;

	@NotBlank
	private String				inventor;

	@NotBlank
	private String				description;

	@URL
	@NotBlank
	private String				webSite;

	@NotNull
	@Valid
	private CustomEmail			email;

	private boolean				openSource;

	@Range(min = -5, max = 5)
	private Double				stars;


	@Transient
	public String getLicence() {
		String res = "Open-source";
		if (!this.openSource) {
			res = "Closed-source";
		}
		return res;
	}

}
