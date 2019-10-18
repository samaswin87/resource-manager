package com.resource.common.constants;

import java.util.Arrays;
import java.util.List;

public enum Status {

	SUBMITTED(1, "Submitted"), PENDING(2, "Pending"), APPROVED(3, "Approved"), REJECTED(4, "Rejected"), CANCEL_REQUESTED(5, "CancelRequested"), CANCELLED(6, "Cancelled"), EXPIRED(7, "Expired");
	
	private Integer id;
	private String status;
	
	Status(Integer id, String status) {
		this.id = id;
		this.status = status;
	}

	 public String getStatus() {
         return this.status;
     }
	 
     public Integer getId() {
         return this.id;
     }
     
     public List<Status> statuses(){
    	 return Arrays.asList(Status.values());
     }

}
