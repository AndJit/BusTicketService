package com.aj.bts.TicketManagementSystem.db.races;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacesRepository extends CrudRepository<Race, Integer> {

    Race findRaceById(int id);

}
