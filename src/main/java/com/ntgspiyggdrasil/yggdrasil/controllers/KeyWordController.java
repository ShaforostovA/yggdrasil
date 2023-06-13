package com.ntgspiyggdrasil.yggdrasil.controllers;

import com.ntgspiyggdrasil.yggdrasil.models.User;
import com.ntgspiyggdrasil.yggdrasil.payload.request.KeyWordsRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.UpdateKeyWord;
import com.ntgspiyggdrasil.yggdrasil.payload.response.KeyWordModel;
import com.ntgspiyggdrasil.yggdrasil.payload.response.KeyWordResponse;
import com.ntgspiyggdrasil.yggdrasil.payload.response.UserShortModel;
import com.ntgspiyggdrasil.yggdrasil.repository.UserRepository;
import com.ntgspiyggdrasil.yggdrasil.security.jwt.JwtUtils;
import com.ntgspiyggdrasil.yggdrasil.services.KeyWordService;
import com.ntgspiyggdrasil.yggdrasil.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/keywords")
@AllArgsConstructor
public class KeyWordController {
    @Autowired
    private KeyWordService keyWordService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public Iterable<KeyWordResponse> getDocumentStructures() {
        return keyWordService.getKeyWords();
    }
    @PostMapping("/add/{documentId}")
    public Iterable<KeyWordResponse> addKeyWords(@PathVariable("documentId") long documentId, @RequestHeader("Authorization") String authorization, @RequestBody KeyWordsRequest keyWords) {
        String headerAuth = authorization;
        String username = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7,headerAuth.length()));
        User user = userRepository.findByUsername(username).orElseThrow();
        return keyWordService.addKeyWords(documentId, user, keyWords.getKeyWords());
    }
    @GetMapping("/document/{documentId}")
    public List<KeyWordResponse> getDocumentKeyWords(@PathVariable("documentId") long documentId) {
        return keyWordService.getDocumentKeyWords(documentId);
    }

    @PostMapping("/update/{keyWordId}")
    public KeyWordModel updateKeyWord(@PathVariable("keyWordId") long keyWordId, @RequestBody UpdateKeyWord updateKeyWord) {
        return KeyWordModel.toModel(keyWordService.updateKeyWord(keyWordId, updateKeyWord.getKeyWordName()));
    }

    @DeleteMapping("/delete/{keyWordId}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity deleteKeyWord(@PathVariable("keyWordId") long keyWordId) {
        keyWordService.deleteKeyWord(keyWordId);
        return ResponseEntity.ok().build();
    }
}
