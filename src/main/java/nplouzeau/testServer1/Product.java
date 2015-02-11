package nplouzeau.testServer1;


import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: plouzeau
 * Date: 2013-02
 * Time: 21:23
 */

/**
 * @author Noël Plouzeau
 *         A simple product definition.
 *         Note the use of a UID generation operation.
 */
@XmlRootElement
public class Product {

	private String nom;
	private float prix;
	private String UID;

	/**
	 * Produces a unique ID to designate a Product in the server
	 *
	 * @return The UID as a String
	 */
	public String getUID() {
		if (this.UID == null) {
			this.UID = Integer.toHexString(this.hashCode());
		}
		return this.UID;
	}

	public void setUID(String uid) {
		this.UID = uid;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	//  Explicit constructor required by the JAX-RS system
	public Product() {
	}

	public Product(String nom, float prix) {
		this.nom = nom;
		this.prix = prix;
	}
}

