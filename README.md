#


#Objects of the system with End Points

##1. Address
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
        "street1": "361, Sunehri Bagh",
        "street2": "Sector 13, Rohini",
        "city": "Delhi",
        "state": "Delhi",
        "zip": "110085",
        "country": "India",
        "phone": "8884638679",
        "email": "careemtle@gocareem.com",
        "is_residential":true,
        "metadata": "Customer ID 123456",
        "test": true,
        "messages": []
    }
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

   ##End Points:

   ### Create a new parcel

   POST /parcels/

   ###Retrieve an existing parcel by object id.

   GET /parcels/<PARCEL OBJECT ID>

   L###ist all parcels

   GET /parcels/


##3. Shipment
   {
       "object_state": "VALID",
       "object_status": "SUCCESS",
       "object_purpose": "PURCHASE",
       "object_created": "2013-12-01T06:24:20.121Z",
       "object_updated": "2013-12-01T06:24:20.121Z",
       "object_id": "5e40ead7cffe4cc1ad45108696162e42",
       "object_owner": "careemtle@gocareem.com",
       "address_from": "4f406a13253945a8bc8deb0f8266b245",
       "address_to": "4c7185d353764d0985a6a7825aed8ffb",
       "address_return": "4f406a13253945a8bc8deb0f8266b245",
       "parcel": "ec952343dd4843c39b42aca620471fd5",
       "submission_date": "2013-12-03T12:00:00.000Z",
       "insurance_amount": "",
       "insurance_currency": "",
       "extra": {},
       "customs_declaration": "",
       "reference_1": "",
       "reference_2": "",
       "rates_url": "https://api.gocareem.com/shipments/5e40ead7cffe4cc1ad45108696162e42/rates/",
       "rates_list": [
           {
               "object_state": "VALID",
               "object_purpose": "PURCHASE",
               "object_created": "2013-12-01T06:24:21.121Z",
               "object_updated": "2013-12-01T06:24:21.121Z",
               "object_id": "545ab0a1a6ea4c9f9adb2512a57d6d8b",
               "object_owner": "careemtle@gocareem.com",
               "shipment": "5e40ead7cffe4cc1ad45108696162e42",
               "attributes": [],
               "amount": "5.50",
               "currency": "USD",
               "amount_local": "5.50",
               "currency_local": "USD",
               "provider": "USPS",
               "servicelevel_name": "Priority Mail",
               "servicelevel_token":"usps_priority",
               "servicelevel_terms": "",
               "days": 2,
               "arrives_by": null,
               "duration_terms": "Delivery in 1 to 3 business days.",
               "trackable": true,
               "insurance": false,
               "insurance_amount_local": "0.00",
               "insurance_currency_local": null,
               "insurance_amount": "0.00",
               "insurance_currency": null,
               "messages": [],
               "carrier_account": "078870331023437cb917f5187429b093",
               "test": true
           },
           ...
       ],
       "carrier_accounts": [],
       "metadata": "Customer ID 123456",
       "messages": [],
       "test": true
   }

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
    {
        "object_state":"VALID",
        "object_purpose":"PURCHASE",
        "object_created":"2013-12-09T01:56:52.780Z",
        "object_updated":"2013-12-09T01:56:52.780Z",
        "object_id":"cf6fea899f1848b494d9568e8266e076",
        "object_owner":"careemtle@gocareem.com",
        "shipment":"5e40ead7cffe4cc1ad45108696162e42",
        "attributes":[],
        "amount_local":"5.35",
        "currency_local":"USD",
        "amount":"5.35",
        "currency":"USD",
        "provider":"USPS",
        "servicelevel_name":"Priority Mail",
        "servicelevel_token":"usps_priority",
        "servicelevel_terms":"",
        "days":2,
        "duration_terms":"Delivery within 1, 2, or 3 days based on where your package started and where it’s being sent.",
        "trackable":true,
        "insurance":true,
        "insurance_amount_local":"0",
        "insurance_currency_local":null,
        "insurance_amount":"0",
        "insurance_currency":null,
        "carrier_account":"b741b99f95e841639b54272834bc478c",
        "zone":"1",
        "messages":[],
        "test":true
    }
   A rate is an available service of a shipping provider for a given shipment,
   including the price and transit time.

   ##End Points

   ### Get shipping rates

   GET /shipments/<SHIPMENT OBJECT ID>/rates/[<CURRENCY CODE>/]

   ### Retrieve an existing rate by object id.

   GET /rates/<RATE OBJECT ID>



