# PaytmTask5
Question: Design a payment gateway system(e-wallet) from scratch which supports these features:

- User registeration on e-wallet.
- Merchant registration on e-wallet
- Payment acceptance by any merchant. 
- Creating user wallet for user registered and enabling it in payments flow.
- Create merchant wallet here where payment money will be credited from user wallet to merchant's wallet.

Following APIs needs to be implemented:
1) POST user/register (Take user details as input)
2) POST user/add/money (add amount to user wallet)
3) POST merchant/register
3) POST transaction/initiate -- it should fail if user wallet doesn't have enough balance
4) GET transaction/status -- to be used by user/merchant both.

Maintain a database for storing order,user,merchant data
Use OOPs concepts(inheritance, polymorphism) as much as you can to implement this and try to build this system keeping data security/concurrency issues in mind.

Tech Stack expected:
- Java
- MySql
- Spring Boot
- Hibernate


User Related APIs:
A)GET :
        1)http://localhost:9090/users
        2)http://localhost:9090/users/uid
B)POST:
        1)http://localhost:9090/users
        2
body(JSON):{
    "firstName" : "Yashwant",
    "lastName" : "Gahlot",
    "email":"gyash2558@gmail.com",
    "address1":"xyxs",
    "address2":"sdfadss",
    "mobile":994564,
    "gender":"female"
    
}
C)PUT:
        1)http://localhost:9090/users/uid
D)DELETE:

API USED:
GET: http://localhost:9090/transaction/status/{tid}
GET: http://localhost:9090/transaction/{tid}
GET: http://localhost:9090/transaction/all
GET: http://localhost:9090/wallet

POST:http://localhost:9090/(customer or merchant)/register
{
    "mobileWallet" : 3433344232
}

POST: http://localhost:9090/transaction/make
body:{
    "sender": 3433344232,
    "receiver": 12332232,
    "amount": 500
}

PUT:http://localhost:9090/wallet/add/money/{amount}
{
    "mobileWallet" :  12332232
}
PUT: http://localhost:9090/wallet/activate/(0 or 1)

{
    "mobileWallet" :  12332232
}



