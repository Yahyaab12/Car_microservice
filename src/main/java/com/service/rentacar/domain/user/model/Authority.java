package com.service.rentacar.domain.user.model;

public enum Authority
{
    VIEW_CAR_LOCATION("VIEW_CAR_LOCATION"),
    VIEW_ADMIN_PANEL("VIEW_ADMIN_PANEL"),
    ADD_VEHICLE("ADD_VEHICLE"),
    REMOVE_VEHICLE("REMOVE_VEHICLE"),
    GET_ALL_RESERVATIONS("GET_ALL_RESERVATIONS");

    private final String authority;

    Authority(String authority)
    {
        this.authority = authority;
    }

    public String getAuthority()
    {
        return authority;
    }
}
