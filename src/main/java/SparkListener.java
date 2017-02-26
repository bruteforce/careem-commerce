import com.careem.RequestParser;
import com.careem.model.*;

import static spark.Spark.*;
public class SparkListener {

    public static void main(String[] args) {
        setupRoutes();
    }


    private static void setupRoutes() {
        get("/address/:object_id", (req, res) -> {
           return Address.retrieve(req.params(":object_id"));
        });

        post("/address", (req, res) -> {
            return Address.create(RequestParser.parseAddressFromRequest(req));
        });

        get("/parcel/:object_id", (req, res) -> {
            return Parcel.retrieve(req.params(":object_id"));
        });

        post("/parcel", (req, res) -> {
            return Parcel.create(RequestParser.parseParcelFromRequest(req));
        });

        post("/shipment", (req, res) -> {
            return Shipment.create(RequestParser.parseShipmentFromRequest(req));
        });

        get("/shipment/:object_id", (req, res) -> {
            return Shipment.retrieve(req.params(":object_id"));
        });

        post("/transaction", (req, res) -> {
            return Transaction.create(RequestParser.parseTransactionFromRequest(req));
        });

        post("/carrier", (req, res) -> {
            return CarrierAccount.create(RequestParser.parseCarrierFromRequest(req));
        });

        get("/carrier/:object_id", (req, res) -> {
            return CarrierAccount.create(RequestParser.parseCarrierFromRequest(req));
        });
    }
}