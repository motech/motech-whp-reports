package org.motechproject.donotcall.repository;

import org.motechproject.donotcall.domain.DoNotCallEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoNotCallEntryRepository extends JpaRepository<DoNotCallEntry, Long> {

}
