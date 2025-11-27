package com.example.gonglee.repository;

import com.example.gonglee.model.Candidate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidateStore {

    private final List<Candidate> candidates;

    public CandidateStore(ObjectMapper objectMapper) throws Exception {
        ClassPathResource resource = new ClassPathResource("data/ideal_worldcup.json");
        try (InputStream is = resource.getInputStream()) {
            Candidate[] arr = objectMapper.readValue(is, Candidate[].class);
            this.candidates = Arrays.asList(arr);
            System.out.println("✅ 로드된 후보자: " + candidates.size() + "명");
        }
    }

    public List<Candidate> getAll() {
        return new ArrayList<>(candidates);
    }

    public List<Candidate> findByTournamentAndCategory(String tournament, String category) {
        return candidates.stream()
                .filter(c -> c.getTournament().equalsIgnoreCase(tournament))
                .filter(c -> c.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public Candidate findById(String id) {
        return candidates.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Candidate> findByTournament(String tournament) {
        return candidates.stream()
                .filter(c -> c.getTournament().equalsIgnoreCase(tournament))
                .collect(Collectors.toList());
    }
}
