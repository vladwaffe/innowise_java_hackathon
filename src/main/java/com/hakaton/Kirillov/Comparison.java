package com.hakaton.Kirillov;

import com.hakaton.Kirillov.config.BotConfig;
import com.hakaton.Kirillov.models.Cript;
import com.hakaton.Kirillov.models.CriptServise;
import com.hakaton.Kirillov.models.Users;
import com.hakaton.Kirillov.models.UsersServise;
import com.hakaton.Kirillov.telegram.TelegramBot;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class Comparison {

    @SneakyThrows
    public static String[] comparisonCost(){
        List<Cript> oldCost = CriptServise.getCriptList();
        ParseCourseToBd.parse(readSite.read());
        List<Cript> newCost = CriptServise.getCriptList();
        TelegramBot Bot = new TelegramBot();

        for(int i = 0; i < oldCost.size()-1; i++){
            StringBuilder massage = new StringBuilder();
            double b = (newCost.get(i).getCost() - oldCost.get(i).getCost())/(oldCost.get(i).getCost()/100);
            if(b>15){
                massage.append("cost up" + newCost.get(i).getName() + "  " + newCost.get(i).getCost());
                Bot.writeOfChangeCost(String.valueOf(massage), 15);
            } else if (b>10) {
                massage.append("cost up" + newCost.get(i).getName() + "  " + newCost.get(i).getCost());
                Bot.writeOfChangeCost(String.valueOf(massage), 10);
            } else if (b>5) {
                massage.append("cost up" + newCost.get(i).getName() + "  " + newCost.get(i).getCost());
                Bot.writeOfChangeCost(String.valueOf(massage), 5);
            } else if (b<5) {
                massage.append("cost down" + newCost.get(i).getName() + "  " + newCost.get(i).getCost());
                Bot.writeOfChangeCost(String.valueOf(massage), 5);
            } else if (b<10) {
                massage.append("cost down" + newCost.get(i).getName() + "  " + newCost.get(i).getCost());
                Bot.writeOfChangeCost(String.valueOf(massage), 10);
            } else if (b<15) {
                massage.append("cost down" + newCost.get(i).getName() + "  " + newCost.get(i).getCost());
                Bot.writeOfChangeCost(String.valueOf(massage), 5);
            }


        }





        return null;
    }
}
