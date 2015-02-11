package nplouzeau.testServer1;


import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * User: plouzeau
 * Date: 2013-02
 * Time: 08:54
 */
@Singleton
@Path("/produits")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ProductManager {
	private List<Product> productList;

	public ProductManager() {

		productList = new ArrayList<Product>();

		productList.add(new Product("Sandwich au jambon d'écrevisse", 3.0f));
		productList.add(new Product("Sandwich au fluorure de sodium", 3.0f));
		productList.add(new Product("Sandwich aux haricots verts", 3.0f));
		productList.add(new Product("Pizza", 3.0f));
		productList.add(new Product("Panini au gorgonzola", 4.0f));


	}

	/**
	 * Default action for the produits manager: return all products
	 *
	 * @return The list of products (all fields)
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public synchronized List<Product> getProductList() {
		return this.productList;
	}

	/**
	 * Add a new product to the list.
	 * A new UID is generated automatically in the server
	 *
	 * @param p Product to add       (without an UID)
	 * @return Product added          (with an UID)
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public synchronized Product addProduit(Product p) {
		this.productList.add(p);
		return p;
	}

	/**
	 * Get a specific product  by UID
	 *
	 * @param uid The UID of the product to fetch, passed as a PathParam by the client
	 * @return The product with the correspond UID, or else null if none found
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{uid}")
	public synchronized Product getProduitByUID(@PathParam("uid") String uid) {
		for (Product p : productList) {
			if (p.getUID().equals(uid)) return p;
		}
		return null;                               // Aucun produit correspondant à l'uid
	}

	/**
	 * Get a specific product  by name .
	 * Be careful with white space in names,
	 * as browsers convert them.
	 *
	 * @param name The UID of the product to fetch, passed as a QueryParam by the client
	 * @return The product with the correspond UID, or else null if none found
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("find")
	public synchronized Product getProduitByName(@QueryParam("name") String name) {
		for (Product p : this.productList) {
			if (p.getNom().equals(name))
				return p;                       // Product found
		}
		return null;                // No product found
	}

	/**
	 * Retrieve the first product from the list
	 *
	 * @return The first product if the list is not empty, or else null
	 */
	@GET
	@Path("first")
	@Produces({MediaType.APPLICATION_JSON})
	public synchronized Product premierProduit() {
		if (!productList.isEmpty()) {
			return productList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Remove a product
	 *
	 * @param uid The UID of the product to be removed
	 */
	@DELETE
	@Path("{uid}")
	public synchronized void deleteProduit(@PathParam("uid") String uid) {
		Product productToRemove = null;
		for (Product p : productList) {
			if (p.getUID().equals(uid)) {
				productToRemove = p;
				break;
			}
		}
		if (productToRemove != null) {
			productList.remove(productToRemove);
		}
	}

}
