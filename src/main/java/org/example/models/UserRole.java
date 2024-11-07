package org.example.models;

import java.util.Map;

public class UserRole {
    private static final String USER = "User";
    private static final String HR = "HR";
    private static final String SALES = "Sales";
    private static final String MANAGEMENT = "Management";
    private int roleId;

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
        if (!rolesMap.containsKey(roleId)) {
            return "Guest";
        }
        return rolesMap.get(getRoleId());
    }
}
