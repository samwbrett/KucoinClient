package actions;

import client.KucoinClientV2;
import client.KucoinClientV2Response;
import exceptions.RequestException;
import schemas.objects.SymbolInfo;
import schemas.requests.PostOrderRequest;
import schemas.responses.PostOrderResponse;
import utils.NumberUtils;

import java.util.UUID;

/**
 * Places a limit order
 */
public class SimpleLimitOrder extends KucoinTradeAction<KucoinClientV2Response<PostOrderResponse>> {

    private final PostOrderRequest.Side side;
    private final double price;
    private final double size;
    private boolean postOnly;

    public SimpleLimitOrder(KucoinClientV2 client, String symbol, PostOrderRequest.Side side, double price, double size) {
        super(client, symbol);
        this.side = side;
        this.price = price;
        this.size = size;
        this.postOnly = false;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    @Override
    public KucoinClientV2Response<PostOrderResponse> attempt(SymbolInfo symbolInfo) {
        int pricePlaces = NumberUtils.getPrecision(symbolInfo.getPriceIncrement());
        int sizePlaces = NumberUtils.getPrecision(symbolInfo.getBaseIncrement());
        try {
            KucoinClientV2Response<PostOrderResponse> response = getClient().postOrder(new PostOrderRequest()
                    .withPrice(NumberUtils.toPrecision(price, pricePlaces))
                    .withSize(NumberUtils.toPrecision(size, sizePlaces))
                    .withClientOid(UUID.randomUUID().toString())
                    .withSide(side)
                    .withType(PostOrderRequest.Type.LIMIT)
                    .withSymbol(getSymbol())
                    .withPostOnly(postOnly));
            if (!response.isSuccess()) {
                addLiveInfo(response.getHttpResponse().body());
                return null;
            }
            return response;
        } catch (RequestException e) {
            addLiveInfo(e.getMessage());
            return null;
        }
    }
}
