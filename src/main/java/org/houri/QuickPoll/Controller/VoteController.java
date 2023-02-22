package org.houri.QuickPoll.Controller;

import org.houri.QuickPoll.domain.Vote;
import org.houri.QuickPoll.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;

    @PostMapping("/polls/{pollId}/votes")
    public ResponseEntity<?> createVote(@PathVariable Long pollId,
                                        @RequestBody Vote vote) {
        vote = voteRepository.save(vote);

        // Set The header for the newly created resources
        HttpHeaders responseHeader = new HttpHeaders();

        responseHeader.setLocation(
                ServletUriComponentsBuilder.
                        fromCurrentRequest().
                        path("/{id}").buildAndExpand(vote.getId()).toUri());
        return new ResponseEntity<>(null,responseHeader, HttpStatus.CREATED);
    }

    @GetMapping("/polls/{pollId}/votes")
    public Iterable<Vote> getAllVotes(@PathVariable Long pollId){
        return voteRepository.findByPoll(pollId);
    }
}
