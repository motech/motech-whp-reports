package org.motechproject.donotcall.repository;

import org.motechproject.donotcall.domain.DoNotCallEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoNotCallEntryRepository extends JpaRepository<DoNotCallEntry, Long> {
    List<DoNotCallEntry> findByEntityId(String entityId);
}
