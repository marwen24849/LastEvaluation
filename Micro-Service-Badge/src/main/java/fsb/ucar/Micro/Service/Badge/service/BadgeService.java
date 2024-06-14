package fsb.ucar.Micro.Service.Badge.service;

import fsb.ucar.Micro.Service.Badge.Repository.BadgeRepository;
import fsb.ucar.Micro.Service.Badge.dto.BadgeModel;
import fsb.ucar.Micro.Service.Badge.entity.Badge;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BadgeService {

    private BadgeRepository badgeRepository;

    public ResponseEntity<List<Badge>> getAllBadge(){
        return ResponseEntity.ok(this.badgeRepository.findAll());
    }

    public ResponseEntity<Badge> findBadgeById(long id){
        Optional<Badge> optionalBadge = this.badgeRepository.findById(id);
        return optionalBadge.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<Long> AddBadge(BadgeModel model){
        Badge badge = new Badge();
        badge.setDescription(model.getDescription());
        badge.setName(model.getName());
        badge.setType(model.getType());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.badgeRepository.save(badge).getId());
    }

    public ResponseEntity<Badge> updateBadge(long id, BadgeModel model) {
        Optional<Badge> optionalBadge = badgeRepository.findById(id);
        if (optionalBadge.isPresent()) {
            Badge badge = optionalBadge.get();
            badge.setDescription(model.getDescription());
            badge.setName(model.getName());
            badge.setType(model.getType());
            return ResponseEntity.ok(badgeRepository.save(badge));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<Void> deleteBadge(long id) {
        Optional<Badge> optionalBadge = badgeRepository.findById(id);
        if (optionalBadge.isPresent()) {
            badgeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
