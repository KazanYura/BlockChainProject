# BlockChainProject

## Preparation part
To use this server you need to 
1. Create schema by using file **schema.sql** in **resources folder**
2. Create user **root** for your database with password **initial** or change settings in *hibernate.cfg.xml*
3. Launch server.
## Routes 
All routes starts with <b>http://localhost:8080/</b>
### User part <br />
 <b> /users/add</b> add user to database.(POST request) Request format  <br />
{ <br />
<	"firstName":"Username", <br />
	"password":"Password" <br />
} <br />
 **returns full user with id.**  <br />
Responce <br />
`{ <br />
    "id": 1, <br />
    "firstName": "Username", <br />
    "password": "Password" <br />
}<br />
`
**users/validate/** validate user (GET request) Request format<br />
`{<br />
	"firstName":"Username",<br />
	"password":"Password"<br />
}<br />
`
**returns user if validation is ok and status code 404 with error message if not** <br />
**users/delete** remove user (DELETE request)<br />
<br />
### Blocks part <br />
<br />
**blocks/** get all blocks (GET request) just for debug currently disabled.<br />
**blocks/add** add block to database.(POST request) Request format <br />
`{<br />
	"userId":3,<br />
	"gameResults":42,<br />
}<br />
`
**returns full info about block**<br />
`{<br />
    "userId": 3,<br />
    "gameResults": 42,<br />
    "previousHash": "0",<br />
    "last": true,<br />
    "timeStamp": 1578946283884,<br />
    "hash": "dd547df5b82b9f74d53b860da20514643426750047197dfbab7f7a7358c98953"<br />
}<br /><br />
`
**blocks/hash** get block by hash (GET request) Request format<br />
`{<br />
  "hash": "dd547df5b82b9f74d53b860da20514643426750047197dfbab7f7a7358c98953"<br />
}<br />
`
Responce format<br />
`{<br />
    "userId": 3,<br />
    "gameResults": 42,<br />
    "previousHash": "0",<br />
    "last": true,<br />
    "timeStamp": 1578946283884,<br />
    "hash": "dd547df5b82b9f74d53b860da20514643426750047197dfbab7f7a7358c98953"<br />
}<br />
`
**blocks/edit** edit existing block (PUT request) just for debug currently disabled.<br />
Request format <br />
`{<br />
  "hash": "dd547df5b82b9f74d53b860da20514643426750047197dfbab7f7a7358c98953",<br />
  "gameResults":42,<br />
  "userId":17<br />
}<br />
`
Responce<br />
`
{<br />
    "userId": 17,<br />
    "gameResults": 42,<br />
    "previousHash": null,<br />
    "last": true,<br />
    "timeStamp": 1578946506963,<br />
    "hash": "dd547df5b82b9f74d53b860da20514643426750047197dfbab7f7a7358c98953"<br />
}<br />
`
**blocks/userlastblock** returns last game for user with certain id (GET request)<br />
Request<br />
`
{<br />
  "userId":17<br />
}<br />
`
Responce<br />
`
{<br />
    "userId": 17,<br />
    "gameResults": 42,<br />
    "previousHash": null,<br />
    "last": true,<br />
    "timeStamp": 1578946506963,<br />
    "hash": "dd547df5b82b9f74d53b860da20514643426750047197dfbab7f7a7358c98953"<br />
}<br />
`
