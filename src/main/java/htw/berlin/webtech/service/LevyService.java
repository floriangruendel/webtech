package htw.berlin.webtech.service;

import htw.berlin.webtech.persistence.LevyEntity;
import htw.berlin.webtech.persistence.LevyRepository;
import htw.berlin.webtech.web.api.Levy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LevyService {

    private final LevyRepository levyRepository;

    public LevyService(LevyRepository levyRepository) {
        this.levyRepository = levyRepository;
    }

    public List<Levy> findAll() {
        List<LevyEntity> levies = levyRepository.findAll();
        return levies.stream()
                .map(levyEntity -> new Levy(
                        levyEntity.getId(),
                        levyEntity.getTitle(),
                        levyEntity.getDiscription(),
                        levyEntity.getModul(),
                        levyEntity.getDeadline(),
                        levyEntity.getImportance()
                ))
                .collect(Collectors.toList());
    }
}
