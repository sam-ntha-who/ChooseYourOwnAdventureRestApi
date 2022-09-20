# DIY Storytime Rest API
This is the RESTful API for DIY Storytime, our web app for the Final Project for Grand Circus Java Bootcamp. The web app can be found here: https://github.com/sam-ntha-who/ChooseYourOwnAdventureWebApp.git

# The DIY Storytime Web App:

The DIY Storytime web app makes use of this API to communicate with our No-SQL database to perform all crud functionality to both Scene and Story collections so that stories can be played, written, edited and deleted with ease. As this web app is for creating Choose Your Own Adventure style stories, the data is arranged in a non-binary or multifurcating tree structure so that story paths can be easily nested within the scenes they are chosen from. 

# The API: 

This API performs all of the crud functionality via HTTP Requests from the web app and upon initialization copies the stories and scenes from a master collections into the collections that the user can create, read, update & delete. It assembles the tree structure whenever something is changed via the play function and caluclates the pathlengths and boolean for longest and shortest paths so that the app is updated dynamically. 

# Choose Your Own Adventure?

An interactive book or visual novel (or web app) written from a second-person point of view that gamifies the reading proces, with the reader assuming the role of the protagonist and making choices that determine the main character's actions and the plot's outcome. 
