package com.tourMaster.letsTour.DAO;

import com.tourMaster.letsTour.modals.PackageOffer;
import com.tourMaster.letsTour.modals.TourDestination;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AdvanceSearchDAOImpl  implements AdvanceSearchDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TourDestination> getDestinationsByNameDatePerson(String name, String date, String persons) {

        List<TourDestination> filteredByName=!(name.trim().isEmpty())?searchDestinationsByNameandDesc(name):new ArrayList<>();
        List<TourDestination>filteredByDatePerson=!(date.trim().isEmpty())?getDestinationsByDateAndPersons(date,persons):new ArrayList<>();
        List<TourDestination> finalListTourDestinations;
        if (!(filteredByName.isEmpty())&& !(filteredByDatePerson.isEmpty())){
            HashSet<String> setOfDatePersonDestinations =new HashSet<>(filteredByDatePerson.stream().map(tourDestination -> {return tourDestination.getId();}).toList());
            finalListTourDestinations=filteredByName.stream().filter(element->setOfDatePersonDestinations.contains(element.getId())).collect(Collectors.toList());
        }else if(filteredByName.isEmpty())
        {
            finalListTourDestinations=filteredByDatePerson;
        }else if(filteredByDatePerson.isEmpty())
        {
            finalListTourDestinations=filteredByName;
        }
        else {
            finalListTourDestinations=new ArrayList<>();
        }
        return finalListTourDestinations;
    }

    @Override
    public List<TourDestination> searchDestinationsByNameandDesc(String keyword) {
        List<TourDestination> tourDestinations= new ArrayList<>();
        String sqlQuery="SELECT * " +
                        "FROM tour_destinations "+
                        "where Match(name,description) AGAINST (?1 IN NATURAL LANGUAGE MODE )";
        Query query =this.entityManager.createNativeQuery(sqlQuery);
        query.setParameter(1,keyword);
        List<Object []> resultList= query.getResultList();
        tourDestinations= resultList.stream().map(row-> new TourDestination(String.valueOf(row[0]),(String)row[1],(String)row[2],(String)row[3])).toList();
        return tourDestinations;

    }


    @Override
    public List<TourDestination> getDestinationsByDateAndPersons(String date, String persons) {
        TypedQuery<PackageOffer> typedQuery= this.entityManager.createQuery(" select po from PackageOffer as po where (FUNCTION('STR_TO_DATE', :dateParam, '%Y-%m-%d') >= function('str_to_date',po.packageSchedule.startDate,'%Y-%m-%d')) and (FUNCTION('STR_TO_DATE', :dateParam, '%Y-%m-%d')<=function('str_to_date',po.packageSchedule.endDate,'%Y-%m-%d'))", PackageOffer.class);
        typedQuery.setParameter("dateParam",date);
        List<PackageOffer> packageOffers =typedQuery.getResultList();
        List<TourDestination> tourDestinations= new ArrayList<>();

        if (!persons.trim().isEmpty())
        {
            List<PackageOffer> filteredByNoOfPersons= packageOffers.stream().filter(packageOffer -> (packageOffer.getTourPackage().getNoOfPersons().equals(Integer.parseInt(persons)))).toList();
            tourDestinations=filteredByNoOfPersons.stream().map(packageOffer -> {return packageOffer.getTourPackage().getTourDestination();}).toList();
        }else {
            tourDestinations=packageOffers.stream().map(packageOffer -> {return packageOffer.getTourPackage().getTourDestination();}).toList();
        }

        return tourDestinations;
    }
}
