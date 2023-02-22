package org.houri.QuickPoll.repository;

import org.houri.QuickPoll.domain.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote,Long> {

@Query(value = "select v.* from Option , Vote v where o.POLL_ID = ?1 and v.OPTION_ID = o.OPTION_ID",
       nativeQuery = true)
    public Iterable<Vote> findByPoll(Long pollId);
}
