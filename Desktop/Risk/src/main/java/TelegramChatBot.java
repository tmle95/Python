import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramChatBot extends TelegramLongPollingBot {

    private String message;
    private long chatID = 0;

    @Override
    public void onUpdateReceived(Update update) {
        //check if there is a message for update and that there is text
        if (update.hasMessage() && update.getMessage().hasText()) {
            message = update.getMessage().getText();
        }
        chatID = update.getMessage().getChatId();
    }

    @Override
    public String getBotUsername() {
        return "CTCRiskBot";
    }

    @Override
    public String getBotToken() {
        return "662182844:AAHNWWstFfr8DXCL_pN4nsdLCEkrlrAc5vM";
    }

    public String getMessage() {
        return message;
    }

    public void sendMessage(String messageToSend) {
        while (chatID == 0)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        SendMessage message = new SendMessage() // Create a SendMessage object
                .setChatId(chatID)
                .setText(messageToSend);
        try {
            execute(message); // method to send message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void clearMessage() {
        message = null;
    }
}

