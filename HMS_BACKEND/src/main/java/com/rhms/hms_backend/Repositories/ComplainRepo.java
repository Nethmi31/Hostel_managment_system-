package com.rhms.hms_backend.Repositories;

import com.rhms.hms_backend.Models.Complain;
import com.rhms.hms_backend.Models.Room_assignment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComplainRepo extends CrudRepository<Complain, Long> {


    @Query(nativeQuery = true, value = "SELECT * FROM daily_complaint_count")
    List<Object[]> findAllComplaint();


    @Query(nativeQuery = true, value = "SELECT * FROM ApprovedComplaints")
    List<Complain> ApprovedComplaint();

    @Query(nativeQuery = true, value = "SELECT * FROM warden_complain_view")
    List<Complain> ComplaintWardenView();

    @Query(nativeQuery = true, value = "SELECT * FROM dean_complain_view")
    List<Complain> ComplaintDeanView();

    @Query(nativeQuery = true, value = "SELECT * FROM complain WHERE  complainant  = :id")
    List<Complain> getComplainByuid(@Param("id") String id);


    @Query(value = "SELECT * FROM  complain WHERE complainant = ?1", nativeQuery = true)
    List<Complain> findComplain(String uid);


}
