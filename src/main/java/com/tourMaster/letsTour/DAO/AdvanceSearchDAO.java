package com.tourMaster.letsTour.DAO;

import com.tourMaster.letsTour.modals.PackageOffer;
import com.tourMaster.letsTour.modals.TourDestination;

import java.util.List;

public interface AdvanceSearchDAO {
    List<TourDestination> searchDestinationsByNameandDesc(String keyword);
    List<TourDestination> getDestinationsByDateAndPersons(String date,String persons);
    List<TourDestination> getDestinationsByNameDatePerson(String name, String date, String persons);

}
