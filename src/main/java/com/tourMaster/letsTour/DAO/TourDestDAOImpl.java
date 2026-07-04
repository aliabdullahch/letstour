package com.tourMaster.letsTour.DAO;

import com.tourMaster.letsTour.DTOs.DestinationDescriptionDTO;
import com.tourMaster.letsTour.modals.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TourDestDAOImpl implements TourDestDAO{

    private EntityManager entityManager;

    @Autowired
    public TourDestDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<TourDestination> getAllTourDestinations() {
        String jpql ="SELECT t FROM TourDestination  t";
        TypedQuery<TourDestination> typedQuery= this.entityManager.createQuery("select t from TourDestination as t", TourDestination.class);
        List<TourDestination> spotList=typedQuery.getResultList();
        return spotList;

    }

    @Transactional
    @Override
    public void saveDestinationWithAgencies(TourDestination tourDestination) {
        this.entityManager.merge(tourDestination);

    }

    @Transactional
    @Override
    public void updateAgencyWithPackage(TravelAgency ta)
    {
        this.entityManager.merge(ta);

    }

    @Transactional
    @Override
    public TravelAgency getAgencyWithPackages(Integer id) {
        TypedQuery<TravelAgency> typedQuery=this.entityManager.createQuery("select t from TravelAgency as t left join fetch t.packages where t.id =:data",TravelAgency.class);
        typedQuery.setParameter("data",id);
        return typedQuery.getSingleResult();
    }

    @Transactional
    @Override
    public void removePackageFromAgency(Integer id) {
        TourPackage temp=this.entityManager.find(TourPackage.class,id);
        this.entityManager.remove(temp);
    }

    @Override
    public List<TourPackage> getPackagesByAgencyIdDestinationId(Integer agencyId, Integer destinationId) {
        TypedQuery<TourPackage> typedQuery=this.entityManager.createQuery("select tp from TourPackage tp  join fetch tp.travelAgency  join fetch tp.tourDestination where (tp.travelAgency.id=:data1 and tp.tourDestination.id=:data2)", TourPackage.class);
        typedQuery.setParameter("data1",agencyId);
        typedQuery.setParameter("data2",destinationId);
        List<TourPackage> packages=typedQuery.getResultList();
        return packages;
    }

    @Override
    public TourDestination getDestinationWithPackages(Integer destId) {
        TypedQuery<TourDestination> typedQuery =this.entityManager.createQuery("select td from TourDestination td left join fetch td.tourPackages where td.id=:data", TourDestination.class);
        typedQuery.setParameter("data",destId);
        return typedQuery.getSingleResult();

    }
    @Override
    public TourDestination getTourDestinationWithImages(Integer destId)
    {
        TypedQuery <TourDestination> typedQuery =this.entityManager.createQuery("select td from TourDestination td left join fetch td.destinationImages where td.id=:param1",TourDestination.class);
        typedQuery.setParameter("param1",destId.toString());
        return typedQuery.getSingleResult();

    }

    @Transactional
    @Override
    public void updateDestinationWithPackage(TourDestination td) {
        this.entityManager.merge(td);
    }
    @Transactional
    @Override
    public void updatePackage(TourPackage tp) {
        this.entityManager.merge(tp);
    }

    @Transactional
    @Override
    public TourPackage getPackageByDestination(Integer packageId, Integer destId) {
        TypedQuery<TourPackage> typedQuery= this.entityManager.createQuery(" select tp from TourPackage as tp left join fetch tp.tourDestination where (tp.id = :param1 and tp.tourDestination.id = :param2)", TourPackage.class);
        typedQuery.setParameter("param1",packageId);
        typedQuery.setParameter("param2",destId);
        List<TourPackage> tourPackages=typedQuery.getResultList();
        return tourPackages.get(0);
    }


    @Transactional
    @Override
    public TourDestination getDestinationWithImagesAgencies(Integer id) {
        TypedQuery<TourDestination> typedQuery =this.entityManager.createQuery("select td from TourDestination as td left join fetch td.destinationImages  where td.id=:param", TourDestination.class);
        typedQuery.setParameter("param",id);
        return typedQuery.getSingleResult();
    }

    @Transactional
    @Override
    public void saveUser(User myUser) {
        this.entityManager.persist(myUser);
    }


    @Override
    public TourDestination getDestinationWithAgencies(Integer Id) {
        TypedQuery<TourDestination> typedQuery=this.entityManager.createQuery(" select t from TourDestination as t left join fetch t.travelAgencies where t.id=:data",TourDestination.class);
   typedQuery.setParameter("data",Id);
   TourDestination temp=typedQuery.getSingleResult();
   return temp;
    }

    @Override
    public User getUserWithBookings(Integer userId)
    {
        TypedQuery<User> typedQuery=this.entityManager.createQuery("select u from User u left join fetch u.bookings where u.id=:data",User.class);
        typedQuery.setParameter("data",userId);
        User fetchedUser= typedQuery.getSingleResult();
        return fetchedUser;

    }

    @Override
    public TourPackage getPackageWithBookings(Integer packId)
    {
        TypedQuery<TourPackage> typedQuery= this.entityManager.createQuery("from TourPackage tp left join fetch tp.packageBookings where tp.id=:data",TourPackage.class);
        typedQuery.setParameter("data",packId);
        TourPackage tp=typedQuery.getSingleResult();
        return tp;
    }

    @Transactional
    @Override
    public void updateUserWithBookings(User u) {
        this.entityManager.merge(u);
    }

    @Transactional
    @Override
    public void updatePackageWithbokings(TourPackage tp) {
        this.entityManager.merge(tp);

    }


    @Transactional
    @Override
    public Integer isUserExists(String email) {
        System.out.println(email);
        TypedQuery<User> typedQuery =this.entityManager.createQuery("select u from User as u where u.email=:data",User.class);
        System.out.println("The email which is in the parameter is "+email);
        typedQuery.setParameter("data",email);
       List<User>  tempUsers= typedQuery.getResultList();
       return tempUsers.toArray().length==0?-1:tempUsers.get(0).getId();

    }

    @Override
    public User getUserByEmail(String email) {
        System.out.println(email);
        TypedQuery<User> typeQuery =this.entityManager.createQuery("select u from User as u where u.email=:data",User.class);
        typeQuery.setParameter("data",email);
        List<User> tempUsers = typeQuery.getResultList();
        return tempUsers.get(0);
    }

    @Override
    public List<TourDestination> advanceSearchDestinationsByNameandDesc(String name, String desc) {
        return List.of();
    }

    @Override
    public List<String> getAllPossibleAreaTypes() {
        String sqlQuery="SELECT DISTINCT (area_type) from tour_destinations";
        Query queryObj =this.entityManager.createNativeQuery(sqlQuery);
        List<String> areaTypes=queryObj.getResultList();
        return areaTypes;
    }

    @Override
    public void updateImagePath(String previousName, String newName) {
        String sqlQueryString="UPDATE tour_destinations set image_path =:param1  where image_path=:param2";
        Query sqlQuery= this.entityManager.createNativeQuery(sqlQueryString);
        sqlQuery.setParameter("param1",newName);
        sqlQuery.setParameter("param2",previousName);
        sqlQuery.executeUpdate();
    }

    @Override
    public User getUserWithBookingById(Integer id)
    {
        TypedQuery<User> typedQuery=this.entityManager.createQuery("select u from User as u left join fetch u.myBookings where u.id= :param1 ",User.class);
        typedQuery.setParameter("param1",id);
        return typedQuery.getSingleResult();
    }

    @Override
    public Guest getGuestByEmail(String email) {
       TypedQuery<Guest> typedQuery=this.entityManager.createQuery("select g from Guest as g where g.email= :param1",Guest.class);
       typedQuery.setParameter("param1",email);
       List<Guest> allGuests = typedQuery.getResultList();
       return allGuests.isEmpty()?null:allGuests.get(0);

    }

    @Override
    public DestinationDescriptionDTO getdetailedDescription(String destId) {
        TypedQuery<DestinationDescriptionDTO> typedQuery=this.entityManager.createQuery("select new com.tourMaster.letsTour.DTOs.DestinationDescriptionDTO(destdesc.id,destdesc.description) from DestinationDescription as destdesc where destdesc.tourDestination.id = :param", DestinationDescriptionDTO.class);
        typedQuery.setParameter("param",destId);
        List<DestinationDescriptionDTO> destDescriptions= typedQuery.getResultList();
        return destDescriptions.isEmpty()? new DestinationDescriptionDTO():destDescriptions.get(0);
    }

    @Override
    public List<TourDestination> getSideFilteredData(String filterType, String filterValue) {
        String sqlQueryString="SELECT * FROM tour_destinations where "+filterType+" =(?1) ";
        Query sqlQuery=this.entityManager.createNativeQuery(sqlQueryString);
        sqlQuery.setParameter(1,filterValue);
        List<Object []> filteredTourDestinations=sqlQuery.getResultList();
        return filteredTourDestinations.stream().map(element-> new TourDestination(String.valueOf(element[0]),(String)element[1],(String)element[2],(String)element[3],(String)element[4],(String)element[5],(String)element[6])).toList();
    }

    @Override
    public List<CategoryReview> getCategoryReviewsByDestinationId(Integer id) {
        TypedQuery<CategoryReview> categoryReviewTypedQuery =this.entityManager.createQuery("select cr from CategoryReview as cr where cr.reviewedOn.id=:param1",CategoryReview.class);
        categoryReviewTypedQuery.setParameter("param1",id);
        return categoryReviewTypedQuery.getResultList();
    }

    @Override
    public List<TourPackage> getTourPackagesByDestinationId(Integer Id) {
        TypedQuery<TourPackage> typedQuery=this.entityManager.createQuery("select tp from TourPackage as tp where tp.tourDestination.id=:param1", TourPackage.class);
        typedQuery.setParameter("param1",Id);
        return typedQuery.getResultList();
    }

    @Override
    public TourDestination getDestinationById(Integer id) {
        return this.entityManager.find(TourDestination.class,id);
    }

    @Override
    public TravelAgency getTravelAgencyById(Integer Id) {
        return this.entityManager.find(TravelAgency.class,Id);
    }

    @Override
    public List<Review> getReviewsByDestinationId(Integer id) {
      TypedQuery<Review> reviewTypedQuery =this.entityManager.createQuery("select r from Review as r  left join fetch r.reviewType where r.reviewedOn.id=:param1",Review.class);
      reviewTypedQuery.setParameter("param1",id);
      List<Review>reviewsList=reviewTypedQuery.getResultList();
      return reviewsList;
    }

    @Override
    public Guest createNewGuest(Guest guest) {
        this.entityManager.persist(guest);
        return guest;
    }


    @Override
    public Integer createBooking(Booking booking) {
        this.entityManager.persist(booking);
        return (booking.getId());
    }
}
