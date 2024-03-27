package com.hakaton.Kirillov.telegram;

import com.hakaton.Kirillov.HibernateUtils;
import com.hakaton.Kirillov.config.BotConfig;
import com.hakaton.Kirillov.models.Cript;
import com.hakaton.Kirillov.models.CriptServise;
import com.hakaton.Kirillov.models.Users;
import com.hakaton.Kirillov.models.UsersServise;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.List;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String currency = "";

        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText){
                case "/start":
                    try {
                        startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "/loadAll":
                    try {
                        allCourseOfCripta(chatId);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "/prosent":
                    /*try {

                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }*/
                    break;
                default:
                    try {
                        sendMessage(chatId, currency);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
            }
        }

    }

    private void startCommandReceived(Long chatId, String name) throws TelegramApiException {
        String answer = "Hi, " + name + ", nice to meet you!" + "\n" +
                "Enter the currency whose official exchange rate" + "\n" +
                "you want to know in relation to BYN." + "\n" +
                "/loadAll to show all Cript";
        sendMessage(chatId, answer);
        UsersServise.addUser(new Users(name, 5, chatId));
    }
    private void allCourseOfCripta(Long chatId) throws TelegramApiException {
        System.out.println("start");
        List<Cript> criptList = CriptServise.getCriptList();
        System.out.println("end read");
        StringBuilder answer = new StringBuilder();
        for(Cript element : criptList){
            answer.append(element.getName() + "   " + element.getCost() + "\n");
            System.out.println(answer);
        }
        sendMessage(chatId, String.valueOf(answer));
    }
    public void writeOfChangeCost(String massage, int prosent) throws TelegramApiException {
        List<Users> users = UsersServise.getUserByProsent(prosent);

        for(Users user : users){
            this.sendMessage(user.getChat_id(), massage);
        }

    }
    public void sendMessage(Long chatId, String textToSend) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        execute(sendMessage);
    }
}