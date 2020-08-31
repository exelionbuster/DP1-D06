
package acme.datatypes;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import acme.framework.datatypes.DomainDatatype;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CustomEmail extends DomainDatatype {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	private String				displayName;

	@NotBlank
	@Email(regexp = "^([a-zA-Z0-9!#$%&'*+\\/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+\\/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)$")
	private String				email;

}
