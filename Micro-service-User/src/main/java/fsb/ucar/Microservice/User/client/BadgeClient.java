package fsb.ucar.Microservice.User.client;



import fsb.ucar.Microservice.User.dto.Badge;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "MICRO-SERVICE-BADGE")
public interface BadgeClient {

    @GetMapping("/badges")
    ResponseEntity<List<Badge>> getAllBadges();

    @GetMapping("/badges/{badgeId}")
    ResponseEntity<Badge> getBadgeById(@PathVariable("badgeId") long badgeId);

    @PostMapping("/badges")
    ResponseEntity<Long> addBadge(@RequestBody Badge Badge);

    @PutMapping("/badges/{badgeId}")
    ResponseEntity<Badge> updateBadge(@PathVariable("badgeId") long badgeId, @RequestBody Badge Badge);

    @DeleteMapping("/badges/{badgeId}")
    ResponseEntity<Void> deleteBadge(@PathVariable("badgeId") long badgeId);

}
