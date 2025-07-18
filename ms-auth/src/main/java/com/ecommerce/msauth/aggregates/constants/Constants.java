package com.ecommerce.msauth.aggregates.constants;

public class Constants {
    public static final Boolean STATUS_ACTIVE = true;
    public static final String CLAVE_AccountNonExpired ="isAccountNonExpired";
    public static final String CLAVE_AccountNonLocked ="isAccountNonLocked";
    public static final String CLAVE_CredentialsNonExpired = "isCredentialsNonExpired";
    public static final String CLAVE_Enabled = "isEnabled";
    public static final String REFRESH = "refresh";
    public static final String ACCESS = "access";
    public static final String[] ENDPOINTS_PERMIT = {
            "/auth/**",
            "/actuator/**"
    };
    public static final String ENDPOINTS_USER = "/test/user";
    public static final String ENDPOINTS_ADMIN = "/test/admin";
    public static final String ENDPOINTS_SUPERADMIN = "/test/superadmin";
}
