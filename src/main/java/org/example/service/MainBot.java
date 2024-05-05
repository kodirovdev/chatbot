package org.example.service;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.ReplyParameters;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public class MainBot extends TelegramLongPollingBot {
//    Bot Username
    @Override
    public String getBotUsername() {
//        Bu yerga bot username kiriting!
        return "";
    }
// Bot token
    @Override
    public String getBotToken() {
//        Bu yerga bot token kiriting!
        return "";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
//      Bu yerga admin id kiriting!
        String adminId = "";
        if (update.hasMessage()){
            Message message = update.getMessage();
            if (message.hasText()){
                String text = message.getText();
                if (text.equals("/start")){
                    SendMessage sendMessage = new SendMessage(message.getChatId().toString(), "Assalomu alaykum. \nHabaringizni yuboring!");
                    execute(sendMessage);
                }else {
                    if (!message.getFrom().getId().toString().equals(adminId)){
                        ForwardMessage forwardMessage = new ForwardMessage();
                        forwardMessage.setChatId(adminId);
                        forwardMessage.setMessageId(message.getMessageId());
                        forwardMessage.setFromChatId(message.getFrom().getId());
                        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), "Habaringiz yuborildi✅. \n" +
                                "Iltimos javobini kuting!");
                        execute(sendMessage);
                       execute(forwardMessage);
                    }else {
                        User forwardFrom = message.getReplyToMessage().getForwardFrom();
                        SendMessage sendMessage = new SendMessage(forwardFrom.getId().toString(), text);
                        SendMessage sendMessage1 = new SendMessage(adminId, "Habar foydalanuvchiga yuborildi!");
                        execute(sendMessage1);
                        execute(sendMessage);
                    }
                }

            }
             else {
                SendMessage sendMessage = new SendMessage(message.getChatId().toString(), "Faqat habar yuborishingiz mumkin!");
                execute(sendMessage);
            }
        }

    }




}
