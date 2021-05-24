import client.KucoinClientV2;
import coins.CoinCurrency;
import exceptions.ParameterException;
import exceptions.RequestException;
import params.TradeParameters;
import responses.KucoinResponse;

import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {

        KucoinClientV2 client = new KucoinClientV2();
        try {
            System.out.println(client.getAccounts());

            Future<KucoinResponse> futureResponse = client.placeOrderUntilExists(3,
                    TradeParameters
                        .newBuilder(TradeParameters.Side.buy, CoinCurrency.USD, CoinCurrency.USD)
                        .withType(TradeParameters.Type.market)
                        .withSize(0.01)
                        .build()
            );
            Thread.sleep(5000);
        } catch (RequestException | ParameterException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
