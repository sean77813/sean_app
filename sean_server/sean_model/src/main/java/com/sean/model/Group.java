package com.sean.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Group implements Serializable {

    private Integer id;

    private String gid;

    private String pid;

    private String gname;

}
