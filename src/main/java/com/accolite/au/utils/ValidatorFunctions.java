package com.accolite.au.utils;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidatorFunctions {
    private static final String ORGANIZATION_DOMAIN = "accolitedigital.com";

    public boolean emailValidator(String email){
        //Regular Expression
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";

        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isWithinOrganization(String email){
        String splittedEmail[] = email.split("@");
        if(splittedEmail.length >= 2){
            if(splittedEmail[splittedEmail.length - 1].compareTo("accolitedigital.com") == 0){
                return true;
            }
        }
        return false;
    }

}