##5. Transaction (shipping labels)
    {
       "object_state":"VALID",
       "object_status":"SUCCESS",
       "object_created":"2014-07-25T02:09:34.422Z",
       "object_updated":"2014-07-25T02:09:34.513Z",
       "object_id":"ef8808606f4241ee848aa5990a09933c",
       "object_owner":"careemtle@gocareem.com",
       "test":true,
       "rate":"ee81fab0372e419ab52245c8952ccaeb",
       "tracking_number":"",
       "tracking_status":null,
       "tracking_url_provider":"",
       "label_url":"",
       "commercial_invoice_url": "",
       "messages":[

       ],
       "customs_note":"",
       "submission_note":"",
       "metadata":""
    }

    A transaction is the purchase of a shipping label from a shipping provider for a specific service.

    ##End Points:

    ### Create a transaction based on a rate object

    POST /transactions/

    ### Retrieve an existing transaction by object id.

    GET /transactions/<TRANSACTION OBJECT ID>





##6. Customs Items
    {
        "object_created": "2014-02-24T22:05:55.014",
        "object_updated": "2014-02-24T22:05:55.016",
        "object_id": "0265b7cc4d71468197b2e8584cf8fc05",
        "object_owner": "careemtle@gocareem.com",
        "object_state": "VALID",
        "description": "T-Shirt",
        "quantity": 2,
        "net_weight": "400",
        "mass_unit": "g",
        "value_amount": "20",
        "value_currency": "USD",
        "tariff_number": "",
        "origin_country": "US",
        "metadata": "Order ID #123123",
        "test": true
    }
   Customs items are distinct items in your shipment parcel.

   ## END POINTS

   ### Creates a new Customs Item object.

   POST /customs/items/

   ### Retrieve an existing Customs Item by object id.

   GET /customs/items/<CUSTOMS ITEM OBJECT ID>

   ### List all custom-items objects.

   GET /customs/items/

##7. Carrier Accounts
    {
        "object_id":"b741b99f95e841639b54272834bc478c",
        "object_owner": "careemtle@gocareem.com",
        "carrier": "fedex",
        "account_id": "321123",
        "parameters": {
            "meter": "789987"
        },
        "test": false,
        "active": true
    }

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

  {
    "carrier": "usps",
    "tracking_number": "9205590164917312751089",
    "address_from": {
      "city": "Bangalore",
      "state": "Karnataka",
      "zip": "89101",
      "country": "India"
    },
    "address_to": {
      "city": "Delhi",
      "state": "Delhi",
      "zip": "110085",
      "country": "India"
    },
    "eta": "2016-07-23T00:00:00Z",
    "servicelevel": {
      "token": "usps_priority",
      "name": "Priority Mail"
    },
    "metadata": null,
    "tracking_status": {
      "object_created": "2016-07-23T20:35:26.129Z",
      "object_updated": "2016-07-23T20:35:26.129Z",
      "object_id": "ce48ff3d52a34e91b77aa98370182624",
      "status": "DELIVERED",
      "status_details": "Your shipment has been delivered at the destination mailbox.",
      "status_date": "2016-07-23T13:03:00Z",
      "location": {
        "city": "Spotsylvania",
        "state": "VA",
        "zip": "22551",
        "country": "US"
      }
    },
    "tracking_history": [
      {
        "object_created": "2016-07-22T14:36:50.943Z",
        "object_id": "265c7a7c23354da5b87b2bf52656c625",
        "status": "TRANSIT",
        "status_details": "Your shipment has been accepted.",
        "status_date": "2016-07-21T15:33:00Z",
        "location": {
          "city": "Las Vegas",
          "state": "NV",
          "zip": "89101",
          "country": "US"
        }
      },
      ...

      {
        "object_created": "2016-07-23T20:35:26.129Z",
        "object_id": "aab1d7c0559d43ccbba4ff8603089e56",
        "status": "DELIVERED",
        "status_details": "Your shipment has been delivered at the destination mailbox.",
        "status_date": "2016-07-23T13:03:00Z",
        "location": {
          "city": "Spotsylvania",
          "state": "VA",
          "zip": "22551",
          "country": "US"
        }
      }
    ]
  }

  ### Request the tracking status of a shipment by sending a GET request.

   GET /tracks/<CARRIER>/<TRACKING NUMBER>/

  ### Register a tracking webhook

   POST /tracks/









