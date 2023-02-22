package org.houri.QuickPoll.repository;

import org.houri.QuickPoll.domain.Option;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<Option,Long> {
}
