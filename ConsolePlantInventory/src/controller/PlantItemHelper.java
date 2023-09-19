package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.PlantItem;

/**
 * @author jenni - jjarrell1
 * CIS175 -Fall 2023
 * Sept 19, 2023
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

		typedQuery.setParameter("selectedPlantType", toDelete.getPlantType());
		typedQuery.setParameter("selectedLocation", toDelete.getLocation());

		//we only want one result
		typedQuery.setMaxResults(1);

		PlantItem result = typedQuery.getSingleResult();

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
