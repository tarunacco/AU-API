package com.accolite.au.dto;

import com.accolite.au.models.Batch;
import com.accolite.au.models.Trainer;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class GroupDTO {
    private int groupId;
    private BatchDTO batchDTO;

    @NotNull(message = "Group Name Should Be Provided")
    private String groupName;

    private TrainerDTO trainerDTO;

    @CreationTimestamp
    private Timestamp createdOn;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public BatchDTO getBatchDTO() {
        return batchDTO;
    }

    public void setBatchDTO(BatchDTO batchDTO) {
        this.batchDTO = batchDTO;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public TrainerDTO getTrainerDTO() {
        return trainerDTO;
    }

    public void setTrainerDTO(TrainerDTO trainerDTO) {
        this.trainerDTO = trainerDTO;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}
