package org.softwaremanager.backoffice.auth.repository;

import org.softwaremanager.backoffice.auth.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
