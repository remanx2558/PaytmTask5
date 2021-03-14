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
