# Java Doc
[Code Doc](https://bruteforce.github.io/)

# High-Level Work Flow Diagram
(https://github.com/bruteforce/bruteforce.github.io/blob/master/high-level.png?raw=true)


#Objects of the system with End Points

##1. Address
    Address objects are used for creating Shipments, obtaining Rates and printing Labels

    ##End Points:


    ###Create a new address

    POST /address/

    ###Retrieve an existing address by object id.

    GET /address/<address_object_id>

    ###Validate an existing address by object id.

    GET /address/<address_object_id>/validate

    #List all addresses

    GET /addresses/


##2. Parcel
   Parcel objects are used for creating Shipments, obtaining Rates and printing Labels.
   Parcels are created with their basic dimensions and their weight.

   ##End Points:

   ### Create a new parcel

   POST /parcels/

   ###Retrieve an existing parcel by object id.

   GET /parcels/<PARCEL OBJECT ID>

   L###ist all parcels

   GET /parcels/


##3. Shipment

   A shipment is made up of "to" and "from" addresses and the parcel to be shipped.
   From shipment object we can retrieve shipping rates and purchase a shipping label.

   ##End Point -

   ###Creates a new shipment object.

   POST /shipments/

   ###Retrieve an existing shipment

   GET /shipments/<SHIPMENT OBJECT ID>

   ###List all shipment objects.

   GET /shipments/





##4. Rate

   A rate is an available service of a shipping provider for a given shipment,
   including the price and transit time.

   ##End Points

   ### Get shipping rates

   GET /shipments/<SHIPMENT OBJECT ID>/rates/[<CURRENCY CODE>/]

   ### Retrieve an existing rate by object id.

   GET /rates/<RATE OBJECT ID>



##5. Transaction (shipping labels)

    A transaction is the purchase of a shipping label from a shipping provider for a specific service.

    ##End Points:

    ### Create a transaction based on a rate object

    POST /transactions/

    ### Retrieve an existing transaction by object id.

    GET /transactions/<TRANSACTION OBJECT ID>





##6. Customs Items

   Customs items are distinct items in your shipment parcel.

   ## END POINTS

   ### Creates a new Customs Item object.

   POST /customs/items/

   ### Retrieve an existing Customs Item by object id.

   GET /customs/items/<CUSTOMS ITEM OBJECT ID>

   ### List all custom-items objects.

   GET /customs/items/

##7. Carrier Accounts

   Carrier accounts are used as credentials to retrieve shipping rates and
   purchase labels from a shipping provider.


   ##END POINTS

   ###Creates a new carrier account object.

   POST /carrier_accounts/

   ###Retrieve an existing carrier account by object id.

   GET /carrier_accounts/<CARRIER ACCOUNT OBJECT ID>

   ###List all carrier accounts

   GET /carrier_accounts/

   ###Update an existing carrier account

   /carrier_accounts/<CARRIER ACCOUNT OBJECT ID>

##8. Tracking Status

  ### Request the tracking status of a shipment by sending a GET request.

   GET /tracks/<CARRIER>/<TRACKING NUMBER>/

  ### Register a tracking webhook

   POST /tracks/









