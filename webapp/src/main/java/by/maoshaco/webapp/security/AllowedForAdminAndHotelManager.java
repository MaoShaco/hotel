package by.maoshaco.webapp.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(AllowedForAdminAndHotelManager.condition)
public @interface AllowedForAdminAndHotelManager {
    String condition = "hasAnyRole('ADMIN', 'HOTEL_MANAGER')";
}
