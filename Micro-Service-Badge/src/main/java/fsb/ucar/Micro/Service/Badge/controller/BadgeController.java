package fsb.ucar.Micro.Service.Badge.controller;

import fsb.ucar.Micro.Service.Badge.dto.BadgeModel;
import fsb.ucar.Micro.Service.Badge.entity.Badge;
import fsb.ucar.Micro.Service.Badge.service.BadgeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/badges")
@AllArgsConstructor
public class BadgeController {


    private BadgeService badgeService;

    @GetMapping
    public ResponseEntity<List<Badge>> getAllBadges() {
        return badgeService.getAllBadge();
    }

    @GetMapping("/{badgeId}")
    public ResponseEntity<Badge> getBadgeById(@PathVariable long badgeId) {
        return badgeService.findBadgeById(badgeId);
    }

    @PostMapping
    public ResponseEntity<Long> addBadge(@RequestBody BadgeModel badgeModel) {
        return badgeService.AddBadge(badgeModel);
    }

    @PutMapping("/{badgeId}")
    public ResponseEntity<Badge> updateBadge(@PathVariable long badgeId, @RequestBody BadgeModel badgeModel) {
        return badgeService.updateBadge(badgeId, badgeModel);
    }

    @DeleteMapping("/{badgeId}")
    public ResponseEntity<Void> deleteBadge(@PathVariable long badgeId) {
        return badgeService.deleteBadge(badgeId);
    }
}