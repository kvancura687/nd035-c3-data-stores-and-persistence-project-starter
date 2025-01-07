create user 'critter'@'localhost' identified by '12345'; -- Create the user
grant all on critterdb.* to 'critter'@'localhost'; -- Gives all privileges to that user on new db