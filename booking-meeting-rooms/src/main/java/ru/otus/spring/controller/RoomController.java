package ru.otus.spring.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.dto.RoomFilter;
import ru.otus.spring.dto.RoomRequestDto;
import ru.otus.spring.dto.RoomResponseDto;
import ru.otus.spring.service.RoomService;

import java.util.List;

/**
 * @author MTronina
 */
@Api(tags = "Сервис работы с переговорками")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/rooms")
    public ResponseEntity<Void> createRoom(
            @RequestBody RoomRequestDto room) {
        roomService.createRoom(room);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/rooms/{roomId}")
    public ResponseEntity<Void> editRoom(
            @PathVariable Long roomId,
            @RequestBody RoomRequestDto room) {
        roomService.updateRoom(roomId, room);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/rooms/{roomId}")
    public ResponseEntity<List<RoomResponseDto>> deleteRoom(
            @PathVariable Long roomId) {
        List<RoomResponseDto> rooms = roomService.deleteRoom(roomId);
        return ResponseEntity.ok(rooms);
    }

    @PostMapping("/rooms/search")
    public ResponseEntity<List<RoomResponseDto>> getRooms(
            @RequestBody RoomFilter roomFilter) {
        List<RoomResponseDto> rooms = roomService.getRooms(roomFilter);
        return ResponseEntity.ok(rooms);
    }
}
