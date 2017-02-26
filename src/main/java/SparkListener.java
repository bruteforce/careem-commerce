import com.careem.RequestParser;
import com.careem.model.Address;
import com.careem.model.Parcel;
import com.careem.model.Shipment;
import com.careem.model.Transaction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


import java.util.HashMap;
import java.util.Map;

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
    }
}