package org.houri.QuickPoll.repository;

import org.houri.QuickPoll.domain.Poll;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PollRepository extends PagingAndSortingRepository<Poll,Long> {
}
