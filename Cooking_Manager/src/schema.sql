/*
Author:  joyth619
Created: 16/04/2018
*/

create table profile (
    userID varchar(50),
    name varchar(40),
    gluten_free boolean, 
    dairy_free boolean,
    vegan boolean, 
    vegetarian boolean,
    paleo boolean, 
    playlists array,
	 username varchar(20),
	 password varchar(1000),
    PRIMARY KEY (userID)
);

create table recipes (
    RecipeID varchar(10),
    name varchar(50),
    prepTime varchar(50),
    servingsize varchar(50),
    tags varchar(5),
    ingredients varchar(1000),
    description varchar(1000),
    method varchar(1000),
    PRIMARY KEY (RecipeID)
);

create table playlists (
    playlistID varchar(100),
    playlistName varchar(20),
    userID varchar(50),
    contents array,
    PRIMARY KEY (playlistID),
    FOREIGN KEY (userID) references profile(userID)
)
