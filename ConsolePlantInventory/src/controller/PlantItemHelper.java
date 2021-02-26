package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.PlantItem;

/**
 * @author jenni - jjarrell
 * CIS175 -Spring 2021
 * Feb 11, 2021
 */
public class PlantItemHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConsolePlantInventory");
	public void insertItem(PlantItem pi) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(pi);
		em.getTransaction().commit();
		em.close();

	}
	public List<PlantItem> showAllPlantItems(){
		EntityManager em = emfactory.createEntityManager();
			List<PlantItem> allItems = em.createQuery("SELECT i FROM PlantItem i").getResultList();
			return allItems;
	}
	public void deleteItem(PlantItem toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PlantItem> typedQuery = em.createQuery("select pi from PlantItem pi where pi.plantType = :selectedPlantType and pi.location = :selectedLocation", PlantItem.class);

//Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedPlantType", toDelete.getPlantType());
		typedQuery.setParameter("selectedLocation", toDelete.getLocation());

		//we only want one result
		typedQuery.setMaxResults(1);

		//get the result and save it into a new list item
		PlantItem result = typedQuery.getSingleResult();

		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
		
	}
	
	/**
	 * @param toEdit
	 */
	
	public void updateItem(PlantItem toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param idToEdit
	 * @return
	 */
	public PlantItem searchForItemById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		PlantItem found = em.find(PlantItem.class, idToEdit);
		em.close();
		return found;
	}

	/**
	 * @param plantTypeName
	 * @return
	 */
	public List<PlantItem> searchForItemByPlantType(String plantTypeName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PlantItem> typedQuery = em.createQuery("select pi from PlantItem pi where pi.plantType = :selectedPlantType", PlantItem.class);
		typedQuery.setParameter("selectedPlantType", plantTypeName);

		List<PlantItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	/**
	 * @param locationName
	 * @return
	 */
	public List<PlantItem> searchForItemByLocation(String locationName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PlantItem> typedQuery = em.createQuery("select pi from PlantItem pi where pi.location = :selectedLocation", PlantItem.class);
		typedQuery.setParameter("selectedLocation", locationName);

		List<PlantItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
}


	public void cleanUp(){
		emfactory.close();
	}

}
