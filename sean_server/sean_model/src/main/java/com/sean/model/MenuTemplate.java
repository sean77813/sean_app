package com.sean.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
public class MenuTemplate implements Serializable {
    private String id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconcls;

    private Boolean keepalive;

    private Boolean requireauth;

    private String pid;

    private Boolean enabled;

    private String label;


}
