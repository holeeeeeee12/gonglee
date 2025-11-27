package com.example.gonglee.controller;

import com.example.gonglee.model.Candidate;
import com.example.gonglee.repository.CandidateStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/worldcup")
@CrossOrigin(origins = "*")
public class WorldCupController {

    private final CandidateStore candidateStore;
    private final Random random = new Random();

    public WorldCupController(CandidateStore candidateStore) {
        this.candidateStore = candidateStore;
    }

    @GetMapping("/candidates")
    public List<Candidate> getCandidates(
            @RequestParam(name = "tour") String tournament,
            @RequestParam(name = "cat") String category
    ) {
        List<Candidate> results = candidateStore.findByTournamentAndCategory(tournament, category);
        System.out.println("🔍 조회: tour=" + tournament + ", cat=" + category + ", 결과=" + results.size() + "명");
        return results;
    }

    @GetMapping("/random")
    public RandomPairResponse getRandomPair(
            @RequestParam(name = "tour") String tournament,
            @RequestParam(name = "cat") String category
    ) {
        List<Candidate> candidates = candidateStore.findByTournamentAndCategory(tournament, category);
        
        if (candidates.size() < 2) {
            throw new IllegalArgumentException("최소 2명 이상이어야 합니다");
        }

        int idx1 = random.nextInt(candidates.size());
        int idx2 = random.nextInt(candidates.size());
        
        while (idx1 == idx2) {
            idx2 = random.nextInt(candidates.size());
        }

        Candidate candidate1 = candidates.get(idx1);
        Candidate candidate2 = candidates.get(idx2);

        return new RandomPairResponse(candidate1, candidate2);
    }

    @GetMapping("/start")
    public List<Candidate> startTournament(
            @RequestParam(name = "tour") String tournament,
            @RequestParam(name = "cat") String category
    ) {
        List<Candidate> candidates = candidateStore.findByTournamentAndCategory(tournament, category);
        java.util.Collections.shuffle(candidates);
        System.out.println("🏆 토너먼트 시작: " + tournament + " " + category + " (" + candidates.size() + "명)");
        return candidates;
    }

    public static class RandomPairResponse {
        public Candidate candidate1;
        public Candidate candidate2;

        public RandomPairResponse(Candidate candidate1, Candidate candidate2) {
            this.candidate1 = candidate1;
            this.candidate2 = candidate2;
        }

        public Candidate getCandidate1() { return candidate1; }
        public Candidate getCandidate2() { return candidate2; }
    }
}
