package com.sean.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
public class Role implements Serializable {

    private String id;

    private String rid;

    private String rname;

}
