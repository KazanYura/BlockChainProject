# BlockChainProject

## Preparation part
To use this server you need to 
1. Create schema by using file **schema.sql** in **resources folder**
2. Create user **root** for your database with password **initial** or change settings in *hibernate.cfg.xml*
3. Launch server.
## Routes 
All routes starts with <b>http://localhost:8080/</b>  
### User part  
 <b> /users/add</b> add user to database.(POST request) Request format  
 `  
{   
	"firstName":"Username",  
	"password":"Password"  
}   
`   
 **returns full user with id.**  
Responce  
`{  
    "id": 1,  
    "firstName": "Username",  
    "password": "Password"  
}  
`  
**users/validate/** validate user (GET request) Request format   
`{  
	"firstName":"Username",  
	"password":"Password"  
}  
`  
**returns user if validation is ok and status code 404 with error message if not**  
  
**users/delete** remove user (DELETE request)  
  
### Blocks part  
  
**blocks/** get all blocks (GET request) just for debug currently disabled.  
**blocks/add** add block to database.(POST request) Request format    
`{  
	"userId":3,  
	"gameResults":42,  
}  
`
**returns full info about block**  
`{  
    "userId": 3,  
    "gameResults": 42,  
    "previousHash": "0",  
    "last": true,   
    "timeStamp": 1578946283884,  
    "hash": "dd547df5b82b9f74d53b860da20514643426750047197dfbab7f7a7358c98953"  
}   
`
**blocks/hash** get block by hash (GET request) Request format  
`{  
  "hash": "dd547df5b82b9f74d53b860da20514643426750047197dfbab7f7a7358c98953"  
}  
`
Responce format<br />
`{  
    "userId": 3,  
    "gameResults": 42,  
    "previousHash": "0",  
    "last": true,  
    "timeStamp": 1578946283884,  
    "hash": "dd547df5b82b9f74d53b860da20514643426750047197dfbab7f7a7358c98953"  
}<br />
`
**blocks/edit** edit existing block (PUT request) just for debug currently disabled.  
Request format   
`{  
  "hash": "dd547df5b82b9f74d53b860da20514643426750047197dfbab7f7a7358c98953",  
  "gameResults":42,  
  "userId":17  
}  
`  
Responce  
`  
{  
    "userId": 17,  
    "gameResults": 42,  
    "previousHash": null,  
    "last": true,  
    "timeStamp": 1578946506963,  
    "hash": "dd547df5b82b9f74d53b860da20514643426750047197dfbab7f7a7358c98953"  
}  
`  
**blocks/userlastblock** returns last game for user with certain id (GET request)  
Request  
`  
{  
  "userId":17  
}  
`  
Responce  
`
{  
    "userId": 17,  
    "gameResults": 42,  
    "previousHash": null,  
    "last": true,  
    "timeStamp": 1578946506963,  
    "hash": "dd547df5b82b9f74d53b860da20514643426750047197dfbab7f7a7358c98953"  
}  
`
