package com.prueba.wom.dto.request;

import java.util.List;

public class UpdateTaskStatusRequest {
    private List<Integer> taskIds;
    private Integer status;

    public UpdateTaskStatusRequest() {
    }

    public UpdateTaskStatusRequest(List<Integer> taskIds, Integer status) {
        this.taskIds = taskIds;
        this.status = status;
    }

    public List<Integer> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(List<Integer> taskIds) {
        this.taskIds = taskIds;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
