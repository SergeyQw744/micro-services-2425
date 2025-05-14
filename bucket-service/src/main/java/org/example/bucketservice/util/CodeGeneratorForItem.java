package org.example.bucketservice.util;

import org.example.bucketservice.dao.UniqueCodeFindDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class CodeGeneratorForItem {
    private final String alphabet = "0123456789zxcvbnmasdfghjklqwertyuiop";

    private final UniqueCodeFindDao uniqueCodeFindDao;

    @Autowired
    public CodeGeneratorForItem(UniqueCodeFindDao uniqueCodeFindDao) {
        this.uniqueCodeFindDao = uniqueCodeFindDao;
    }

    public String generate(){
        String[] chars = alphabet.split("");
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 8; i++){
            code = code + chars[random.nextInt(chars.length)];
        }
        if (uniqueCodeFindDao.findByCode(code)){
            generate();
        }
        return code;
    }
}
