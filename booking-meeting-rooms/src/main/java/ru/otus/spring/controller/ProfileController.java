package ru.otus.spring.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.dto.ProfileDto;
import ru.otus.spring.security.AuthUserDetails;
import ru.otus.spring.security.AuthUtils;
import ru.otus.spring.service.ProfileService;

import java.security.Principal;

/**
 * @author MTronina
 */
@Api(tags = "Сервис работы с профилем")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/profiles")
    public ResponseEntity<ProfileDto> getProfile(Principal principal) {
        AuthUserDetails userDetails = AuthUtils.getAuthUserDetails(principal);
        ProfileDto profile = profileService.getProfile(userDetails);
        return ResponseEntity.ok(profile);
    }

    @PostMapping("/profiles")
    public ResponseEntity<ProfileDto> createProfile(Principal principal) {
        AuthUserDetails userDetails = AuthUtils.getAuthUserDetails(principal);
        ProfileDto profile = profileService.createProfile(userDetails);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profiles")
    public ResponseEntity<Void> editProfile(
            @RequestBody ProfileDto updateRequest, Principal principal) {
        AuthUserDetails userDetails = AuthUtils.getAuthUserDetails(principal);
        profileService.updateProfile(updateRequest, userDetails);
        return ResponseEntity.ok().build();
    }

}
