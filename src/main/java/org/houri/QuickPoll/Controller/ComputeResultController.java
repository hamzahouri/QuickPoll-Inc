package org.houri.QuickPoll.Controller;

import org.houri.QuickPoll.domain.Vote;
import org.houri.QuickPoll.dto.OptionCount;
import org.houri.QuickPoll.dto.VoteResult;
import org.houri.QuickPoll.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

public class ComputeResultController {

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping("/computersult")
    public ResponseEntity<?> computeResult(@RequestParam Long pollId){

        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);

        // Algorithm to count votes
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
        for(Vote v : allVotes) {
            totalVotes ++;
       // Get the OptionCount corresponding to this Option
            OptionCount optionCount = tempMap.get(v.getOptions().getId());
            if(optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOptions().getId());
                tempMap.put(v.getOptions().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount()+1);
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());



        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);

    }
}
