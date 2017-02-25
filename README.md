Objects in the System

1. Address
    {
        "object_state": "VALID",
        "object_purpose": "PURCHASE",
        "object_source": "FULLY_ENTERED",
        "object_created": "2013-12-11T19:38:09.729Z",
        "object_updated": "2013-12-11T19:38:09.729Z",
        "object_id": "fcd9c72b564d4bfa8c03299ed6545132",
        "object_owner": "careemtle@gocareem.com",
        "name": "Shawn Ippotle",
        "company": "careem",
        "street1": "215 Clayton St.",
        "street2": "",
        "city": "San Francisco",
        "state": "CA",
        "zip": "94117",
        "country": "US",
        "phone": "+1 555 341 9393",
        "email": "careemtle@gocareem.com",
        "is_residential":true,
        "metadata": "Customer ID 123456",
        "test": true,
        "messages": []
    }
    Address objects are used for creating Shipments, obtaining Rates and printing Labels
    End Points:
    Create a new address
    POST /address/

    Retrieve an existing address by object id.
    GET /address/<address_object_id>

    Validate an existing address by object id.
    GET /address/<address_object_id>/validate

    List all addresses
    GET /addresses/


2. Parcel
    {
       "object_state": "VALID",
       "object_created": "2013-12-11T19:38:09.729Z",
       "object_updated": "2013-12-11T19:38:09.729Z",
       "object_id": "fcd9c72b564d4bfa8c03299ed6545132",
       "object_owner": "careemtle@gocareem.com",
       "template": null,
       "length": "20",
       "width": "15",
       "height": "12",
       "distance_unit": "in",
       "weight": "5",
       "mass_unit": "lb",
       "metadata": "Customer ID 123456",
       "extra": {},
       "test": true
    }
   Parcel objects are used for creating Shipments, obtaining Rates and printing Labels.
   Parcels are created with their basic dimensions and their weight.

   End Points:

   Create a new parcel
   POST /parcels/
   Retrieve an existing parcel by object id.
   GET /parcels/<PARCEL OBJECT ID>
   List all parcels
   GET /parcels/


3. Shipment
   A shipment is made up of "to" and "from" addresses and the parcel to be shipped.
   From shipment object we can retrieve shipping rates and purchase a shipping label.


4. Rate
   A rate is an available service of a shipping provider for a given shipment,
   including the price and transit time.

5. Transaction (shipping labels)
   A transaction is the purchase of a shipping label from a shipping provider for a specific service.

6. Customs Items
   Customs items are distinct items in your shipment parcel.









