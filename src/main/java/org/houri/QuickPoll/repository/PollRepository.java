package org.houri.QuickPoll.repository;

import org.houri.QuickPoll.domain.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends CrudRepository<Poll,Long> {
}
