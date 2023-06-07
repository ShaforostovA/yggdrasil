package com.ntgspiyggdrasil.yggdrasil.services;

import com.ntgspiyggdrasil.yggdrasil.models.KeyWord;
import com.ntgspiyggdrasil.yggdrasil.models.User;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DocumentShortModel;
import com.ntgspiyggdrasil.yggdrasil.payload.response.KeyWordModel;
import com.ntgspiyggdrasil.yggdrasil.payload.response.KeyWordResponse;
import com.ntgspiyggdrasil.yggdrasil.repository.DocumentRepository;
import com.ntgspiyggdrasil.yggdrasil.repository.KeyWordRepository;
import com.ntgspiyggdrasil.yggdrasil.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KeyWordService {
    @Autowired
    private KeyWordRepository keyWordRepository;
    @Autowired
    private DocumentRepository documentRepository;

    public List<KeyWordResponse> getDocumentKeyWords(long documentId) {
        return keyWordRepository.findByDocumentId(documentId).stream().map(keyWord -> new KeyWordResponse(keyWord.getId(), keyWord.getName())).collect(Collectors.toList());
    }

    public Iterable<KeyWordResponse> getKeyWords() {
        ArrayList<KeyWordResponse> keyWordResponse = new ArrayList<KeyWordResponse>(keyWordRepository.findAll().size());
        for (KeyWord item : keyWordRepository.findAll()) {
            keyWordResponse.add(new KeyWordResponse(item.getId(), item.getName()));
        }
        Iterable<KeyWordResponse> keyWords = keyWordResponse;

        return keyWords;
    }
    public Iterable<KeyWordResponse> addKeyWords(long documentId, User user, Set<String> keyWords) {
        Set<KeyWord> keyWordAttached = keyWordRepository.findByDocumentId(documentId);

            if (!keyWordAttached.isEmpty()) {
                keyWordAttached.forEach(keyWordAttach -> {
                    if(!keyWords.contains(keyWordAttach.getName())) {
                        keyWordRepository.deleteAttachDocumentKeyWord(documentId, keyWordAttach.getId());
                    }
                });
            }
            if (!keyWords.isEmpty()) {
                keyWordAttached = keyWordRepository.findByDocumentId(documentId);

                if (keyWordAttached.size() != keyWords.size()) {
                    Set<KeyWord> finalKeyWordAttached = keyWordAttached;
                    keyWords.forEach(newKeyWord -> {
                        KeyWord findKW = finalKeyWordAttached.stream().filter(findKeyWord -> findKeyWord.getName().equals(newKeyWord)).findFirst().orElse(null);
                        if(findKW == null) {
                            if (!keyWordRepository.existsByName(newKeyWord)) {
                                KeyWord createKeyWord = keyWordRepository.save(new KeyWord(newKeyWord, user, new Date()));
                                keyWordRepository.attachDocumentKeyWord(documentId, createKeyWord.getId());
                            } else {
                                KeyWord findKeyWord = keyWordRepository.findByName(newKeyWord).orElseThrow();
                                keyWordRepository.attachDocumentKeyWord(documentId, findKeyWord.getId());
                            }
                        }
                    });
                }
            }

        return keyWordRepository.findByDocumentId(documentId).stream().map(keyWord -> new KeyWordResponse(keyWord.getId(), keyWord.getName())).collect(Collectors.toList());
    }

    public KeyWord updateKeyWord(long keyWordId, String keyWordName) {
        KeyWord keyWord = keyWordRepository.findById(keyWordId).orElseThrow();
        keyWord.setName(keyWordName);
        return keyWordRepository.save(keyWord);
    }

    public void deleteKeyWord(long keyWordId) {
        keyWordRepository.deleteAttachDocumentKeyWord(keyWordId);
        keyWordRepository.deleteById(keyWordId);
    }
}
