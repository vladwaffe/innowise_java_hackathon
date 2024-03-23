package com.hakaton.Kirillov;

import com.hakaton.Kirillov.models.Cript;
import com.hakaton.Kirillov.models.CriptServise;

import java.util.List;

public class ParseCourseToBd {
    public static void parse(List<String[]> splitStringList){
        HibernateUtils.startSession();
        for(int i = 0; i < 100; i=i+2){
            CriptServise.addCriptNoOpenSession(new Cript(splitStringList.get(i)[3], Float.parseFloat(splitStringList.get(i+1)[3])));
        }
        HibernateUtils.closeSession();
    }
}
