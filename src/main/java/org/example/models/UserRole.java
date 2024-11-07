package org.example.models;

import java.util.Map;

public class UserRole {
    public static final String USER = "User";
    public static final String HR = "HR";
    public static final String SALES = "Sales";
    public static final String MANAGEMENT = "Management";
    int roleId;

    private static final Map<Integer, String> rolesMap = Map.of(
            0, USER,
            1, HR,
            2, SALES,
            3, MANAGEMENT
    );

    public UserRole(final int roleId) {
        setRoleId(roleId);
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(final int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return rolesMap.get(getRoleId());
    }
}
