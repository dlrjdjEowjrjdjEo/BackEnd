package com.medic.controller;

import com.medic.dto.DeleteRoutineReq;
import com.medic.dto.RoutineReq;
import com.medic.entity.RoutineEntity;
import com.medic.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MypageController {

    private final MypageService mypageService;

    @Autowired
    public MypageController(MypageService mypageService) {
        this.mypageService = mypageService;
    }

    @GetMapping("/check")
    public String check() {
        return "Hello MyPage Controller";
    }

    /*@GetMapping("/{userId}")
    public ResponseEntity<?> myPage(@PathVariable int userId) {
        User user = myPageService.getUser(userId);
        List<Like> list = myPageService.getLikeList(userId);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("user", user);
        map.put("likeList", list);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }*/

    @GetMapping("/{userId}/mysupplement")
    public ResponseEntity<?> mySupplement(@PathVariable int userId) {
        List<RoutineEntity> list = mypageService.getRoutineList(userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/{userId}/mysupplement")
    public ResponseEntity<?> insertSupplement(@PathVariable int userId, @RequestBody RoutineReq routineReq) {
        RoutineEntity routineEntity = RoutineEntity.builder().userId(userId).supplementId(routineReq.getSupplementId())
                .time(routineReq.getTime()).day(routineReq.getDay()).tablets(routineReq.getTablets())
                .pushAlarm(routineReq.getPushAlarm()).addedSince(routineReq.getAddedSince()).build();
        mypageService.insertRoutine(routineEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{userId}/mysupplement/{routineId}")
    public ResponseEntity<?> updateRoutineVisibility(@PathVariable int routineId, @RequestBody DeleteRoutineReq deleteRoutineReq) {
        String deletedSince = deleteRoutineReq.getDeletedSince();
        mypageService.updateRoutineVisibility(routineId, deletedSince);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{userId}/mysupplement/{routineId}")
    public ResponseEntity<?> updateSupplement(@PathVariable int userId, @PathVariable int routineId,
                                              @RequestBody RoutineReq routineReq) {
        String deletedSince = routineReq.getAddedSince();
        RoutineEntity routineEntity = RoutineEntity.builder().userId(userId).supplementId(routineReq.getSupplementId())
                .time(routineReq.getTime()).day(routineReq.getDay()).tablets(routineReq.getTablets())
                .pushAlarm(routineReq.getPushAlarm()).addedSince(routineReq.getAddedSince()).build();
        mypageService.updateRoutine(routineId, routineEntity, deletedSince);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
