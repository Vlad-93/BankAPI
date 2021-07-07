package main.java.service;



import entities.Account;
import entities.Card;
import entities.Client;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static entities.Card.getCard;
import static entities.Card.getLastCardNumber;
import static entities.Client.getClient;
import static service.ObjToJsonConverter.*;

/**
 * Класс сервиса клиента
 */
public class UserService {

    /**
     * Пополняет счёт карты
     * @param sumString строка в формате json вида {"sum":00.00} или строка с суммой со вторым знаком после запятой, например "12.89"
     * @param cardId карты, которую надо пополнить
     * @param fromJson true, если строка на входе передается в json, иначе false
     * @return получившийся баланс карты в формате json
     */

    public String addMoneyToCard(String sumString, long cardId, boolean fromJson) {
        BigDecimal sum;
        if (fromJson)
            sum = new BigDecimal(covertJsonToMap(sumString).get("sum"));
        else
            sum = new BigDecimal(sumString);
        Card card = getCard(cardId);
        if (card == null)
            return null;
        else {
            BigDecimal prev = card.getBalance();
            card.setCardBalance(prev.add(sum));
            return convertCardBalanceToJson(Collections.singletonMap("balance", card.getBalance()));
        }
    }


    /**
     * Возвращает баланс счёта карты
     * @param cardId идентификатор карты
     * @return баланс карты в формате json
     */

    public String getBalance(long cardId) {
        Card card = getCard(cardId);
        if (card == null)
            return null;
        else {
            BigDecimal balance = card.getBalance().setScale(2, RoundingMode.DOWN);
            return convertCardBalanceToJson(Collections.singletonMap("balance", balance));
        }
    }

    /**
     * @param clientLogin Логин клиента
     * @return список карт клиента в формате json
     */

    public String getAllCards(String clientLogin) {
        Client client = getClient(clientLogin);
        List<Object> cardsList = new ArrayList<>();

        client.getAccounts().forEach(account -> cardsList.addAll(account.getCards()));
        return convertListToJsonString(cardsList);

    }

    /**
     * Созддает новую карту, привязанную к сч>ту
     * @param accountIdString идентификатор счёта в формате json вида {"id":0}
     * @return всю инф-ю по созданной карте в формате json
     */

    public String createNewCard(String accountIdString){
        long accountId = Long.parseLong(covertJsonToMap(accountIdString).get("id"));
        if (Account.ifExists(accountId)) {
            String lastCardNumber = getLastCardNumber();
            Long number = Long.parseLong(lastCardNumber);
            number++;
            String currentNum = String.format("%016d", number);
            Card card = new Card(0, currentNum);
            Card.addCard(card, accountId);
            return convertCardToJson(getCard(currentNum));
        }
        else return null;
   }}
