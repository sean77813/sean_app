package com.sean.model;

import lombok.ToString;

@ToString
public class RoleMenuTree {
    private String id;

    private String rid;

    private String mid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid == null ? null : mid.trim();
    }

    public RoleMenuTree() {
    }

    public RoleMenuTree(String id, String rid, String mid) {
        this.id = id;
        this.rid = rid;
        this.mid = mid;
    }
}