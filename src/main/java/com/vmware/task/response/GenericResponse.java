package com.vmware.task.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class GenericResponse implements Serializable {
    public String result;
}
